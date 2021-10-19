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
        String dateOfBirth = "1999-04-03";
        Money balance = new Money(5000, 0);
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals("Debitcard", card.getCardType());
    }

    @Test
    void constructorSetsCardOwner() {
        String orgNumber = "789765-8765";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals(customer, card.getCardOwner());
    }

    @Test
    void constructorSetsBalance() {
        String dateOfBirth = "2000-05-12";
        Money balance = new Money(5000, 0);
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals(5000, card.getBalanceAmount());
    }

    @Test
    void payMethodThrowsInsufficientBalance() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            card.pay(new Money(6000, 0));
        });
        Assertions.assertEquals("Insufficient balance.", exception.getMessage());
    }


}
