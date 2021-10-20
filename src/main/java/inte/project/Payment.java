package inte.project;
//author Dan Jensen

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Payment {

    private Money amountPaid = new Money(0);
    private Money paymentAmount;
    private String paymentType;

    private HashMap<String, Card> cardPayments = new HashMap<>();
    private HashMap<Integer, Cash> cashPayment = new HashMap<>();
    private MembershipPoints pointPayment = new MembershipPoints();

    public Payment(Money paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Payment(Money paymentAmount, Cash... cash) {
        this.paymentAmount = paymentAmount;
        for (Cash money : cash) {
            amountPaid = amountPaid.add(this.collectCashPayments(money));
        }
        this.paymentType = "Cash";
    }

    public Payment(Money paymentAmount, Card... cards) {
        this.paymentAmount = paymentAmount;
        for (Card card: cards) {
            cardPayments.put(card.getCardType(), card);
            card.pay(paymentAmount);
        }
        this.paymentType = "Card";
    }

    public Payment(Money paymentAmount, MembershipPoints pointPayment) {
        this.paymentAmount = paymentAmount;
        this.paymentAmount.add(new Money(pointPayment.getCertainAmountOfPoints(paymentAmount.getAmountInOre())));
        this.paymentType = "Points";
    }

    public Money collectCashPayments(Cash money) {
        if (!this.cashPayment.containsKey(money.getDenomination().getAmountOfCrown()))
        this.cashPayment.put(money.getDenomination().getAmountOfCrown(), money);
        else this.cashPayment.get(money.getDenomination().getAmountOfCrown()).add(money.getQuantity());
        return money.getTotal();
    }

    public Money getPayment() {
        if (this.paymentType.equals("Cash")) return amountPaid;
        return paymentAmount;
    }

    public Collection<Card> getCardPaymentValues() {
        return Collections.unmodifiableCollection(this.cardPayments.values());
    }


    public Collection<Cash> getCashPaymentValues() {
        return Collections.unmodifiableCollection(this.cashPayment.values());
    }


    public String getPaymentType() {
        return paymentType;
    }
}
