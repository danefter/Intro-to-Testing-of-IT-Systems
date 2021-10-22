package inte.project;
//author Dan Jensen
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class RegisterTest {

    public RegisterTest() {}

    @Test
    void makeChange() {
        Register register = new Register(new Store("Vasagata 12",12456,"Stockholm","0706524324"));
        ArrayList<Cash> cash = new ArrayList<>();

        Assertions.assertEquals(register.giveChange(1000), register.giveChange(1150));
    }
    @Test
    void registerMember() {
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

}
