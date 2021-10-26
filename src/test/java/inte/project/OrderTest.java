package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    void returnCurrentPayment() {
        Product product = new Appliances("348723", "Fridge", new Money(100, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(100, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(100, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(100, 0));
        Order order = new Order(product, product1, product2, product3);
        Assertions.assertEquals(order.getCurrentTotal(), new Money(515, 0));
    }


        @Test
        void orderProductsWithInsufficientAmount() {
            Product product = new Appliances("348723", "Fridge", new Money(5000));
            Product product1 = new Appliances("347654", "Stove", new Money(4500));
            Product product2 = new Tele("341276", "Mobile", new Money(10000));
            Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
            Order order = new Order(product, product1, product2, product3);
            Money money = new Money(10, 0);
            Cash cash = new Cash(money, 1);
            Payment payment = new Payment(order.getCurrentTotal(), cash);
            Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
                order.payTotalForProducts(payment);
            });
            Assertions.assertEquals("Insufficient amount.", exception.getMessage());
        }


    @Test
    void orderProductsWithOnePayment() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Order order = new Order(product, product1, product2, product3);
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        DebitCard card = new DebitCard("Debitcard", person, new Money(100000000));
        Payment payment = new Payment(order.getCurrentTotal(), card);
        order.payTotalForProducts(payment);
        Assertions.assertEquals(order.getCurrentTotal(), payment.getPayment());
    }

    @Test
    void orderProductsWithCard() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(10000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Order order = new Order(product, product1, product2, product3);
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", "1993-6-5");
        GiftCard card1 = new GiftCard("Giftcard", person, new Money(100000000));
        Payment payment = new Payment(order.getCurrentTotal(), card1);
        order.payTotalForProducts(payment);
        Assertions.assertEquals(order.getCurrentTotal(), payment.getPayment());
    }

    @Test
    void orderProductsWithCash() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        Money money = new Money(1000, 0);
        Money money100 = new Money(100, 0);
        Money money50 = new Money(50, 0);
        Cash cash = new Cash(money, 5);
        Cash cash1 = new Cash(money100, 1);
        Cash cash2 = new Cash(money50, 1);
        Payment payment = new Payment(order.getCurrentTotal(), cash, cash1, cash2);
        order.payTotalForProducts(payment);
        Assertions.assertEquals(order.getCurrentTotal(), payment.getPayment());
    }

    @Test
    void orderProductsWithPoints() {
        Product product = new Appliances("348723", "Fridge", new Money(5000));
        Product product1 = new Appliances("347654", "Stove", new Money(4500));
        Product product2 = new Tele("341276", "Mobile", new Money(100000000));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1500));
        Order order = new Order(product, product1, product2, product3);
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(order.getCurrentTotal().getAmountInOre());
        Payment payment = new Payment(order.getCurrentTotal(), customer);
        order.payTotalForProducts(payment);
        Assertions.assertEquals(order.getCurrentTotal(), order.getCurrentPayment());
    }

    @Test
    void orderProductsWithSeparateMethods() {
        Product product = new Appliances("348723", "Fridge", new Money(2000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(2000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(2000, 0));
        Order order = new Order(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 20);
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(50000000);
        Payment payment = new Payment(new Money(5000, 0), customer);
        Payment payment2 = new Payment(new Money(4000, 0), cash);
        order.paySeparatelyForProducts(payment, payment2);
        Assertions.assertEquals(order.getCurrentTotal(), order.getCurrentPayment());
    }

    @Test
    void orderProductsWithSeparateMethodsGetInfo() {
        Product product = new Appliances("348723", "Fridge", new Money(2000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(2000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(2000, 0));
        Order order = new Order(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 20);
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(50000000);
        Payment payment = new Payment(new Money(5000, 0), customer);
        Payment payment2 = new Payment(new Money(4000, 0), cash);
        order.paySeparatelyForProducts(payment, payment2);
        Assertions.assertEquals("Order date: " + order.getDateOfOrder()
                + "\nPayment methods: [\n"+ customer.getMembership().getMembershipPoints() + "\nAmount paid: 5000:00 kr, \n"
                + "Cash:\n" + "Amount paid: 4000:00 kr]" + "\nProducts: [" +
                 "\n347654 Stove 2000:00 kr Appliances Store: null, " +
                 "\n346576 Mixer 2000:00 kr Household Store: null, " +
                 "\n341276 Mobile 1000:00 kr Tele Store: null, " +
                "\n348723 Fridge 2000:00 kr Appliances Store: null]" +
                 "\nTotal amount paid: " + "9000:00 kr"+
                "\nTotal discount amount: " + "0:00 kr"
                , order.getInfo());
    }

    @Test
    void orderProductsWithSeparateMethodsInsufficientAmount() {
        Product product = new Appliances("348723", "Fridge", new Money(2000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(2000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(2000, 0));
        Order order = new Order(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 10);
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(50000000);
        Payment payment = new Payment(new Money(5000, 0), customer);
        Payment payment2 = new Payment(new Money(1000, 0), cash);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.paySeparatelyForProducts(payment, payment2);
        });
        Assertions.assertEquals("Insufficient amount.", exception.getMessage());
    }

    @Test
    void applyDiscountPercentToAllOrders() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Order order = new Order(product);
        order.applyDiscountPercentToTotalOrder(0.25);
        Assertions.assertEquals(new Money(975, 0), order.getCurrentTotal());
    }

    @Test
    void applyDiscountPercentToProductType() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        order.applyDiscountPercentToProductType(1.00, "Appliances");
        Assertions.assertEquals(new Money(3150, 0), order.getCurrentTotal());
    }

    @Test
    void applyDiscountAmountToAllOrders() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        order.applyDiscountAmountToTotalOrder(new Money(2000, 0));
        Assertions.assertEquals(new Money(3150, 0), order.getCurrentTotal());
    }

    @Test
    void applyDiscountAmountToProductType() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        order.applyDiscountAmountToProductType(new Money(500, 0), "Appliances");
        Assertions.assertEquals(new Money(4150, 0), order.getCurrentTotal());
    }

    @Test
    void applyDiscountAmountToProductTypeThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.applyDiscountAmountToProductType(new Money(1500, 0), "Appliances");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void orderProductsWithSeparateMethodsAndProductTypeDiscountGetInfo() {
        Product product = new Appliances("348723", "Fridge", new Money(2000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(2000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(2000, 0));
        Order order = new Order(product, product1, product2, product3);
        Cash cash = new Cash(new Money(200, 0), 20);
        String dateOfBirth = "1999-04-03";
        Customer customer = new PrivatePerson("name", "address", "name@email.com", "6666666", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(50000000);
        Payment payment = new Payment(new Money(5000, 0), customer);
        order.applyDiscountAmountToProductType(new Money(500, 0), "Appliances");
        Payment payment2 = new Payment(new Money(4000, 0), cash);
        order.paySeparatelyForProducts(payment, payment2);
        Assertions.assertEquals("Order date: " + order.getDateOfOrder()
                        + "\nPayment methods: [\n"+ customer.getMembership().getMembershipPoints() + "\nAmount paid: 5000:00 kr, \n"
                        + "Cash:\n" + "Amount paid: 4000:00 kr]" + "\nProducts: [" +
                        "\n347654 Stove 2000:00 kr Appliances Store: null Discount: 500:00 kr, " +
                        "\n346576 Mixer 2000:00 kr Household Store: null, " +
                        "\n341276 Mobile 1000:00 kr Tele Store: null, " +
                        "\n348723 Fridge 2000:00 kr Appliances Store: null Discount: 500:00 kr]" +
                         "\nTotal amount paid: " + "8000:00 kr"+
                        "\nTotal discount amount: " + "1000:00 kr"
                , order.getInfo());
    }

    @Test
    void applyDiscountAmountToAllOrdersThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.applyDiscountAmountToTotalOrder(new Money(20000, 0));
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountPercentToAllOrdersThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Order order = new Order(product);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.applyDiscountPercentToTotalOrder(1.50);
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountPercentToProductTypeThrows() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.applyDiscountPercentToProductType(1.50, "Appliances");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountPercentToProductThrowsNegativeDiscount() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.applyDiscountPercentToProduct(1.50, "348723");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }

    @Test
    void applyDiscountAmountToProductThrowsNegativeDiscount() {
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Order order = new Order(product, product1, product2, product3);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            order.applyDiscountAmountToProduct(new Money(1500, 0), "348723");
        });
        Assertions.assertEquals("Discount causes price increase.", exception.getMessage());
    }


}
