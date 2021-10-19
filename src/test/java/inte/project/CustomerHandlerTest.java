package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {
    @Test
    void addNewCustomerExistHashSet(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", 1997);
        assertFalse(customer.getCustomerHandler().getAllCustomers().isEmpty());
    }
    @Test
    void gettingEmptyHashSet(){
        CustomerHandler ch = new CustomerHandler();
        assertTrue(ch.getAllCustomers().isEmpty());
    }
    @Test
    void addNewCustomerExistHashMap(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", 1997);
        assertNotNull(customer.getCustomerHandler().getCustomerByName("name"));
    }
    @Test
    void addNewCustomerExistHashMapAdress(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", 1997);
        assertNotNull(customer.getCustomerHandler().getCustomerByAdress("address"));
    }

    @Test
    void addNewCustomerExistHashMapPhone(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", 1997);
        assertNotNull(customer.getCustomerHandler().getCustomerByPhoneNumber("0708988900"));
    }
    @Test
    void getNonExistingCustomerThrowsIllegal(){
        CustomerHandler ch = new CustomerHandler();
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            ch.getCustomerByPhoneNumber("0707990998");
        });
    }
}