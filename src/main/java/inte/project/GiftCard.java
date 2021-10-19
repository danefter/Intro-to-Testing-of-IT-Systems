package inte.project;


import java.text.SimpleDateFormat;
import java.util.Date;

public class GiftCard extends Card{

    String dateOfIssue;

    public GiftCard(String cardType, Customer cardOwner, int balance) {
        super(cardType, cardOwner, balance);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        dateOfIssue = formatter.format(today);
    }

    @Override
    public String getCardType() {
        return "Giftcard";
    }
}