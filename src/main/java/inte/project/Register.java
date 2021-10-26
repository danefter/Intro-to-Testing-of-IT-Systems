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
        Order order = new Order(products);
        for (Product product: products) {;
                inventory.remove(product.getId());
        }
        return order;
        }

        public void cancelOrderAfterScan(Order order) {
            for (Product product : order.getProductsToOrder()) {
                inventory.put(product.getId(), product);
            }
            Collection<Product> cancelledProducts = order.getProductsToOrder();
            order.getProductsToOrder().removeAll(cancelledProducts);
            order.setDateOfOrder();
            printReceipt(order);
        }


    public void payForOrder(Order order, Payment... payments) {
        if (order.payTotalForProducts(payments)){
        if (order.getCashFromPayment() != null) addCashPaymentToRegister(order);
        if (order.getCustomer().getMembership()!= null)
            order.getCustomer().getMembership().getMembershipPoints().addPoints((int)
                    getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
        totalBalance = totalBalance.add(order.getCurrentPayment());
        addToDailyReports(order);}
        else System.out.print("Insufficient payment, please try again");
        printReceipt(order);
    }

    public void payForOrderUsingInput(Order order, Customer customer) {
        int amount = 0;
        int total = order.getCurrentTotal().getAmountOfCrown();
        Payment[] payments = new Payment[0];
        do{
            payments = Arrays.copyOf(payments, payments.length +1);
            Payment payment = waitForInput(order, customer);
            payments[payments.length-1] = payment;
            amount += payment.getPayment().getAmountOfCrown();
    }
        while(amount < total);
        payForOrder(order, payments);
    }


    public Payment waitForInput(Order order, Customer customer) {
        order.setCustomer(customer);
        System.out.print("Choose payment method:");
        return selectPaymentMethod(customer, order.getCurrentTotal().getAmountOfCrown(), scanner.nextLine());
    }


    public Payment selectPaymentMethod(Customer customer, int amount, String type) {
        if (type.equalsIgnoreCase("giftcard")) {
            presentGiftCardBalance(customer.getGiftCard());
            return new Payment(new Money(amount, 0), customer.getGiftCard());
        }
        if (type.equalsIgnoreCase("debitcard")) return new Payment(new Money(amount, 0), customer.getDebitCard());
        if (type.equalsIgnoreCase("points")) {
            presentPointBalance(customer);
            return new Payment(new Money(amount, 0), customer);
        }
        if (type.equalsIgnoreCase("cash")) return payWithCash(amount);
        else return new Payment(new Money(amount, 0));
    }

    public Payment payWithCash(int total) {
        Payment payment = new Payment(new Money(total, 0));
        int amount = 0;
        do {
            try {
                System.out.print("Input denominations then quantity for each:");
                Cash cash = new Cash(new Money(scanner.nextInt(), 0), scanner.nextInt());
                payment.addCashToEmptyPayment(cash);
                amount = payment.getPayment().getAmountOfCrown();
            }
            catch (InputMismatchException e) {
                System.out.print("Not an integer");
            }
        }
        while (total < amount);
        return payment;
    }

    public void customerBecomesMember(Customer customer){
        customer.addMembership();
    }

    public void presentPointBalance(Customer customer) {
        if (customer.getMembership() != null) System.out.print(customer.getMembership().getMembershipPoints().toString());
    }

    public void presentGiftCardBalance(GiftCard giftCard) {
        System.out.print(giftCard.getBalance());
    }

    public void presentTotal(Order order) {
        String totalInfo = "Products: " + order.getProductsAsString()+
                "\nTotal with VAT: " + order.getCurrentTotal();
        totalInfo = totalInfo.replaceAll("Store: "+ store.toString(), "");
        System.out.print(totalInfo.replaceAll("[\\[\\]]", ""));
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

    public Collection<Product> getInventory() {
        return Collections.unmodifiableCollection(inventory.values());
    }

    public Collection<Cash> getCashBalance() {
        return Collections.unmodifiableCollection(cashBalance.values());
    }

    public Money getTotalBalance() {
        return totalBalance;
    }
}
