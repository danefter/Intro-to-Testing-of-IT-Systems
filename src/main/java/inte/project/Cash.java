package inte.project;

//author Dan Jensen

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cash implements Comparable<Cash> {
    private final Money denomination;
    private int quantity;

    public Cash(int denomination, int quantity) {
        if (denomination <= 0) {
            throw new IllegalArgumentException("Denomination can't be less than or equal to zero.");
        }
        List<Integer> denominations = Arrays.asList(1, 5, 10, 20, 50, 100, 200, 500, 1000);
        if (!denominations.contains(denomination)) {
            throw new IllegalArgumentException("Invalid denomination.");
        }
        this.denomination = new Money(denomination, 0);
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity can't be less than zero.");
        }
            this.quantity = quantity;
        }


    public int getDenomination() {
        return this.denomination.getAmountOfCrown();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Money getTotal() {
        return (new Money(this.denomination.getAmountOfCrown() * this.quantity));
    }

    public Money getTotalInOre() {
        return (new Money(this.denomination.getAmountOfCrown() * this.quantity * 100));
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
        return this.denomination.getAmountOfCrown() - other.denomination.getAmountOfCrown();
    }

    public boolean equals(Object other) {
        if (other instanceof Cash) {
            Cash m = (Cash)other;
            return this.denomination.getAmountOfCrown() == m.denomination.getAmountOfCrown();
        }
            return false;
    }

    public int hashCode() {
        return Objects.hash(this.denomination.getAmountOfCrown() + this.quantity);
    }
}
