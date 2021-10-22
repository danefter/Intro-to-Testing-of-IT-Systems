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

}
