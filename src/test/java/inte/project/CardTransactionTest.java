//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTransactionTest {
    public CardTransactionTest() {
    }

    @Test
    void constructorCreatesCard() {
        int yearOfBirth = 1999;
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", yearOfBirth);
        Card card = new Card("debit", customer, 5000);
        CardTransaction cardTransaction = new CardTransaction(card);
        Assertions.assertEquals(card, cardTransaction.getCard());
    }

    @Test
    void getPaymentGetsPayment() {
        String orgNumber = "675522-7899";
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new Card("debit", customer, 5000);
        CardTransaction cardTransaction = new CardTransaction(card);
        cardTransaction.getPayment(2000);
        Assertions.assertEquals(3000, card.getBalance());
    }

    @Test
    void getPaymentMethodStillThrowsInsufficientBalance() {
        int yearOfBirth = 1990;
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", yearOfBirth);
        Card card = new Card("debit", customer, 5000);
        CardTransaction cardTransaction = new CardTransaction(card);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            cardTransaction.getPayment(6000);
        });
        Assertions.assertEquals("Insufficient balance.", exception.getMessage());
    }
}
