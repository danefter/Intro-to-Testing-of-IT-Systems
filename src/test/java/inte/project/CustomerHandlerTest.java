package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {

    @Test
    void addNewCustomerExistHashSet(){
        CustomerHandler ch = new CustomerHandler();
        ch.addCustomer(new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13"));
        assertFalse(ch.getAllCustomers().isEmpty());
    }

    @Test
    void gettingEmptyHashSet(){
        CustomerHandler ch = new CustomerHandler();
        assertTrue(ch.getAllCustomers().isEmpty());
    }

    @Test
    void addNewCustomerExistHashMapName(){
        CustomerHandler ch = new CustomerHandler();
        ch.addCustomer(new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13"));
        assertNotNull(ch.getCustomerByName("name"));
    }
    @Test
    void addNewCustomerExistHashMapAdress(){
        CustomerHandler ch = new CustomerHandler();
        ch.addCustomer(new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13"));
        assertNotNull(ch.getCustomerByAdress("address"));
    }

    @Test
    void addNewCustomerExistHashMapPhone(){
        CustomerHandler ch = new CustomerHandler();
        ch.addCustomer(new PrivatePerson("name", "address", "email", "0708988900", "1997-05-13"));
        assertNotNull(ch.getCustomerByPhoneNumber("0708988900"));
    }


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
    void getAllCustomers(){
        CustomerHandler customerHandler = new CustomerHandler();
        customerHandler.addCustomer(new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1990-01-01"));
        customerHandler.addCustomer(new PrivatePerson("lukas", "Adressvägen 5", "lukas@gmail.com", "1612345678", "1991-01-01"));
        customerHandler.addCustomer(new PrivatePerson("maggie", "Adressvägen 6", "maggie@gmail.com", "2512345678", "1992-01-01"));
        customerHandler.addCustomer(new PrivatePerson("magnus", "Adressvägen 7", "magnus@gmail.com", "3412345678", "1993-01-01"));
        customerHandler.addCustomer(new PrivatePerson("blim", "Adressvägen 8", "blim@gmail.com", "4312345678", "1994-01-01"));
        customerHandler.addCustomer(new PrivatePerson("blam", "Adressvägen 9", "blam@gmail.com", "5212345678", "1995-01-01"));
        System.out.print(customerHandler.getAllCustomers().toString());
    }
    @Test
    void removeCustomerTest(){
        CustomerHandler customerHandler = new CustomerHandler();
        customerHandler.addCustomer(new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1990-01-01"));
        customerHandler.addCustomer(new PrivatePerson("lukas", "Adressvägen 5", "lukas@gmail.com", "1612345678", "1991-01-01"));
        customerHandler.addCustomer(new PrivatePerson("maggie", "Adressvägen 6", "maggie@gmail.com", "2512345678", "1992-01-01"));
        customerHandler.addCustomer(new PrivatePerson("magnus", "Adressvägen 7", "magnus@gmail.com", "3412345678", "1993-01-01"));
        customerHandler.addCustomer(new PrivatePerson("blim", "Adressvägen 8", "blim@gmail.com", "4312345678", "1994-01-01"));
        customerHandler.addCustomer(new PrivatePerson("blam", "Adressvägen 9", "blam@gmail.com", "5212345678", "1995-01-01"));
        customerHandler.removeCustomer(customerHandler.getCustomerByAdress("Adressvägen 4"));
        customerHandler.removeCustomer(customerHandler.getCustomerByAdress("Adressvägen 5"));
        customerHandler.removeCustomer(customerHandler.getCustomerByAdress("Adressvägen 6"));
        customerHandler.removeCustomer(customerHandler.getCustomerByAdress("Adressvägen 7"));
        customerHandler.removeCustomer(customerHandler.getCustomerByAdress("Adressvägen 8"));
        customerHandler.removeCustomer(customerHandler.getCustomerByAdress("Adressvägen 9"));
        assertTrue(customerHandler.getAllCustomers().isEmpty());
    }
    @Test
    void changingAddressIsSavedWhenFoundWithOtherVariable(){
        CustomerHandler ch = new CustomerHandler();
        String orgNumber = "345623-6787";
        Customer customer = new Company("Lina Larsson", "Storgatan 15", "linalarsson89@gmail.com", "0708707066", orgNumber);
        ch.addCustomer(customer);
        ch.getCustomerByAdress("Storgatan 15").setAddress("Sjöholmsvägen rätt");
        assertEquals("Sjöholmsvägen rätt", ch.getCustomerByPhoneNumber("0708707066").getAddress());
    }
}