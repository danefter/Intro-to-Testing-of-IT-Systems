package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurchaseTest {

    @Test
    void returnCurrentPayment(){
        Product product = new Appliances("348723", "Fridge", new Money(100, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(100, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(100, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(100, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Assertions.assertEquals(purchase.getCurrentTotal(), new Money(400, 0));
    }

    @Test
    void purchaseProductsWithOnePayment() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        Payment payment = new Payment(purchase.getCurrentTotal(), card);
        purchase.payTotalForProducts(payment);
        Assertions.assertEquals(purchase.getCurrentTotal(), payment.getPayment());
    }

    @Test
    void purchaseProductsWithMultipleCards() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        GiftCard card1 = new GiftCard("Giftcard", person, new Money(100000000));
        Payment payment = new Payment(purchase.getCurrentTotal(), card, card1);
        purchase.payTotalForProducts(payment);
        Assertions.assertEquals(purchase.getCurrentTotal(), payment.getPayment());
    }

    @Test
    void purchaseProductsWithCash() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Money money = new Money(1000, 0);
        Cash cash = new Cash(money, 2);
        Cash cash1 = new Cash(money, 2);
        Payment payment = new Payment(purchase.getCurrentTotal(), cash, cash1);
        purchase.payTotalForProducts(payment);
        Assertions.assertEquals(purchase.getCurrentTotal(), payment.getPayment());
    }

    @Test
    void purchaseProductsWithPoints() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(100000000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        MembershipPoints points = new MembershipPoints(purchase.getCurrentTotal().getAmountInOre());
        Payment payment = new Payment(purchase.getCurrentTotal(), points);
        purchase.payTotalForProducts(payment);
        Assertions.assertEquals(purchase.getCurrentTotal(), purchase.getCurrentPayment());
    }

    @Test
    void purchaseProductsWithSeparateMethods() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 10);
        MembershipPoints points = new MembershipPoints(5000);
        Payment payment = new Payment(new Money(5000), points);
        Payment payment2 = new Payment(new Money(2000), cash);
        purchase.paySeparatelyForProducts(payment, payment2);
        Assertions.assertEquals(purchase.getCurrentTotal(), purchase.getCurrentPayment());
    }


}
