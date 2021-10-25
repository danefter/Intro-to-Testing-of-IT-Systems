package inte.project;
//author Dan Jensen

import java.util.*;

public class Register {


    private final Store store;

    private Report dailyReport = new Report();

    private final Scanner scanner = new Scanner(System.in);

    //saves orders based on ID
    private HashMap<String, Order> dailyOrders = new HashMap<>();

    //saves order collections based on Date
    private HashMap<String, HashMap<String, Order>> dailyReports = new HashMap<>();

    private HashMap<Integer, Cash> cashBalance = new HashMap<>();

    private HashMap<String, Product> inventory = new HashMap<>();

    private Money totalBalance = new Money(0);

    //slots for Cash
    private static final List<Cash> ACCEPTED_DENOMINATIONS = Arrays.asList(
            new Cash(new Money(1, 0), 0),
            new Cash(new Money(5, 0), 0),
            new Cash(new Money(10, 0), 0),
            new Cash(new Money(20, 0), 0),
            new Cash(new Money(50, 0), 0),
            new Cash(new Money(100, 0), 0),
            new Cash(new Money(200, 0), 0),
            new Cash(new Money(500, 0), 0),
            new Cash(new Money(1000, 0), 0));

    public Register(Store store, Product... products) {
        this.store = store;
        for (Cash cash: ACCEPTED_DENOMINATIONS)
            this.cashBalance.putIfAbsent(cash.getDenomination().getAmountOfCrown(), cash);
        for (Product p:products) {
            p.addStore(store);
            inventory.put(p.getId(), p);
        }
        }

        public Order scanProductsForOrder(Product... products) {
        Order order = new Order();
        for (Product product: products) {;
                order.addProduct(inventory.get(product.getId()));
                inventory.remove(product.getId());
        }
        return order;
        }

        public void cancelOrderAfterScan(Order order) {
            for (Product product : order.getProductsToOrder()) {
                inventory.put(product.getId(), product);
            }
        }

    public void makeOrder(Order order, Payment... payments) {
        order.paySeparatelyForProducts(payments);
        if (order.getCashFromPayment() != null) addCashPaymentToRegister(order);
        totalBalance = totalBalance.add(order.getCurrentPayment());
        addToDailyReports(order);
        printReceipt(order);
        }

    //gets the cash
    public void addCashPaymentToRegister(Order order) {
        for (Cash cash: order.getCashFromPayment()) {
            cashBalance.get(cash.getDenomination().getAmountOfCrown()).add(cash.getQuantity());
        }
    }

    //literally just a system out print of order.getInfo
    public void printReceipt(Order order){
        Receipt receipt = new Receipt(order, store);
        receipt.print();
    }

    //logs orders
    public void addToDailyReports(Order order){
        dailyOrders.put(order.getOrderId(), order);
        dailyReports.put(order.getDateOfOrder(), dailyOrders);
    }

     //add points kollar membership, returnerar void
    public double getPointsForOrder(int costOfOrder){
        if(costOfOrder < 100){
            return costOfOrder * 0.1;
        }
        else if(costOfOrder < 1000){
            return costOfOrder * 0.2;
        }
        else if(costOfOrder < 10000){
            return costOfOrder * 0.3;
        }else{
            return costOfOrder * 0.4;
        }
    }


    public Collection<Cash> getCashBalance() {
        return Collections.unmodifiableCollection(cashBalance.values());
    }

    public Money getTotalBalance() {
        return totalBalance;
    }
}
