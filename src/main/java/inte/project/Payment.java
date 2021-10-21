package inte.project;
//author Dan Jensen

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Payment {

    private Money amountPaid = new Money(0);
    private Money paymentAmount;
    private String paymentType;
    private Customer customer;

    private Card cardPayment;
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

    public Payment(Money paymentAmount, Card card) {
        this.paymentAmount = paymentAmount;
        this.cardPayment = card;
        card.pay(paymentAmount);
        customer = card.getCardOwner();
        this.paymentType = "Card";
    }

    public Payment(Money paymentAmount, MembershipPoints pointPayment) {
        this.paymentAmount = paymentAmount;
        amountPaid = amountPaid.add(new Money(pointPayment.getCertainAmountOfPoints(paymentAmount.getAmountInOre())));
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


    public Collection<Cash> getCashPaymentValues() {
        return Collections.unmodifiableCollection(this.cashPayment.values());
    }


    public String getPaymentType() {
        return paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Card getCardPayment() {
        return cardPayment;
    }

    @Override
    public String toString() {
        if (this.paymentType.equals("Card") && cardPayment.getCardType().equals("Debitcard"))
            return getCardPayment().toString() + "\nAmount paid: " + paymentAmount;
        if (this.paymentType.equals("Card") && cardPayment.getCardType().equals("Giftcard"))
            return getCardPayment().toString() + "\nAmount paid: " + paymentAmount + "\nRemaining balance: " + cardPayment.getBalanceAmount();
        if (this.paymentType.equals("Points"))
            return pointPayment.toString() + "\nAmount paid: " + amountPaid;
        return "Cash:" + "\nAmount paid: " + amountPaid;
    }
}
