package inte.project;

public class Card {
    private final String cardType;
    private final Customer cardOwner;
    private int balance;

    public Card(String cardType, Customer cardOwner, int balance) {
        this.cardType = cardType;
        this.cardOwner = cardOwner;
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public Customer getCardOwner() {
        return this.cardOwner;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void pay(int amount) {
        if (this.balance < amount) {
            throw new IllegalStateException("Insufficient balance.");
        }
        this.setBalance(this.balance - amount);
    }

    public void receive(int amount) {
    }
}
