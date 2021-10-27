
package inte.project;

//author Dan Jensen

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CardTest {

    @Test
    void constructorOfSuper() {
            String dateOfBirth = "1999-04-03";
            Money balance = new Money(5000, 0);
            Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
            Card card = new Card("Giftcard", customer, balance);
            Assertions.assertEquals("Giftcard", card.getCardType());
        }

    @Test
    void constructorSetsGiftCardType() {
        String dateOfBirth = "1999-04-03";
        Money balance = new Money(5000, 0);
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        Card card = new GiftCard("Giftcard", customer, balance);
        Assertions.assertEquals("Giftcard", card.getCardType());
    }

    @Test
    void constructorSetsDebitCardType() {
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
        Assertions.assertEquals(balance, card.getBalance());
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

    @Test
    void debitToStringTest() {
        String orgNumber = "789765-8765";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals( "Debitcard: name", card.toString());
    }

    @Test
    void giftToStringTest() {
        String orgNumber = "789765-8765";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new GiftCard("Giftcard", customer, balance);
        Assertions.assertEquals("Giftcard: name, Balance: 5000:00 kr", card.toString());
    }

    @Test
    void payMethodReturnsFunds() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Money payment = new Money(1000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals(payment, card.pay(payment));
    }

    @Test
    void getBalance() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals(balance, card.getBalance());
    }

    @Test
    void addBalance() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Money payment = new Money(1000, 0);
        Money correctBalance = new Money(6000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        card.addBalance(payment);
        Assertions.assertEquals(correctBalance, card.getBalance());
    }

    @Test
    void subtractBalance() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Money payment = new Money(1000, 0);
        Money correctBalance = new Money(4000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        card.subtractBalance(payment);
        Assertions.assertEquals(correctBalance, card.getBalance());
    }

    @Test
    void getCardType() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals("Debitcard", card.getCardType());
    }

    @Test
    void getCardTypeGift() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new GiftCard("Giftcard", customer, balance);
        Assertions.assertEquals("Giftcard", card.getCardType());
    }

    @Test
    void getBalanceAmount() {
        String orgNumber = "345268-5798";
        Money balance = new Money(5000, 0);
        Customer customer = new Company("name", "address", "name@email.com", "6666666", orgNumber);
        Card card = new DebitCard("Debitcard", customer, balance);
        Assertions.assertEquals(balance, card.getBalance());
    }

}
