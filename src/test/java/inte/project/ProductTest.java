package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void constructorTestId() {
        Product product = new Product("207813","screen",3999.9);
        assertEquals("207813",product.getId());
    }

    @Test
    void constructorTestName() {
        Product product = new Product("208614","tablet",2000);
        assertEquals("tablet",product.getName());
    }

    @Test
    void constructorTestPrise() {
        Product product = new Product("208614","tablet",2999.9);
        assertEquals(2999.9,product.getPrise());
    }

    @Test
    void lessdidgtsThanSixInId() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            new Product("24987","phone", 500.0);
        });

        assertEquals("Id must contain 6 numbers", e.getMessage());
    }

}