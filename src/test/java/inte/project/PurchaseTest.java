//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import org.junit.jupiter.api.Test;

public class PurchaseTest {
    public PurchaseTest() {
    }

    @Test
    void purchaseProductsWithOnePayment() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(new Product[]{product, product1, product2, product3});
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        Payment payment = new Payment(purchase.getCurrentTotal(), new Card[]{card});
        purchase.payTotalForProducts(payment);
    }

    @Test
    void purchaseProductsWithMultipleCards() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(new Product[]{product, product1, product2, product3});
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        GiftCard card1 = new GiftCard("Giftcard", person, new Money(100000000));
        Payment payment = new Payment(purchase.getCurrentTotal(), new Card[]{card, card1});
        purchase.payTotalForProducts(payment);
    }

    @Test
    void purchaseProductsWithCash() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(new Product[]{product, product1, product2, product3});
        Cash cash = new Cash(20, 2000);
        Cash cash1 = new Cash(20, 5000);
        Payment payment = new Payment(purchase.getCurrentTotal(), new Cash[]{cash, cash1});
        purchase.payTotalForProducts(payment);
    }
}
