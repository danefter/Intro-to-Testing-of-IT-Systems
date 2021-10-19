package inte.project;

public class DebitCard extends Card{
    public DebitCard(String cardType, Customer cardOwner, Money balance) {
        super(cardType, cardOwner, balance);
    }

    @Override
    public String getCardType() {
        return "Debitcard";
    }
}
