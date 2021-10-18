package inte.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {
    @Test
    void addNewCustomerExistHashSet(){
        CustomerHandler ch = new CustomerHandler();
        ch.addPrivatePerson("name", "address", "email", "0707990998", 1997);
        assertFalse(ch.getAllCustomers().isEmpty());
    }
    @Test
    void addNewCustomerExistHashMap(){
        CustomerHandler ch = new CustomerHandler();
        ch.addPrivatePerson("name", "address", "email", "0707990998", 1997);
        assertNotNull(ch.getCustomerByName("name"));
    }
    @Test
    void addNewCustomerExistHashMapAdress(){
        CustomerHandler ch = new CustomerHandler();
        ch.addPrivatePerson("name", "address", "email", "0707990998", 1997);
        assertNotNull(ch.getCustomerByAdress("address"));
    }

    @Test
    void addNewCustomerExistHashMapPhone(){
        CustomerHandler ch = new CustomerHandler();
        ch.addPrivatePerson("name", "address", "email", "0707990998", 1997);
        assertNotNull(ch.getCustomerByPhoneNumber("0707990998"));
    }

}