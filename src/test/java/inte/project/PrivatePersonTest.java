package inte.project;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PrivatePersonTest {
    @Test
    void constructorSetsDateOfBirth(){
        int yearOfbirth = 1993;
        PrivatePerson person = new PrivatePerson("Albin Ahl", "Regngatan 33", "abbeAhl@gmail.com", "0707896779", yearOfbirth);
        assertEquals(yearOfbirth, person.getYearOfBirth());
    }
}
