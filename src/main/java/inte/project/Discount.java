package inte.project;

public class Discount {

    double percentage = 1.00;

    Money amount = new Money(0);

    public Discount(double percentage) {
        this.percentage = percentage;
    }

    public Discount(Money amount) {
        this.amount = amount;
    }

}
