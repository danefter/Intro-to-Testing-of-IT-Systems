package inte.project;
//author Marah Zeibak
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void constructorTestId() {
        Product product = new Tele("207813","screen", new Money(4000));
        assertEquals("207813",product.getId());
    }

    @Test
    void constructorTestName() {
        Product product = new Tele("208614","tablet",new Money(2000));
        assertEquals("tablet",product.getName());
    }

    @Test
    void constructorTestPrice() {
        Product product = new Tele("208614","tablet",new Money(2999));
        assertEquals(2999,product.getPrice().getAmountInOre());
    }

    @Test
    void constructorTestType() {
        Product product = new Tele("986523","phone",new Money(450));

        assertEquals("Tele",product.getType());
    }

    @Test
    void setPriceTest() {
        Product product = new Tele("986523","phone",new Money(450));
        product.setPrice(new Money(300));

        assertEquals(300.0,product.getPrice().getAmountInOre());
    }

    @Test
    void testGetPricePlusVat() {
        Product product = new Tele("986523","phone",new Money(4500));
        assertEquals(5850,product.getPricePlusVAT());

    }
    @Test
    void lessdidgtsThanSixInId() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Tele("24987","phone", new Money(4500));
        });

        assertEquals("Id must contain 6 numbers", e.getMessage());
    }

    @Test
    void priceLessThan0Kr() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Tele("245694","phone",new Money(-45));
        });

        assertEquals("price must be more", e.getMessage());
    }

}