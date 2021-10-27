
package inte.project;
//author Dan Jensen

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class CashTest {
    public CashTest() {
    }

    @Test
    void constructorWorksWithValidDenomination() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        Assertions.assertEquals(money, cash.getDenomination());
    }

    @Test
    void constructorWorksWithValidQuantity() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        Assertions.assertEquals(1, cash.getQuantity());
    }

    @Test
    void constructorThrowsInvalidDenomination() {
        Money money = new Money(21, 0);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(money, 1);
        });
        Assertions.assertEquals("Invalid denomination.", exception.getMessage());
    }

    @Test
    void constructorThrowsInvalidDenominationEqualToZero() {
        Money money = new Money(0);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(money, 1);
        });
        Assertions.assertEquals("Denomination can't be less than or equal to zero.", exception.getMessage());
    }

    @Test
    void constructorThrowsInvalidDenominationLessThanZero() {
        Money money = new Money(-1);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(money, 1);
        });
        Assertions.assertEquals("Denomination can't be less than or equal to zero.", exception.getMessage());
    }

    @Test
    void constructorThrowsInvalidQuantityLessThanZero() {
        Money money = new Money(20, 0);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cash(money, -1);
        });
        Assertions.assertEquals("Quantity can't be less than zero.", exception.getMessage());
    }

    @Test
    void addChangesQuantityCorrectly() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        cash.add(1);
        Assertions.assertEquals(2, cash.getQuantity());
    }

    @Test
    void addChangesTotalCorrectly() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        cash.add(1);
        Assertions.assertEquals(new Money(40, 0), cash.getTotal());
    }

    @Test
    void removeChangesQuantityCorrectly() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        cash.remove(1);
        Assertions.assertEquals(0, cash.getQuantity());
    }

    @Test
    void removeChangesTotalCorrectly() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        cash.remove(1);
        Assertions.assertEquals(new Money(0), cash.getTotal());
    }

    @Test
    void removeThrowsNegativeCash() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            cash.remove(2);
        });
        Assertions.assertEquals("Negative cash not allowed.", exception.getMessage());
    }

    @Test
    void equalsTrueForSameDenomination() {
        Money money = new Money(20, 0);
        Cash cash1 = new Cash(money, 1);
        Cash cash2 = new Cash(money, 1);
        Assertions.assertEquals(cash1, cash2);
    }

    @Test
    void equalsFalseForNotSameDenomination() {
        Money money = new Money(20, 0);
        Money money2 = new Money(50, 0);
        Cash cash1 = new Cash(money2, 1);
        Cash cash2 = new Cash(money, 1);
        Assertions.assertNotEquals(cash1, cash2);
    }
    @Test
    void hashCodeWorks() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        Assertions.assertEquals(Objects.hash(cash.getDenomination().getAmountOfCrown() + cash.getQuantity()),cash.hashCode());
    }
    @Test
    void toStringTotal() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        Assertions.assertEquals("20:00 kr", cash.toString() );
    }

    @Test
    void getTotalInOre() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        Assertions.assertEquals(2000, cash.getTotalInOre());
    }
    @Test
    void compareCash() {
        Money money = new Money(20, 0);
        Cash cash = new Cash(money, 1);
        Assertions.assertEquals(0, cash.compareTo(cash));
    }


}
