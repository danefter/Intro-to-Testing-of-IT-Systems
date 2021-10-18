//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterTest {

    public RegisterTest() {}

    @Test
    void calculateCurrentTotalWorks() {
        Register r = new Register();
        Product fridge = new Appliances("123456", "fridgy", 2000);
        Product boat = new HouseHold("123457", "boaty", 3000);
        Product cow = new Tele("123458", "mooey", 6000);
        r.calculateCurrentTotal(fridge, boat, cow);
        Assertions.assertEquals(11000, r.getCurrentTotal());
    }

    @Test
    void receiveCashPaymentShowsOnCashPaymentTotal() {
        Register r = new Register();
        Product fridge = new Appliances("123456", "fridgy", 2000);
        Product boat = new HouseHold("123457", "boaty", 3000);
        Product cow = new Tele("123458", "mooey", 6000);
        CashTransaction payment = new CashTransaction(new Cash(1000, 11));
        r.calculateCurrentTotal(fridge, boat, cow);
        r.receiveCashPayment(payment);
        Assertions.assertEquals(11000, r.getCashPaymentTotal());
    }

    @Test
    void totalsResetAfterCashTransaction() {
        Register r = new Register();
        Product fridge = new Appliances("123456", "fridgy", 2000);
        Product boat = new HouseHold("123457", "boaty", 3000);
        Product cow = new Tele("123458", "mooey", 6000);
        CashTransaction payment = new CashTransaction(new Cash(1000, 11));
        r.calculateCurrentTotal(fridge, boat, cow);
        r.receiveCashPayment(payment);
        Assertions.assertEquals(0, r.getCurrentTotal());
    }
}
