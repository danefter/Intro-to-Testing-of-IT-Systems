package inte.project;
//author Dan Jensen

import java.util.*;
import java.util.stream.Stream;

public class Register {

    private final Store store;

    private Report dailyReport = new Report();

    private final Scanner scanner = new Scanner(System.in);

    //saves purchases based on ID
    private HashMap<String, Purchase> dailyPurchases = new HashMap<>();

    //saves purchase collections based on Date
    private HashMap<String, HashMap<String, Purchase>> dailyReports = new HashMap<>();

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

        public Purchase scanProductsForPurchase(Product... products) {
        Purchase purchase = new Purchase();
        for (Product product: products) {;
                purchase.addProduct(inventory.get(product.getId()));
                inventory.remove(product.getId());
        }
        return purchase;
        }

    public void makePurchase(Purchase purchase, Payment... payments) {
        purchase.paySeparatelyForProducts(payments);
        if (purchase.getCashFromPayment() != null) addCashPaymentToRegister(purchase);
        totalBalance = totalBalance.add(purchase.getCurrentPayment());
        addToDailyReports(purchase);
        printReceipt(purchase);
        }

    //gets the cash
    public void addCashPaymentToRegister(Purchase purchase) {
        for (Cash cash: purchase.getCashFromPayment()) {
            cashBalance.get(cash.getDenomination().getAmountOfCrown()).add(cash.getQuantity());
        }
    }

    //literally just a system out print of purchase.getInfo
    public void printReceipt(Purchase purchase){
        Receipt receipt = new Receipt(purchase, store);
        receipt.print();
    }

    //logs purchases
    public void addToDailyReports(Purchase purchase){
        dailyPurchases.put(purchase.getPurchaseId(), purchase);
        dailyReports.put(purchase.getDateOfPurchase(), dailyPurchases);
    }

    public void getPointsForPurchase(Purchase costOfPurchase){
        int points = 0;
        int money = costOfPurchase.getCurrentPayment().getAmountInOre();
        if(costOfPurchase.getCashFromPayment() == null){
            if(costOfPurchase.getCustomer().isMember()){
                if(money < 100){
                    points = (int)(money * 0.1);
                }
                else if(money < 1000){
                    points = (int)(money * 0.2);
                }
                else if(money < 10000){
                    points = (int)(money * 0.3);
                }else{
                    points = (int)(money * 0.4);
                }
            }
            costOfPurchase.getCustomer().getMembership().getMembershipPoints().addPoints(points);
        }
    }


    public Collection<Cash> getCashBalance() {
        return Collections.unmodifiableCollection(cashBalance.values());
    }

    public Money getTotalBalance() {
        return totalBalance;
    }
}
