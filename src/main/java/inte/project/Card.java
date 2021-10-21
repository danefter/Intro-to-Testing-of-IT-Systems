package inte.project;

//author Dan Jensen

public abstract class Card {
    private final String cardType;
    private final Customer cardOwner;
    private Money balance;

    public Card(String cardType, Customer cardOwner, Money balance) {
        this.cardType = cardType;
        this.cardOwner = cardOwner;
        this.balance = balance;
    }

    public String getBalanceAmount() {
        return this.balance.toString();
    }

    public Customer getCardOwner() {
        return this.cardOwner;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void addBalance(Money money) {
        this.balance = balance.add(money);
    }

    public void subtractBalance(Money money) {
        this.balance = balance.subtract(money);
    }

    public Money pay(Money amount) {
        if (this.balance.getAmountOfCrown() < amount.getAmountOfCrown()) {
            throw new IllegalStateException("Insufficient balance.");
        }
        this.subtractBalance(amount);
        return amount;
    }


    public String toString() {
        return cardType + ": " + cardOwner.getName();
    }
}
