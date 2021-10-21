package inte.project;

//author Dan Jensen

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cash implements Comparable<Cash> {

    private int quantity;
    private final Money denomination;


    public Cash(Money denomination, int quantity) {
        if (denomination.getAmountOfCrown() <= 0) {
            throw new IllegalArgumentException("Denomination can't be less than or equal to zero.");
        }
        List<Integer> denominations = Arrays.asList(1, 5, 10, 20, 50, 100, 200, 500, 1000);
        if (!denominations.contains(denomination.getAmountOfCrown())) {
            throw new IllegalArgumentException("Invalid denomination.");
        }
        this.denomination = denomination;
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity can't be less than zero.");
        }
            this.quantity = quantity;
    }


    public Money getDenomination() {
        return this.denomination;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Money getTotal() {
        return new Money(this.denomination.getAmountOfCrown() * quantity, 0);
    }

    public int getTotalInOre() {
        return this.denomination.getAmountInOre() * quantity;
    }

    public void add(int quantity) {
        this.quantity += quantity;
    }

    public void remove(int quantity) {
        int cashAfterRemoval = this.quantity - quantity;
        if (cashAfterRemoval < 0) {
            throw new IllegalStateException("Negative cash not allowed.");
        }
            this.quantity = cashAfterRemoval;
    }


    public int compareTo(Cash other) {
        return this.denomination.compareTo(other.getDenomination());
    }


    public boolean equals(Object other) {
        if (other instanceof Cash) {
            Cash m = (Cash)other;
            return this.denomination.getAmountOfCrown() == m.getDenomination().getAmountOfCrown();
        }
            return false;
    }

    public int hashCode() {
        return Objects.hash(this.denomination.getAmountOfCrown() + this.quantity);
    }

    public String toString() {
        return getTotal().toString();
    }
}
