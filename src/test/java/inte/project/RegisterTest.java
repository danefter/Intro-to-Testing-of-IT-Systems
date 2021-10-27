package inte.project;
//author Dan Jensen
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RegisterTest {

    public RegisterTest() {
    }

    @Test
    void denominationsConstructed() {
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Register register = new Register(store);
        List<Cash> denominations = Arrays.asList(
                new Cash(new Money(1, 0), 0),
                new Cash(new Money(5, 0), 0),
                new Cash(new Money(10, 0), 0),
                new Cash(new Money(20, 0), 0),
                new Cash(new Money(50, 0), 0),
                new Cash(new Money(100, 0), 0),
                new Cash(new Money(200, 0), 0),
                new Cash(new Money(500, 0), 0),
                new Cash(new Money(1000, 0), 0));
        Assertions.assertTrue(register.getCashBalance().containsAll(denominations));
    }

    @Test
    void makeOrderWithDesiredPaymentMethods() {
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        Money money = new Money(1000, 0);
        Money money100 = new Money(100, 0);
        Money money50 = new Money(50, 0);
        Cash cash = new Cash(money, 5);
        Cash cash1 = new Cash(money100, 1);
        Cash cash2 = new Cash(money50, 1);
        Payment payment = new Payment(order.getCurrentTotal(), cash, cash1, cash2);
        register.payForOrderTF1TF4(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    @Test
    void makeOrderCancelPurchaseMethods() {
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Collection<Product> productsBefore = register.getInventory();
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.cancelOrderAfterScan(order);
        Assertions.assertIterableEquals(productsBefore, register.getInventory());
    }

    @Test
    void presentTotal() {
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Collection<Product> productsBefore = register.getInventory();
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotal(order);
    }


    @Test
    public void tf1() {
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotal(order);
        Money money = new Money(1000, 0);
        Money money100 = new Money(100, 0);
        Money money50 = new Money(50, 0);
        Cash cash = new Cash(money, 5);
        Cash cash1 = new Cash(money100, 1);
        Cash cash2 = new Cash(money50, 1);
        Payment payment = new Payment(order.getCurrentTotal(), cash, cash1, cash2);
        register.payForOrderTF1TF4(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    @Test
    public void tf2() {
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotal(order);
        String dateOfBirth = "1973-04-11";
        Customer customer = new PrivatePerson("Benny Björk", "Sveagatan 43", "bjorn_gunnarsson@gmail.com", "0734778559", dateOfBirth);
        GiftCard giftCard = new GiftCard("Giftcard", customer, new Money(10000, 0));
        customer.setGiftCard(giftCard);
        Payment payment = register.getPaymentInputGiftCard(order, order.getCurrentTotal().getAmountOfCrown(), customer);
        register.payForOrderTF2(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    @Test
    public void tf3() {
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotal(order);
        String dateOfBirth = "1973-04-11";
        Customer customer = new PrivatePerson("Benny Björk", "Sveagatan 43", "bjorn_gunnarsson@gmail.com", "0734778559", dateOfBirth);
        GiftCard giftCard = new GiftCard("Giftcard", customer, new Money(0));
        customer.setGiftCard(giftCard);
        DebitCard debitCard = new DebitCard("Debitcard", customer, new Money(10000, 0));
        customer.setDebitCard(debitCard);
        customer.addMembership();
        Payment payment = register.getPaymentInputGiftCard(order, order.getCurrentTotal().getAmountOfCrown(), customer);
        register.payForOrderTF3(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    @Test
    public void tf4(){
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotal(order);
        String dateOfBirth = "1973-04-11";
        Customer customer = new PrivatePerson("Benny Björk", "Sveagatan 43", "bjorn_gunnarsson@gmail.com", "0734778559", dateOfBirth);
        customer.addMembership();
        customer.getMembership().getMembershipPoints().addPoints(10000000);
        Payment payment = register.getPaymentInputPoints(order, order.getCurrentTotal().getAmountOfCrown(), customer);
        register.payForOrderTF1TF4(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    @Test
    public void tf5(){
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        Money money = new Money(1000, 0);
        Money money100 = new Money(100, 0);
        Money money50 = new Money(50, 0);
        Cash cash = new Cash(money, 5);
        Cash cash1 = new Cash(money100, 1);
        Cash cash2 = new Cash(money50, 1);
        Payment payment = new Payment(order.getCurrentTotal(), cash, cash1, cash2);
        register.cancelOrderAfterScan(order);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    @Test
    public void tf6(){
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotalCancel(order);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    @Test
    public void tf7(){
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotal(order);
        String dateOfBirth = "1973-04-11";
        Customer customer = new PrivatePerson("Benny Björk", "Sveagatan 43", "bjorn_gunnarsson@gmail.com", "0734778559", dateOfBirth);
        customer.addMembership();
        Money money = new Money(1000, 0);
        Money money100 = new Money(100, 0);
        Money money50 = new Money(50, 0);
        Cash cash = new Cash(money, 5);
        Cash cash1 = new Cash(money100, 1);
        Cash cash2 = new Cash(money50, 1);
        Payment payment = new Payment(order.getCurrentTotal(), cash, cash1, cash2);
        register.payForOrderTF7TF8(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }
    @Test
    public void tf8(){
        Store store = new Store("Vasagata 12", 12456, "Stockholm", "0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Order order = register.scanProductsForOrder(product, product1, product2, product3);
        register.presentTotal(order);
        String dateOfBirth = "1973-04-11";
        Customer customer = new PrivatePerson("Benny Björk", "Sveagatan 43", "bjorn_gunnarsson@gmail.com", "0734778559", dateOfBirth);
        customer.addMembership();
        Money money = new Money(1000, 0);
        Money money100 = new Money(100, 0);
        Money money50 = new Money(50, 0);
        Cash cash = new Cash(money, 5);
        Cash cash1 = new Cash(money100, 1);
        Cash cash2 = new Cash(money50, 1);
        Payment payment = new Payment(order.getCurrentTotal(), cash, cash1, cash2);
        register.payForOrderTF7TF8(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

}
