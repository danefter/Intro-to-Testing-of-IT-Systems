package inte.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {
    /*
    @Test
    void constructorTestId() {
        Membership membership = new Membership(new PrivatePerson("namn", "address", "email", "0707889885", "1997-06-09"));        
        assertNotNull(membership);
    }

    @Test
    void addingACustomerToBeAMember(){
        Customer customer = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1997-06-09");
        Membership membership = new Membership(customer);
        assertEquals(customer, membership.getMember(membership.getMemberID())); 
    }

    @Test
    void addingACustomerToBeAMemberAlsoSetsMembership(){
        Customer customer = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1997-06-09");
        Membership membership = new Membership(customer);
        assertTrue(customer.isMember());
    }


    @Test
    void removeExistingMembership(){
        Customer customer = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1997-06-09");
        Membership membership = new Membership(customer);
        membership.removeMembership(customer);
        assertNull(membership.getMembership(customer));
    }

    @Test
    void removingMembershipSetsCustomerMembershipToFalse(){
        Customer customer = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1997-06-09");
        Membership membership = new Membership(customer);
        membership.removeMembership(customer);
        assertFalse(customer.isMember());
    }
    */



    @Test
    void addingACustomerToBeAMemberSetsCorrectID(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707898890", "1997-06-09");
        int correctID = ((PrivatePerson)customer).getDateOfBirth().hashCode() * customer.getEmail().hashCode() * 2000;
        customer.setMembership();
        assertEquals(correctID, customer.getMembership().getMemberID());
    }

    


 /*   @Test
    void toYoungCustomerThrowsException(){
       TODO - good test to do
    }
    */

}