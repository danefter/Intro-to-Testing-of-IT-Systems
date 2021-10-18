//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    public CardTest() {
    }

    @Test
    void constructorSetsCardType() {
        int yearOfBirth = 1999;
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", yearOfBirth);
        Card card = new Card("debit", customer, 5000);
        Assertions.assertEquals("debit", card.getCardType());
    }

    @Test
    void constructorSetsCardOwner() {
        String orgNumber = "789765-8765";
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new Card("debit", customer, 5000);
        Assertions.assertEquals(customer, card.getCardOwner());
    }

    @Test
    void constructorSetsBalance() {
        int yearOfBirth = 2000;
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", yearOfBirth);
        Card card = new Card("debit", customer, 5000);
        Assertions.assertEquals(5000, card.getBalance());
    }

    @Test
    void payMethodThrowsInsufficientBalance() {
        String orgNumber = "345268-5798";
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new Card("debit", customer, 5000);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            card.pay(6000);
        });
        Assertions.assertEquals("Insufficient balance.", exception.getMessage());
    }
}
