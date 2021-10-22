package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PrivatePersonTest {
    @Test
    void constructorSetsDateOfBirth(){
        String dateOfBirth = "1993-6-5";
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", dateOfBirth);
        assertEquals(dateOfBirth, person.getDateOfBirth());
    }

    @Test
    void dateOfBirthTooLowThrowsException(){
        String dateOfBirthh = "1899-8-7";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("Bodil Bergh", "Vattenfallsgränd 3", "bodilbergh@gmail.com", "0708778987", dateOfBirthh);
        });
    }

    @Test
    void dateOfBirthGreaterThanCurrentYearThrowsException(){
        String dateOfBirth = "2025-7-4";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("Carl Carlsson", "Stupgränd 4", "carl@outlook.com", "+4672 5678 222", dateOfBirth);
        });
    }

    @Test
    void customerDateOfBirthSetsCorrectYear(){
        String dateOfBirth = "1966-11-10";
        PrivatePerson customer = new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        assertEquals(1966, customer.getYearOfBirth());
    }

    @Test
    void customerDateOfBirthSetsCorrectMonth(){
        String dateOfBirth = "1977-12-9";
        PrivatePerson customer = new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        assertEquals(12, customer.getMonthOfBirth());
    }

    @Test
    void customerDateOfBirthSetsCorrectDay(){
        String dateOfBirth = "1956-1-16";
        PrivatePerson customer = new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        assertEquals(16, customer.getDayOfBirth());
    }

    @Test
    void monthThatDoesNotExistThrowsException(){
        String dateOfBirth = "1970-14-12";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        });
    }

    @Test
    void monthStartsWithZeroIsAccepted(){
        String dateOfBirth = "1990-02-9";
        PrivatePerson customer = new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        assertEquals(2, customer.getMonthOfBirth());
    }

    @Test
    void dayStartsWithZeroIsAccepted(){
        String dateOfBirth = "1990-02-09";
        PrivatePerson customer = new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        assertEquals(9, customer.getDayOfBirth());
    }

    @Test
    void tooManyDaysInDateOfBirthForMarch(){
        String dateOfBirth = "1999-3-32";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        });
    }

    @Test
    void dayNotPossibleInFebruaryBecauseItIsLeapYear(){
        String dateOfBirth = "2000-02-30";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        });
    }

    @Test
    void dayNotPossibleInFebruaryBecauseItIsNotLeapYear(){
        String dateOfBirth = "1999-02-29";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        });
    }

    @Test
    void day31NotPossibleInApril(){
        String dateOfBirth = "1999-04-31";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("name", "address", "email", "0712345678", dateOfBirth);
        });
    }

}
