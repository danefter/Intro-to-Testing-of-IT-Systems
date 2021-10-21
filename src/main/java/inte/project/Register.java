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
    private HashMap<Integer, Cash> cashBalance = new HashMap<>();

    List<Cash> acceptedDenominations = Arrays.asList(
            new Cash(new Money(1, 0), 0),
            new Cash(new Money(5, 0), 0),
            new Cash(new Money(10, 0), 0),
            new Cash(new Money(20, 0), 0),
            new Cash(new Money(50, 0), 0),
            new Cash(new Money(100, 0), 0),
            new Cash(new Money(200, 0), 0),
            new Cash(new Money(500, 0), 0),
            new Cash(new Money(1000, 0), 0));

    public Register() {
        for (Cash cash: acceptedDenominations)
            this.cashBalance.putIfAbsent(cash.getDenomination().getAmountOfCrown(), cash);
        }

    public void calculateCurrentTotal(Product... products) {
        for (Product p: products)
            this.currentTotal.add(p.getPricePlusVAT());
        }

    public Money getCurrentTotal() {
        return this.currentTotal;
    }

    public int getCashPaymentTotal() {
        return this.cashPaymentTotal;
    }

    public int getCardPaymentTotal(){
        return cardPaymentTotal;
    }

    public Money getCurrentPayment(){
        return currentPayment;
    }

    public void printReceipt() {
    }
}
