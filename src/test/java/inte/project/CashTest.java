//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CashTest {
    public CashTest() {
    }

    @Test
    void constructorWorksWithValidDenomination() {
        Cash cash = new Cash(20, 1);
        Assertions.assertEquals(20, cash.getDenomination());
    }

    @Test
    void constructorWorksWithValidQuantity() {
        Cash cash = new Cash(20, 1);
        Assertions.assertEquals(1, cash.getQuantity());
    }

    @Test
    void constructorThrowsInvalidDenomination() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(21, 1);
        });
        Assertions.assertEquals("Invalid denomination.", exception.getMessage());
    }

    @Test
    void constructorThrowsInvalidDenominationEqualToZero() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(0, 1);
        });
        Assertions.assertEquals("Denomination can't be less than or equal to zero.", exception.getMessage());
    }

    @Test
    void constructorThrowsInvalidDenominationLessThanZero() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(-1, 1);
        });
        Assertions.assertEquals("Denomination can't be less than or equal to zero.", exception.getMessage());
    }

    @Test
    void constructorThrowsInvalidQuantityLessThanZero() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(20, -1);
        });
        Assertions.assertEquals("Quantity can't be less than zero.", exception.getMessage());
    }

    @Test
    void addChangesQuantityCorrectly() {
        Cash cash = new Cash(20, 1);
        cash.add(1);
        Assertions.assertEquals(2, cash.getQuantity());
    }

    @Test
    void addChangesTotalCorrectly() {
        Cash cash = new Cash(20, 1);
        cash.add(1);
        Assertions.assertEquals(40, cash.getTotal());
    }

    @Test
    void removeChangesQuantityCorrectly() {
        Cash cash = new Cash(20, 1);
        cash.remove(1);
        Assertions.assertEquals(0, cash.getQuantity());
    }

    @Test
    void removeChangesTotalCorrectly() {
        Cash cash = new Cash(20, 1);
        cash.remove(1);
        Assertions.assertEquals(0, cash.getTotal());
    }

    @Test
    void removeThrowsNegativeCash() {
        Cash cash = new Cash(20, 1);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            cash.remove(2);
        });
        Assertions.assertEquals("Negative cash not allowed.", exception.getMessage());
    }

    @Test
    void equalsTrueForSameDenomination() {
        Cash cash1 = new Cash(20, 1);
        Cash cash2 = new Cash(20, 1);
        Assertions.assertEquals(cash1, cash2);
    }

    @Test
    void equalsFalseForNotSameDenomination() {
        Cash cash1 = new Cash(50, 1);
        Cash cash2 = new Cash(20, 1);
        Assertions.assertNotEquals(cash1, cash2);
    }

    @Test
    void equalsFalseForOtherObject() {
        Cash cash = new Cash(20, 1);
        int yearOfBirth = 1980;
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", yearOfBirth);
        Card card = new Card("debit", customer, 5000);
        Assertions.assertNotEquals(cash, card);
    }
}
