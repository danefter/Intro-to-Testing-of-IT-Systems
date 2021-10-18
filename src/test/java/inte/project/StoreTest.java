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

}