package inte.project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void toStringPresentsAmount(){
        Money first = new Money(2300);
        assertEquals("23:00 kr", first.toString());
    }

    @Test
    void toStringWithOre(){
        Money first = new Money(23,15);
        assertEquals("23:15 kr", first.toString());
    }

    @Test
    void fiveIsLessThanSix(){
        Money five = new Money(500);
        Money six = new Money(6,0);
        assertEquals(-100, five.compareTo(six));
    }
    @Test
    void fiveIsMoreThanThreeFifty(){
        Money five = new Money(500);
        Money threeFifty = new Money(3,50);
        assertEquals(150, five.compareTo(threeFifty));
    }

    @Test
    void fiveIsFive(){
        Money five = new Money(500);
        assertEquals(500, five.getAmountInOre());
    }

    @Test
    void addFiveAndSix(){
        Money five = new Money(500);
        Money eleven = five.add(600);
        assertEquals(1100, eleven.getAmountInOre());
    }

    @Test
    void addTwoMoneyObjects(){
        Money two = new Money(200);
        Money twoFifty = new Money(2,50);
        assertEquals("4:50 kr", two.add(twoFifty).toString());
    }
    @Test
    void subtractFiveFromSix(){
        Money six = new Money(600);
        assertEquals("1:00 kr", six.subtract(500).toString());
    }
    @Test
    void subtractTwoObjects(){
        Money twoFifty = new Money(2,50);
        Money two = new Money(200);
        assertEquals("0:50 kr", twoFifty.subtract(two).toString());
    }

}
