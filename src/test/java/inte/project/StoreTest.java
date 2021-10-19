package inte.project;
//author Marah Zeibak
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        Product product = new HouseHold("986543","Mixer",new Money(1000));
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, 100);

        assertEquals(100,store.getQuantity(product));
    }

    @Test
    void testAddProduct() {
        Product product = new HouseHold("986543","Mixer",new Money(1000));
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, 100);

        assertEquals(100, store.products.get(product));
    }

    @Test
    void testAddProductWithExistingQuantity() {
        Product product = new HouseHold("986543","Mixer",new Money(1000));
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, 100);
        store.addProduct(product,200);

        assertEquals(300,store.products.get(product));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Appliances("348723","Fridge",new Money(5000));
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product,300);

        store.deleteProduct(product,300);

        assertEquals(0,store.products.get(product));

    }

    @Test
    void testDeleteProductsWithMoreQuantity(){
        Product product = new Appliances("348723","Fridge",new Money(5000));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
            store.addProduct(product,300);
            store.deleteProduct(product,350);
        });

        assertEquals("",e.getMessage());
    }

    @Test
    void testSearchAfterProducts() {
        Product product = new Appliances("348723","Fridge",new Money(5000));
        Product product1 = new Appliances("347654","Stove",new Money(4500));
        Product product2 = new Tele("341276","Mobile",new Money(10000));
        Product product3 = new HouseHold("346576","Mixer",new Money(1500));

        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product,100);
        store.addProduct(product1,30);
        store.addProduct(product2,400);
        store.addProduct(product3,350);

        Map<Product,Integer> newMapWithProducts = new HashMap<>();
        newMapWithProducts.put(product,100);
        newMapWithProducts.put(product1,30);

        assertEquals(newMapWithProducts,store.searchProduct("Appliances"));
    }

}