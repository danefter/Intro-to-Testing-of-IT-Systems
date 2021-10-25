package inte.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.text.SimpleDateFormat;

class MembershipTest {
    
    @Test
    void addingACustomerToBeAMemberSetsCorrectMembershipID() { 
        PrivatePerson customer = new PrivatePerson("Anna Andersson", "Almvägen 1", "anna@gmail.com", "070123456", "2000-02-10");
        customer.addMembership();
        int correctID = customer.getDateOfBirth().hashCode() * customer.getEmail().hashCode() * 2000;
        assertEquals(correctID, customer.getMembership().getMemberID());
    }

    @Test
    void addingACustomerToBeAMember(){
        Customer customer = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1997-06-09");
        customer.addMembership();
        assertTrue(customer.isMember()); 
    }

    @Test
    void removeExistingMembership(){
        Customer customer = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1997-06-09");
        customer.addMembership();
        customer.removeMembership();
        try{
            customer.getMembership(); //we want to catch the IllegalStateException
        }catch(IllegalStateException msg){
            assertEquals("Customer is not a member", msg.getMessage());
        }
    }

    @Test
    void membershipCreatedSetsCorrectDate(){
        Customer customer = new PrivatePerson("Person person", "Adressvägen 4", "personperson@gmail.com", "0712345678", "1997-06-09");
        customer.addMembership();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        String dateForCustomerBecomingMember = "";
        dateForCustomerBecomingMember = formatter.format(today);
        assertEquals("Membership\n Created on: " + dateForCustomerBecomingMember + "\n" + customer.getMembership().getMembershipPoints().toString() + "\n", customer.getMembership().toString());
    }
    
    @Test
    void constructorWithPointsSetsCorrectPoints(){
        Membership mem = new Membership(100);
        assertEquals(100,mem.getMembershipPoints().getAllPoints());
    }

    @Test
    void getMembershipCreatedDate(){
        Membership mem = new Membership(100);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        String dateForCreation = formatter.format(today);
        assertEquals(dateForCreation, mem.getMembershipCreatedDate());
    }
}