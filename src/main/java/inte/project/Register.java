package inte.project;
//author Dan Jensen

import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class Register {
    private int cardPaymentTotal;
    private int cashPaymentTotal;
    private Money currentTotal;
    private Money currentPayment;
    private HashMap<Integer, Cash> cashBalance = new HashMap();

    List<Cash> acceptedDenominations = Arrays.asList(
            new Cash(1, 0),
            new Cash(5, 0),
            new Cash(10, 0),
            new Cash(20, 0),
            new Cash(50, 0),
            new Cash(100, 0),
            new Cash(100, 0),
            new Cash(500, 0),
            new Cash(1000, 0));

    public Register() {
        for (Cash cash: acceptedDenominations)
            this.cashBalance.putIfAbsent(cash.getDenomination(), cash);
        }


    public void calculateCurrentTotal(Product... products) {
        for (Product p: products)
            this.currentTotal.add(p.getPrice());
        }


    public Money getCurrentTotal() {
        return this.currentTotal;
    }

    public int getCashPaymentTotal() {
        return this.cashPaymentTotal;
    }


    public void printReceipt() {
    }
}
