package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCases {

    public TestCases() {}

    public void tf1(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public void tf2(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public void tf3(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public void tf4(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public void tf5(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public void tf6(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public void tf7(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public void tf8(){
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
        register.payForOrder(order, payment);
        Assertions.assertEquals(order.getCurrentPayment(), order.getCurrentTotal());
    }

    public static void main(String[] args) {
        TestCases tf = new TestCases();
        tf.tf1();
    }
}
