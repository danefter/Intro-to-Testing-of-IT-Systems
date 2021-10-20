package inte.project;
//author Dan Jensen

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;



public class Payment {

    private HashMap<String, Card> cardPayments = new HashMap();

    private HashMap<Integer, Cash> cashPayment = new HashMap();

    private MembershipPoints pointPayment = new MembershipPoints();

    private Money paymentTotal = new Money(0);

    private Money paidAmount = new Money(0);

    private String paymentType;

    public Payment(Money paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public Payment(Money paymentTotal, Cash... cash) {
        this.paymentTotal = paymentTotal;
        for (Cash money : cash) {
            this.collectCashPayments(money);
            paidAmount.add(money.getTotal());
        }
        this.paymentType = "Cash";
    }

    public Payment(Money paymentTotal, Card... cards) {
        this.paymentTotal = paymentTotal;
        for (Card card: cards) {
            cardPayments.put(card.getCardType(), card);
            paidAmount.add(card.pay(paymentTotal));
        }
        this.paymentType = "Cards";
    }

    public Payment(Money paymentTotal, MembershipPoints pointPayment) {
        paidAmount.add(new Money(pointPayment.getCertainAmountOfPoints(paymentTotal.getAmountInOre())));
        this.paymentType = "Points";
    }



    public void collectCashPayments(Cash money) {
        Cash earlierMoney = this.cashPayment.putIfAbsent(money.getDenomination(), money);
        if (earlierMoney != null) {
            earlierMoney.add(money.getQuantity());
        }
    }

    public Money getPayment() {
        return paidAmount;
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
