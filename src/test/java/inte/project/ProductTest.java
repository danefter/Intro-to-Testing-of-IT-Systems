package inte.project;
//author Marah Zeibak
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    private static final String ID = "207813";
    private static final String NAME = "tablet";
    private static final String TYPE = "Tele";
    private static final int PRICE = 2999;
    private static final int NEW_PRICE = 300;
    private static final int PRICE_WITHOUT_VAT = 4500;
    private static final int PRICE_PLUS_VAT = 5850;
    private static final int VAT_VALUE = 1350;
    private static final String INVALID_ID = "24987";
    private static final int INVALID_PRICE = -45;
    private Product product;
    private Store store;

    @Test
    void constructorTestId() {
        product = new Tele(ID,"screen", new Money(4000));
        assertEquals(ID,product.getId());
    }

    @Test
    void constructorTestName() {
        product = new Tele("208614",NAME,new Money(2000));
        assertEquals(NAME,product.getName());
    }

    @Test
    void constructorTestPrice() {
        product = new Tele("208614","tablet",new Money(PRICE));
        assertEquals(PRICE,product.getPrice().getAmountInOre());
    }

    @Test
    void constructorTestType() {
        product = new Tele("986523","phone",new Money(450));

        assertEquals(TYPE,product.getType());
    }

    @Test
    void setPriceTest() {
        product = new Tele("986523","phone",new Money(450));
        product.setPrice(new Money(NEW_PRICE));

        assertEquals(NEW_PRICE,product.getPrice().getAmountInOre());
    }

    @Test
    void testGetPricePlusVat() {
        product = new Tele("986523","phone",new Money(PRICE_WITHOUT_VAT));

        assertEquals(PRICE_PLUS_VAT,product.getPricePlusVAT());

    }

    @Test
    void testGetVatValue() {
        product = new Tele("986523","phone",new Money(PRICE_WITHOUT_VAT));

        assertEquals(VAT_VALUE,product.getVatValue());
    }

    @Test
    void lessDigitsThanSixInId() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Tele(INVALID_ID,"phone", new Money(4500));
        });

        assertEquals("Id must contain 6 numbers", e.getMessage());
    }

    @Test
    void priceLessThan0Kr() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Tele("245694","phone",new Money(INVALID_PRICE));
        });

        assertEquals("price must be more", e.getMessage());
    }

    @Test
    void testAddProductsToStoreFromProductClass() {
        product = new Tele("346765","Mobile",new Money(3000));
        store = new Store("Vasagata 8",15143,"Stockholm","0734238796");
        product.addProductsToStore(store,400);

        assertTrue(store.products.containsKey(product));
    }

    @Test
    void testGetStore() {
        product = new Tele("346765","Mobile",new Money(3000));
        store = new Store("Vasagata 8",15143,"Stockholm","0734238796");
        store.addProduct(product,500);

        assertEquals(product.getStore().toString(),store.toString());
    }

}