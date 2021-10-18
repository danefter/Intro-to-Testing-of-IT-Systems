package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTypeTest {

    @Test
    void constructorTestType() {
        ProductType type = new ProductType("data");

        assertEquals("data",type.getType());
    }

    @Test
    void testAddProduct() {
        ProductType type = new ProductType("Tele");
        Product product = new Product("208614","tablet",2000, new ProductType("Tele"));

        type.addProduct(product);

        assertTrue(type.getProducts().contains(product));
    }

    @Test
    void testAddProductWithDifferentType() {
        ProductType type = new ProductType("household");
        Product product = new Product("208614","tablet",2000, new ProductType("data"));

        type.addProduct(product);

        assertFalse(type.getProducts().contains(product));

    }

    @Test
    void testDeleteProduct() {
        ProductType type = new ProductType("Tele");
        Product product = new Product("208614","tablet",2000, new ProductType("Tele"));

        type.addProduct(product);

        type.deleteProduct(product);
        assertFalse(type.getProducts().contains(product));
        
    }

}