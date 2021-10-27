
package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class PaymentTest {

    public PaymentTest() {}

    @Test
    void unspecifiedPaymentConstructor() {
        Payment payment = new Payment(new Money(100));
        Assertions.assertEquals("Insufficient amount", payment.getPaymentType());
    }

    @Test
    void unspecifiedPaymentToString() {
        Money money = new Money(200, 0);
        Payment payment = new Payment(money);
        Assertions.assertEquals("Insufficient amount:\nAmount paid: 0:00 kr", payment.toString());
    }

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
        Assertions.assertEquals("Giftcard: Albin Ahl," + " Balance: " + card.getBalance() + "\nAmount paid: 5000:00 kr\nRemaining balance: 995000:00 kr" , payment.toString());
    }

    @Test
    void pointsPaymentConstructor() {
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(500000);
        Payment payment = new Payment(new Money(5000, 0), customer);
        Assertions.assertEquals("Points", payment.getPaymentType());
    }

    @Test
    void pointsPaymentConstructorThrows() {
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(new Money(5000, 0), customer);
        });
        Assertions.assertEquals("Customer is not member and can't pay with points.", exception.getMessage());
    }

    @Test
    void pointsPaymentToString() {
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(500000);
        MembershipPoints points = customer.getMembership().getMembershipPoints();
        Payment payment = new Payment(new Money(5000, 0), customer);
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
        Assertions.assertEquals(card, payment.getCardPayment());
    }

    @Test
    void getCustomer() {
        String dateOfBirth = "1999-04-03";
        Money balance = new Money(5000, 0);
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Card card = new DebitCard("Debitcard", customer, balance);
        Payment payment = new Payment(balance, card);
        Assertions.assertEquals(customer, payment.getCustomer());
    }

    @Test
    void cardPaymentPays() {
        String dateOfBirth = "1999-04-03";
        Money balance = new Money(5000, 0);
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Card card = new DebitCard("Debitcard", customer, balance);
        Payment payment = new Payment(balance, card);
        Assertions.assertEquals(balance, payment.getPayment());
    }

    @Test
    void pointsPaymentPays() {
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(500000);
        int points = customer.getMembership().getMembershipPoints().getAllPoints();
        Payment payment = new Payment(new Money(5000, 0), customer);
        Assertions.assertEquals(points, payment.getPayment().getAmountInOre());
    }

    @Test
    void getPointsPayment() {
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(500000);
        int points = customer.getMembership().getMembershipPoints().getAllPoints();
        Payment payment = new Payment(new Money(5000, 0), customer);
        Assertions.assertEquals(customer.getMembership().getMembershipPoints(), payment.getPointPayment());
    }

    @Test
    void addCashToPayment() {
        Money money = new Money(1000, 0);
        Payment payment = new Payment(money);
        payment.addCashToEmptyPayment(new Cash(money, 1));
        Assertions.assertEquals(money, payment.getPayment());
        Assertions.assertEquals("Cash", payment.getPaymentType());
    }

    @Test
    void cardPaymentConstructorInsufficient() {
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(0));
        Payment payment = new Payment(new Money(5000), card);
        Assertions.assertEquals("Insufficient amount", payment.getPaymentType());
    }

    @Test
    void pointsPaymentConstructorInsufficient() {
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(0);
        Payment payment = new Payment(new Money(5000, 0), customer);
        Assertions.assertEquals("Insufficient amount", payment.getPaymentType());
    }

    @Test
    void getCashPaymentValues() {
        Money money = new Money(1000, 0);
        Cash cash = new Cash(money, 1);
        Payment payment = new Payment(money, cash);
        Collection<Cash> casshh = new ArrayList<>();
        casshh.add(cash);
        Assertions.assertIterableEquals(casshh, payment.getCashPaymentValues());
    }

    @Test
    void collectCashPaymentValues() {
        Money money = new Money(1000, 0);
        Cash cash = new Cash(money, 0);
        Payment payment = new Payment(money, cash);
        payment.collectCashPayments(cash);
        Collection<Cash> casshh = new ArrayList<>();
        casshh.add(cash);
        Assertions.assertIterableEquals(casshh, payment.getCashPaymentValues());
    }

    @Test
    void cardPaymentValidate() {
        String dateOfBirth = "1999-04-03";
        Money balance = new Money(5000, 0);
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Card card = new DebitCard("Debitcard", customer, balance);
        Payment payment = new Payment(balance, card);
        Assertions.assertEquals("Card", payment.getPaymentType());
    }

    @Test
    void pointsPaymentValidate() {
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(500000);
        Payment payment = new Payment(new Money(5000, 0), customer);
        Assertions.assertEquals("Points", payment.getPaymentType());
    }

    @Test
    void equalPayments() {
        Money money = new Money(1000, 0);
        Cash cash = new Cash(money, 1);
        Payment payment = new Payment(money, cash);
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(500000);
        Payment payment2 = new Payment(new Money(1000, 0), customer);
        Assertions.assertEquals(payment.getPayment(), payment2.getPayment());
    }
}
