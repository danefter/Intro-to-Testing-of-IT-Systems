package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {
    /*
    @Test
    void addNewCustomerExistHashSet(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13");
        assertFalse(customer.getCustomerHandler().getAllCustomers().isEmpty());
    }
    */
    @Test
    void gettingEmptyHashSet(){
        CustomerHandler ch = new CustomerHandler();
        assertTrue(ch.getAllCustomers().isEmpty());
    }
    /*
    @Test
    void addNewCustomerExistHashMap(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13");
        assertNotNull(customer.getCustomerHandler().getCustomerByName("name"));
    }
    @Test
    void addNewCustomerExistHashMapAdress(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13");
        assertNotNull(customer.getCustomerHandler().getCustomerByAdress("address"));
    }

    @Test
    void addNewCustomerExistHashMapPhone(){
        Customer customer = new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13");
        assertNotNull(customer.getCustomerHandler().getCustomerByPhoneNumber("0708988900"));
    }
    */

    @Test
    void getNonExistingCustomerThrowsIllegal(){
        CustomerHandler ch = new CustomerHandler();
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            ch.getCustomerByPhoneNumber("0707990998");
        });
    }
    @Test
    void removeExistingCustomer(){
        CustomerHandler customerHandler = new CustomerHandler();
        Customer cu = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1990-01-01");
        customerHandler.addCustomer(cu);
        customerHandler.removeCustomer(cu);
        assertTrue(customerHandler.getAllCustomers().isEmpty());
    }
    @Test
    void largerCustomerHandlerTest(){
        CustomerHandler customerHandler = new CustomerHandler();
        customerHandler.addCustomer(new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1990-01-01");
        customerHandler.addCustomer(new PrivatePerson("lukas", "Adressvägen 5", "lukas@gmail.com", "1612345678", "1991-01-01"));
        customerHandler.addCustomer(new PrivatePerson("maggie", "Adressvägen 6", "maggie@gmail.com", "2512345678", "1992-01-01"));
        customerHandler.addCustomer(new PrivatePerson("magnus", "Adressvägen 7", "magnus@gmail.com", "3412345678", "1993-01-01"));
        customerHandler.addCustomer(new PrivatePerson("blim", "Adressvägen 8", "blim@gmail.com", "4312345678", "1994-01-01"));
        customerHandler.addCustomer(new PrivatePerson("blam", "Adressvägen 9", "blam@gmail.com", "5212345678", "1995-01-01"));
        customerHandler.removeCustomer(customerHandler.getCustomerByAdress("Adressvägen 4"));
        assertTrue(customerHandler.getAllCustomers().isEmpty());
    }
}