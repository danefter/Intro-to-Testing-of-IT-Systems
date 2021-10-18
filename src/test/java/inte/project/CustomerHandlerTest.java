package inte.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {
    @Test
    void addNewCustomerExistHashMap(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707990998", 1997);
        customer.setMembership();
        customer.removeMembership();
        assertFalse(customer.isMember());
    }

}