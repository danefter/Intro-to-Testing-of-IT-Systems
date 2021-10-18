package inte.project;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PrivatePersonTest {
    @Test
    void constructorSetsDateOfBirth(){
        int yearOfBirth = 1993;
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", yearOfBirth);
        assertEquals(yearOfBirth, person.getYearOfBirth());
    }

    @Test
    void dateOfBirthTooLowThrowsException(){
        int yearOfBirth = 1899;
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("Bodil Bergh", "Vattenfallsgränd 3", "bodilbergh@gmail.com", "0708778987", yearOfBirth);
        });
    }

    @Test
    void dateOfBirthGreaterThanCurrentYearThrowsException(){
        int yearOfBirth = LocalDate.now().getYear() + 3;
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("Carl Carlsson", "Stupgränd 4", "carl@outlook.com", "+4672 5678 222", yearOfBirth);
        });
    }
}
