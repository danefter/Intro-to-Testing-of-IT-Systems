package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void constructorTestId() {
        Product product = new Product("207813","screen",3999.9, new ProductType(""));
        assertEquals("207813",product.getId());
    }

    @Test
    void constructorTestName() {
        Product product = new Product("208614","tablet",2000, new ProductType(""));
        assertEquals("tablet",product.getName());
    }

    @Test
    void constructorTestPrice() {
        Product product = new Product("208614","tablet",2999.9, new ProductType(""));
        assertEquals(2999.9,product.getPrice());
    }

    @Test
    void lessdidgtsThanSixInId() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            new Product("24987","phone", 500.0, new ProductType(""));
        });

        assertEquals("Id must contain 6 numbers", e.getMessage());
    }

}