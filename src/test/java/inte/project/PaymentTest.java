
package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    public PaymentTest() {}

    @Test
    void cardPaymentConstructor() {
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        Payment payment = new Payment(new Money(5000, 0), card);
        Assertions.assertEquals("Card", payment.getPaymentType());
    }

    @Test
    void cardPaymentToString() {
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        GiftCard card = new GiftCard("Giftcard", person, new Money(100000000));
        Payment payment = new Payment(new Money(5000, 0), card);
        Assertions.assertEquals("Giftcard: Albin Ahl\nAmount paid: 5000:00 kr\nRemaining balance: 995000:00 kr" , payment.toString());
    }

    @Test
    void pointsPaymentConstructor() {
        Payment payment = new Payment(new Money(5000, 0), new MembershipPoints(500000));
        Assertions.assertEquals("Points", payment.getPaymentType());
    }

    @Test
    void pointsPaymentToString() {
        MembershipPoints points = new MembershipPoints(500000);
        Payment payment = new Payment(new Money(5000, 0), points);
        Assertions.assertEquals(points + "\nAmount paid: 5000:00 kr", payment.toString());
    }

    @Test
    void cashPaymentConstructor() {
        Money money = new Money(200, 0);
        Payment payment = new Payment(money, new Cash(money, 10), new Cash(money, 10));
        Assertions.assertEquals("Cash", payment.getPaymentType());
    }

    @Test
    void cashPaymentToString() {
        Money money = new Money(200, 0);
        Payment payment = new Payment(money, new Cash(money, 10), new Cash(money, 10));
        Assertions.assertEquals("Cash:\nAmount paid: 4000:00 kr", payment.toString());
    }

    @Test
    void getCashPayment() {
        Money money = new Money(20, 0);
        Money money1 = new Money(400, 0);
        Payment payment = new Payment(money, new Cash(money, 10), new Cash(money, 10));
        Assertions.assertEquals(money1, payment.getPayment());
    }

    @Test
    void getCardPayment() {
        String dateOfBirth = "1999-04-03";
        Money balance = new Money(5000, 0);
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Card card = new DebitCard("Debitcard", customer, balance);
        Payment payment = new Payment(balance, card);
        Assertions.assertEquals(balance, payment.getPayment());
    }

    @Test
    void getPointsPayment() {
        MembershipPoints points = new MembershipPoints(100000);
        Money pay = new Money(1000);
        Payment payment = new Payment(pay, points);
        Assertions.assertEquals(pay, payment.getPayment());
    }



}
