package inte.project;

public interface Discount {


    default double applyDiscountPercent(double percent) {
        return percent;
    }

    default Money applyDiscountAmount(Money amount) {
        return amount;
    }
}
