package inte.project;
//author Marah Zeibak
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class StoreTest {

    private static final String ADDRESS = "Vasagata 12";
    private static final String NEW_ADDRESS = "Gamlagata 3";
    private static final int POST_CODE = 12456;
    private static final int NEW_POST_CODE = 34895;
    private static final String CITY = "Stockholm";
    private static final String PHONE_NUMBER = "0706524324";
    private static final String NEW_PHONE_NUMBER = "0722222222";
    private static final String WRONG_PHONE_NUMBER = "4563fgdd";
    private static final int PRODUCT_QUANTITY = 100;
    private Store store;
    private  Product product;

    @Test
    void constructorTestAddress() {
        store = new Store(ADDRESS,12456,"Stockholm","0706524324");

        assertEquals(ADDRESS,store.getAddress());
    }

    @Test
    void testSetAddress() {
        store = new Store(ADDRESS,12456,"Stockholm","0706524324");
        store.setAddress(NEW_ADDRESS);

        assertEquals(NEW_ADDRESS,store.getAddress());
    }

    @Test
    void constructorTestPostCode() {
        store = new Store("Vasagata 12",POST_CODE,"Stockholm","0706524324");

        assertEquals(POST_CODE,store.getPostCode());
    }

    @Test
    void testSetPostCode() {
        store = new Store("Vasagata 12",POST_CODE,"Stockholm","0706524324");
        store.setPostCode(NEW_POST_CODE);

        assertEquals(NEW_POST_CODE,store.getPostCode());
    }

    @Test
    void constructorTestCity() {
        store = new Store("Vasagata 12",12456,CITY,"0706524324");

        assertEquals(CITY,store.getCity());
    }

    @Test
    void constructorTestPhoneNumber() {
        store = new Store("Vasagata 12",12456,"Stockholm",PHONE_NUMBER);

        assertEquals(PHONE_NUMBER,store.getPhoneNumber());
    }

    @Test
    void testSetPhoneNumber() {
        store = new Store("Vasagata 12",12456,"Stockholm",PHONE_NUMBER);
        store.setPhoneNumber(NEW_PHONE_NUMBER);

        assertEquals(NEW_PHONE_NUMBER,store.getPhoneNumber());
    }

    @Test
    void phoneNumberIsNotDigits() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Store("Vasastan 8",14567,"Stockholm",WRONG_PHONE_NUMBER);
        });

        assertEquals("Phone Number must contain only digits",e.getMessage());
    }

    @Test
    void testGetProductQuantity() {
        product = new HouseHold("986543","Mixer",new Money(1000));
        store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, PRODUCT_QUANTITY);

        assertEquals(PRODUCT_QUANTITY,store.getQuantity(product));
    }

    @Test
    void testAddProduct() {
        product = new HouseHold("986543","Mixer",new Money(1000));
        store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, 100);

        assertTrue(store.products.containsKey(product));
    }

    @Test
    void testAddProductWithExistingQuantity() {
        product = new HouseHold("986543","Mixer",new Money(1000));
        store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product, PRODUCT_QUANTITY);
        store.addProduct(product,200);

        assertEquals(300,store.products.get(product));
    }

    @Test
    void testDeleteProduct() {
        product = new Appliances("348723","Fridge",new Money(5000));
        store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product,300);

        store.deleteProduct(product,300);

        assertEquals(0,store.products.get(product));

    }

    @Test
    void testDeleteNotExistingProduct() {
        product = new Appliances("348723","Fridge",new Money(5000));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
            store.deleteProduct(product,350);
        });

        assertEquals("",e.getMessage());

    }

    @Test
    void testDeleteProductsWithMoreQuantity(){
        product = new Appliances("348723","Fridge",new Money(5000));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
            store.addProduct(product,300);
            store.deleteProduct(product,350);
        });

        assertEquals("",e.getMessage());
    }

    @Test
    void testSearchAfterProducts() {
        product = new Appliances("348723","Fridge",new Money(5000));
        Product product1 = new Appliances("347654","Stove",new Money(4500));
        Product product2 = new Tele("341276","Mobile",new Money(10000));
        Product product3 = new HouseHold("346576","Mixer",new Money(1500));
        Product product4 = new Appliances("897654","Stove",new Money(4500));
        Product product5 = new Appliances("001200","Stove",new Money(4500));

        store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        store.addProduct(product,100);
        store.addProduct(product1,30);
        store.addProduct(product2,400);
        store.addProduct(product3,350);
        store.addProduct(product4,50);
        store.addProduct(product5,300);


        String pro = product5 + " " + store.getQuantity(product5)+ "\n" + product1 + " " + store.getQuantity(product1)+ "\n" + product4 +" " + store.getQuantity(product4)+ "\n"+ product +" "+ store.getQuantity(product) + "\n" ;

        assertEquals(pro,store.searchProduct("Appliances"));
    }

}