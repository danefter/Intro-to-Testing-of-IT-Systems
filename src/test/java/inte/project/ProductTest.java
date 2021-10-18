package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void constructorTestId() {
        Product product = new Tele("207813","screen",3999.9);
        assertEquals("207813",product.getId());
    }

    @Test
    void constructorTestName() {
        Product product = new Tele("208614","tablet",2000);
        assertEquals("tablet",product.getName());
    }

    @Test
    void constructorTestPrice() {
        Product product = new Tele("208614","tablet",2999.9);
        assertEquals(2999.9,product.getPrice());
    }

    @Test
    void constructorTestType() {
        Product product = new Tele("986523","phone",450.0);

        assertEquals("Tele",product.getType());
    }

    @Test
    void setPriceTest() {
        Product product = new Tele("986523","phone",450.0);
        product.setPrice(300.0);

        assertEquals(300.0,product.getPrice());
    }

    @Test
    void lessdidgtsThanSixInId() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            new Tele("24987","phone", 500.0);
        });

        assertEquals("Id must contain 6 numbers", e.getMessage());
    }

    @Test
    void priceLessThan0Kr() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            new Tele("245694","phone",-45);
        });

        assertEquals("price must be more", e.getMessage());
    }

}