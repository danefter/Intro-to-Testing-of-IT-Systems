package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StoreTest {

    @Test
    void constructorTestAddress() {
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");

        assertEquals("Vasagata 12",store.getAddress());
    }

    @Test
    void constructorTestPostCode() {
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");

        assertEquals(12456,store.getPostCode());
    }

    @Test
    void constructorTestCity() {
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");

        assertEquals("Stockholm",store.getCity());
    }

    @Test
    void testGetQuantityProducts() {
        Product product = new HouseHold("986543","Mixer",1000.0);
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, 100);

        assertEquals(100,store.getQuantity(product));
    }

    @Test
    void testAddProduct() {
        Product product = new HouseHold("986543","Mixer",1000.0);
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, 100);

        assertEquals(100, store.products.get(product));
    }

    @Test
    void testAddProductWithExistingQuantity() {
        Product product = new HouseHold("986543","Mixer",1000.0);
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, 100);
        store.addProduct(product,200);

        assertEquals(300,store.products.get(product));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Appliances("348723","Fridge",5000.0);
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product,300);

        store.deleteProduct(product,300);

        assertEquals(0,store.products.get(product));

    }

    @Test
    void testDeleteProductsWithMoreQuantity(){
        Product product = new Appliances("348723","Fridge",5000.0);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
            store.addProduct(product,300);
            store.deleteProduct(product,350);
        });

        assertEquals("",e.getMessage());
    }

}