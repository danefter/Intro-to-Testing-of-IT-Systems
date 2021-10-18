package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void constructorTestId() {
        Product product = new Product("207813","screen",3999.9, new ProductType("Tele"));
        assertEquals("207813",product.getId());
    }

    @Test
    void constructorTestName() {
        Product product = new Product("208614","tablet",2000, new ProductType("Tele"));
        assertEquals("tablet",product.getName());
    }

    @Test
    void constructorTestPrice() {
        Product product = new Product("208614","tablet",2999.9, new ProductType("Tele"));
        assertEquals(2999.9,product.getPrice());
    }

    @Test
    void constructorTestType() {
        Product product = new Product("986523","phone",450.0,new ProductType("Tele"));

        assertEquals("Tele",product.getType().getType());
    }

    @Test
    void setPriceTest() {
        Product product = new Product("986523","phone",450.0,new ProductType("Tele"));
        product.setPrice(300.0);

        assertEquals(300.0,product.getPrice());
    }

    @Test
    void lessdidgtsThanSixInId() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            new Product("24987","phone", 500.0, new ProductType("Tele"));
        });

        assertEquals("Id must contain 6 numbers", e.getMessage());
    }

    @Test
    void priceLessThan0Kr() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            new Product("245694","phone",-45,new ProductType("Tele"));
        });

        assertEquals("price must be more", e.getMessage());
    }

}