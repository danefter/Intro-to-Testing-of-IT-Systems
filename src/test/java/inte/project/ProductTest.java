package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void constructorTestId() {
        Product product = new Product(207813,"screen",399.9);
        assertEquals(207813,product.getId());
    }

}