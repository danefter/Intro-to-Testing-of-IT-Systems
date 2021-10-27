package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class ReceiptTest {

    @Test
    void printReceipt(){
        Store store = new Store("addressvägen 1", 18799, "city", "0789882005");
        HouseHold houseHoldProduct = new HouseHold("986523","fridge",new Money(5000));
        houseHoldProduct.addStore(store);
        Order order = new Order(houseHoldProduct);
        store.addProduct(houseHoldProduct, 5);
        Receipt receipt = new Receipt(order, store);
        String correct = "\n" + store.toString() + "\n" + order.getInfo().replaceAll("[\\[\\]]", "");
        assertEquals(correct, receipt.print());


    }
}
