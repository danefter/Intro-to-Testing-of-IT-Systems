package inte.project;
//author Dan Jensen

import java.util.*;
import java.util.stream.Stream;

public class Register {


    private final Store store;

    private Report dailyreport = new Report();

    private final ScannerInput scanner = new ScannerInput();


    //saves purchases based on ID
    private HashMap<String, Purchase> dailyPurchases = new HashMap<>();

    //saves purchase collections based on Date
    private HashMap<String, HashMap<String, Purchase>> dailyReports = new HashMap<>();

    private HashMap<Integer, Cash> cashBalance = new HashMap<>();

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

    public Register(Store store) {
        this.store = store;
        for (Cash cash: ACCEPTED_DENOMINATIONS)
            this.cashBalance.putIfAbsent(cash.getDenomination().getAmountOfCrown(), cash);
        }

     //doesn't actually scan anything, needs functional scanner
    public Purchase scanProductsForPurchase(Product... productInputs){
        return new Purchase(scanner.scanProducts(productInputs));
    }

    //supposed to allow the employee to choose payment methods based on the customers desires
    public Payment[] scannerInputsPaymentMethods(Payment... payments) {
        return payments;
    }

    //makes the purchase based on choasen methods
    public void makePurchaseWithDesiredPaymentMethods(Purchase purchase, Payment... payments) {
        purchase.paySeparatelyForProducts(scannerInputsPaymentMethods(payments));
        if (purchase.getCashFromPayment() != null) addCashPaymentToRegister(purchase);
        totalBalance = totalBalance.add(purchase.getCurrentPayment());
        printReciept(purchase);
        }

    //gets the cash
    public void addCashPaymentToRegister(Purchase purchase) {
        for (Cash cash: purchase.getCashFromPayment()) {
            cashBalance.get(cash.getDenomination().getAmountOfCrown()).add(cash.getQuantity());
        }
    }

    //literally just a system out print of purchase.getInfo
    public void printReciept(Purchase purchase){
        Receipt receipt = new Receipt(purchase, store);
        receipt.print();
    }

    //logs purchases
    public void addToDailyReports(Purchase purchase){
        dailyPurchases.put(purchase.getPurchaseId(), purchase);
        dailyReports.put(purchase.getDateOfPurchase(), dailyPurchases);
    }


    public Collection<Cash> getCashBalance() {
        return Collections.unmodifiableCollection(cashBalance.values());
    }

    public Money getTotalBalanceInOre() {
        return totalBalance;
    }
}
