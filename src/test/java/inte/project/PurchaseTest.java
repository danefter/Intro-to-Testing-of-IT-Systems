package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurchaseTest {

    @Test
    void returnCurrentPayment() {
        Product product = new Appliances("348723", "Fridge", new Money(100, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(100, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(100, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(100, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Assertions.assertEquals(purchase.getCurrentTotal(), new Money(400, 0));
    }


        @Test
        void purchaseProductsWithInsufficientAmount() {
            Product product = new Appliances("348723", "Fridge", new Money(5000));
            Product product1 = new Appliances("347654", "Stove", new Money(4500));
            Product product2 = new Tele("341276", "Mobile", new Money(10000));
            Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
            Purchase purchase = new Purchase(product, product1, product2, product3);
            Money money = new Money(10, 0);
            Cash cash = new Cash(money, 1);
            Payment payment = new Payment(purchase.getCurrentTotal(), cash);
            Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
                purchase.payTotalForProducts(payment);
            });
            Assertions.assertEquals("Insufficient amount.", exception.getMessage());
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
    void purchaseProductsWithCard() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        GiftCard card1 = new GiftCard("Giftcard", person, new Money(100000000));
        Payment payment = new Payment(purchase.getCurrentTotal(), card1);
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
        Product product = new Appliances("348723", "Fridge", new Money(2000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(2000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(2000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 20);
        MembershipPoints points = new MembershipPoints(5000000);
        Payment payment = new Payment(new Money(5000, 0), points);
        Payment payment2 = new Payment(new Money(4000, 0), cash);
        purchase.paySeparatelyForProducts(payment, payment2);
        Assertions.assertEquals(purchase.getCurrentTotal(), purchase.getCurrentPayment());
    }

    @Test
    void purchaseProductsWithSeparateMethodsToString() {
        Product product = new Appliances("348723", "Fridge", new Money(2000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(2000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(2000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 20);
        MembershipPoints points = new MembershipPoints(500000);
        Payment payment = new Payment(new Money(5000, 0), points);
        Payment payment2 = new Payment(new Money(4000, 0), cash);
        purchase.paySeparatelyForProducts(payment, payment2);
        Assertions.assertEquals("Purchase date: " + purchase.getDateOfPurchase()
                + "\nPayment methods: [\n"+ points + "\nAmount paid: 5000:00 kr, \n"
                + "Cash:\n" + "Amount paid: 4000:00 kr]"
                , purchase.toString());
    }

    @Test
    void purchaseProductsWithSeparateMethodsInsufficientAmount() {
        Product product = new Appliances("348723", "Fridge", new Money(2000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(2000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(2000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 10);
        MembershipPoints points = new MembershipPoints(500000);
        Payment payment = new Payment(new Money(5000, 0), points);
        Payment payment2 = new Payment(new Money(1000, 0), cash);
        purchase.paySeparatelyForProducts(payment, payment2);
        Assertions.assertEquals(purchase.getCurrentTotal(), purchase.getCurrentPayment());
    }

    @Test
    void applyDiscountPercentToAllPurchases() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Purchase purchase = new Purchase(product);
        purchase.applyDiscountPercentToAllPurchases(0.25);
        Assertions.assertEquals(new Money(750, 0), purchase.getCurrentTotal());
    }

    @Test
    void applyDiscountPercentToProductType() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        purchase.applyDiscountPercentToProductType(1.00, "Appliances");
        Assertions.assertEquals(new Money(2000, 0), purchase.getCurrentTotal());
    }

    @Test
    void applyDiscountAmountToAllPurchases() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        purchase.applyDiscountAmountToAllPurchases(new Money(2000, 0));
        Assertions.assertEquals(new Money(2000, 0), purchase.getCurrentTotal());
    }

    @Test
    void applyDiscountAmountToProductType() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        purchase.applyDiscountAmountToProductType(new Money(500, 0), "Appliances");
        Assertions.assertEquals(new Money(3000, 0), purchase.getCurrentTotal());
    }

    @Test
    void applyDiscountAmountToProductTypeThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            purchase.applyDiscountAmountToProductType(new Money(1500, 0), "Appliances");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountAmountToAllPurchasesThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            purchase.applyDiscountAmountToAllPurchases(new Money(20000, 0));
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountPercentToAllPurchasesThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Purchase purchase = new Purchase(product);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            purchase.applyDiscountPercentToAllPurchases(1.50);
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountPercentToProductTypeThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            purchase.applyDiscountPercentToProductType(1.50, "Appliances");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountPercentToProductThrowsNegativeDiscount() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            purchase.applyDiscountPercentToProduct(1.50, "348723");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountAmountToProductThrowsNegativeDiscount() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Purchase purchase = new Purchase(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            purchase.applyDiscountAmountToProduct(new Money(1500, 0), "348723");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }


}
