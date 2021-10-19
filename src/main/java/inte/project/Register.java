//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Register {
    private int cardPaymentTotal;
    private int cashPaymentTotal;
    private int currentTotal;
    private int currentPayment;
    private HashMap<LocalDateTime, CardTransaction> cardTransactions = new HashMap<>();
    private HashMap<LocalDateTime, CashTransaction> cashTransactions = new HashMap<>();
    private HashMap<Integer, Cash> cashBalance = new HashMap<>();

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
            this.currentTotal += p.getPrice().getAmountInOre();
        }


    public void receiveCashPayment(CashTransaction cashTransaction) {
        for(Cash cash: cashTransaction.getPayment()) {
            Cash oldBalance = this.cashBalance.get(cash.getDenomination());
            oldBalance.add(cash.getQuantity());
        }
        while(this.currentPayment < this.currentTotal) {
            this.receiveCashPayment(new CashTransaction());
        }
        this.cashPaymentTotal += this.currentPayment;
        this.cashTransactions.put(LocalDateTime.now(), cashTransaction);
        this.printReceipt();
        this.resetTotals();
    }

    public void receiveCardPayment(CardTransaction cardTransaction) {
        cardTransaction.getPayment(this.currentTotal);
        this.cardTransactions.put(LocalDateTime.now(), cardTransaction);
        this.cardPaymentTotal += this.currentTotal;
        this.printReceipt();
        this.resetTotals();
    }

    public int getCurrentTotal() {
        return this.currentTotal;
    }

    public int getCashPaymentTotal() {
        return this.cashPaymentTotal;
    }

    public int getCardPaymentTotal(){
        return this.cardPaymentTotal;
    }

    public void resetTotals() {
        this.currentTotal = 0;
        this.currentPayment = 0;
    }

    public void printReceipt() {
    }
}
