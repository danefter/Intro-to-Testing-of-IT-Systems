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
        Customer customer = new Customer("name", "address", "name@email.com", "6666666");
        Card card = new Card("debit", customer, 5000);
        Assertions.assertEquals("debit", card.getCardType());
    }

    @Test
    void constructorSetsCardOwner() {
        Customer customer = new Customer("name", "address", "name@email.com", "6666666");
        Card card = new Card("debit", customer, 5000);
        Assertions.assertEquals(customer, card.getCardOwner());
    }

    @Test
    void constructorSetsBalance() {
        Customer customer = new Customer("name", "address", "name@email.com", "6666666");
        Card card = new Card("debit", customer, 5000);
        Assertions.assertEquals(5000, card.getBalance());
    }

    @Test
    void payMethodThrowsInsufficientBalance() {
        Customer customer = new Customer("name", "address", "name@email.com", "6666666");
        Card card = new Card("debit", customer, 5000);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            card.pay(6000);
        });
        Assertions.assertEquals("Insufficient balance.", exception.getMessage());
    }
}
