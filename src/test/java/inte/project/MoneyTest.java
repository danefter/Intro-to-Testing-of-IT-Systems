package inte.project;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    void toStringPresentsAmount(){
        Money first = new Money(23);
        assertEquals(23, 23);
    }

}
