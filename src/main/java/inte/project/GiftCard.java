package inte.project;
//author Dan Jensen

import java.text.SimpleDateFormat;
import java.util.Date;
//author Dan Jensen
public class GiftCard extends Card{

    String dateOfIssue;

    public GiftCard(String cardType, Customer cardOwner, Money balance) {
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
