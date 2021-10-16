//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

public class CardTransaction {
    private final Card card;

    public CardTransaction(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }

    public void getPayment(int amount) {
        this.card.pay(amount);
    }

    public void returnPayment(int amount) {
    }
}
