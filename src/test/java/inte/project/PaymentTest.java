//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {
<<<<<<< Updated upstream

    public PaymentTest() {}

    @Test
    void cardPaymentConstructor() {}
=======
    public PaymentTest() {}

    @Test
    void cardPaymentConstructor() {
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        Payment payment = new Payment(new Money(5000, 0), card);
        Assertions.assertEquals("Card", payment.getPaymentType());
    }
>>>>>>> Stashed changes

    @Test
    void multipleCardPaymentConstructor() {

    }

    @Test
<<<<<<< Updated upstream
    void cashPaymentConstructor() {}

    @Test
    void pointsPaymentConstructor() {}
=======
    void cashPaymentConstructor() {
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        GiftCard card1 = new GiftCard("Giftcard", person, new Money(100000000));
        Payment payment = new Payment(new Money(5000, 0), card, card1);
    }

    @Test
    void pointsPaymentConstructor() {
        Payment payment = new Payment(new Money(5000, 0));
    }
>>>>>>> Stashed changes

    @Test
    void collectCashPayments() {}

    @Test
    void getCashPayment() {}

    @Test
    void getCardPayment() {}

    @Test
    void getPointsPayment() {}

}
