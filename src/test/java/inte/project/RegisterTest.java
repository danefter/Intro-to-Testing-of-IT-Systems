package inte.project;
//author Dan Jensen
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RegisterTest {

    public RegisterTest() {}

    @Test
    void denominationsConstructed() {
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
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
    void makePurchaseWithDesiredPaymentMethods(){
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        Product product = new Appliances("348723", "Fridge", new Money(1000, 0));
        Product product1 = new Appliances("347654", "Stove", new Money(1000, 0));
        Product product2 = new Tele("341276", "Mobile", new Money(1000, 0));
        Product product3 = new HouseHold("346576", "Mixer", new Money(1000, 0));
        Register register = new Register(store, product, product1, product2, product3);
        Purchase purchase = register.scanProductsForPurchase(product, product1, product2, product3);
        Money money = new Money(1000, 0);
        Money money100 = new Money(100, 0);
        Money money50 = new Money(50, 0);
        Cash cash = new Cash(money, 5);
        Cash cash1 = new Cash(money100, 1);
        Cash cash2 = new Cash(money50, 1);
        Payment payment = new Payment(purchase.getCurrentTotal(), cash, cash1, cash2);
        register.makePurchase(purchase, payment);
        Assertions.assertEquals(purchase.getCurrentPayment(), register.getTotalBalance());
    }
    @Test
    void unregisteredCustomerBecomesMemberAndCompletesPurchase() {
        Register register = new Register(new Store("Vasagata 12",12456,"Stockholm","0706524324"));
        ArrayList<Cash> cash = new ArrayList<>();
        CustomerHandler ch = new CustomerHandler();
        // TODO Registrerar varor
        // TODO Inga rabatter
        // TODO Presentera summan
        // Vill bli medlem
        Customer customer = new PrivatePerson("Namn", "address", "email", "0707374653","1995-03-35");
        customer.addMembership();
        // TODO Skickar summan till getPointsForPurchase(double costOfPurchase)
        // TODO adderar summan till kundens membership med customer.getMembership.getMembershippoints.addPoints(double points)
        ch.addCustomer(customer);
        // TODO Betalar
        // TODO Skriver kvitto
    }

    @Test
    void addCashPaymentToRegisterAddsCash(){}

    @Test
    void addToDailyReportsLogsPurchases(){}

    @Test
    void printReceipt(){}

}
