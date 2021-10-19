package inte.project;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

//@author Dan Jensen

public class Payment {

    private HashMap<String, Card> cardPayments = new HashMap();

    private HashMap<Integer, Cash> cashPayment = new HashMap();

    private MembershipPoints pointPayment = new MembershipPoints();

    private String paymentType;

    private int totalPayments;

    public Payment() {}

    public Payment(Card... cards) {
        for (Card card: cards)
            cardPayments.put(card.getCardType(), card);
        this.paymentType = "Card";
    }

    public Payment(Cash... cash) {
        for (Cash money: cash)
            this.collectCashPayments(money);
        this.paymentType = "Cash";
    }

    public Payment(MembershipPoints points) {
        this.pointPayment = points;
        this.paymentType = "Points";
    }


    public void collectCashPayments(Cash money) {
        Cash earlierMoney = this.cashPayment.putIfAbsent(money.getDenomination(), money);
        if (earlierMoney != null) {
            earlierMoney.add(money.getQuantity());
        }
    }

    public String getPaymentType() {
        return paymentType;
    }

    public int getPayment(int amount) {
        if (this.paymentType.equals("Cash")) addCashToTotal();
        if (this.paymentType.equals("Card")) addCardToTotal(amount);
        if (this.paymentType.equals("Ponts")) totalPayments += pointPayment.getPoints();
        return totalPayments;
    }

    public void addCashToTotal() {
        cashPayment.values().forEach(cash -> totalPayments += cash.getTotal());
    }

    public void addCardToTotal(int amount) {
            cardPayments.values().forEach(card -> totalPayments += card.pay(amount));
    }

    public Collection<Card> getCardPayments() {
        return Collections.unmodifiableCollection(this.cardPayments.values());
    }

    public Collection<Cash> getCashPayment() {
        return Collections.unmodifiableCollection(this.cashPayment.values());
    }

    public MembershipPoints getPointPayment() {
        return pointPayment;
    }
}
