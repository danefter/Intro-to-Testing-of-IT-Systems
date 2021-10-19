package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    
    @Test
    void constructorSetsName(){
        String dateOfBirth = "1995-10-13";
        Customer customer = new PrivatePerson("Anna Andersson", "Vasagatan 12", "anna.andersson@gmail.com", "0702445137", dateOfBirth); 
        assertEquals("Anna Andersson", customer.getName());
    } 

    @Test
    void constructorSetsAddress(){
        String dateOfBirth = "1973-04-11";
        Customer customer = new PrivatePerson("Benny Björk", "Sveagatan 43", "bjorn_gunnarsson@gmail.com", "0734778559", dateOfBirth);
        assertEquals("Sveagatan 43", customer.getAddress());
    }

    @Test 
    void constructorSetsEmail(){
        String dateOfBirth = "2000-12-12";
        Customer customer = new PrivatePerson("Camilla Carlsson", "Hamngatan 20", "camilla77@hotmail.com", "0708657840", dateOfBirth);
        assertEquals("camilla77@hotmail.com", customer.getEmail());
    }

    @Test
    void contructorSetsPhoneNumber(){
        String orgNumber = "655576-5433";
        Customer customer = new Company("Danny Danielsson", "Flimmervägen 3", "dannydanny@outlook.com", "0773535788", orgNumber);
        assertEquals("0773535788", customer.getPhoneNumber());
    }

    @Test
    void cunstructorAcceptsPhoneNumberThatStartsWithPlus(){
        String orgNumber = "543234-7689";
        Customer customer = new Company("Daniella Didriksson", "Snövägen 55", "danni@gmail.com", "+46773535788", orgNumber);
        assertEquals("+46773535788", customer.getPhoneNumber());
    }

    @Test
    void lessDigitsThanAcceptedInPhoneNumberThrowsException(){ //the min length is 4 digits, but those places have an area code of 3 digitf, which means min of total 7 digits
        String dateOfBirth = "1984-08-5";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("Eva Ekman", "Ekerövägen 56", "evasmail@gmail.com", "+46799", dateOfBirth);
        });
    }

    @Test
    void moreDigitsThanAcceptedInPhoneNumberThrowsException(){ //12 is the maximum lenth exlusive the area code, which is 4 if you count the + sign. 16 is the maximum of characters.
        String dateOfBirth = "2002-01-12";
        assertThrows(IllegalArgumentException.class, () -> {
            new PrivatePerson("Fanny Fylke", "Hammarbyvägen 33", "fannyfylke@hotmail.com", "+4677123456789121", dateOfBirth);
        });
    }

    @Test
    void spacesInPhoneNumberAreRemoved(){
        String orgNumber = "876789-9878";
        Customer customer = new Company("Gunanr Gabrielsson", "Huddingevägen 2", "gunnar123@gmail.com", "   +46 768393877    ", orgNumber);
        assertEquals(12, customer.getPhoneNumber().length());
    }

    @Test
    void wrongCharacterUsedInPhoneNumberThrownsException(){ // eg @, &, /
        String orgNumber = "453654-7687";
        assertThrows(IllegalArgumentException.class, () -> {
            new Company("Hanna Håkansson", "Hammarbystigen 87", "hannahakansson@hotmail.com", "070@788 9668", orgNumber);
        });
    }

    @Test
    void hyphenInPhoneNumberIsRemovedAndNumberAccepted(){ //hyphen is this character: -
        String dateOfBirth = "1997-06-14";
        Customer customer = new PrivatePerson("Ida Ivarsson", "Ludvikavägen 11", "idaivarsson@outlook.com", "0771- 871668", dateOfBirth);
        assertEquals("0771871668", customer.getPhoneNumber());
    }

    @Test
    void changingPhoneNumberSetsNewPhoneNumber(){
        String dateOfBirth = "1989-11-7";
        Customer customer = new PrivatePerson("Jakob Jansson", "Sibyllavägen 2", "jakke@gmail.com", "+46 779981332", dateOfBirth);
        customer.setPhoneNumber(" +46 789 117229");
        assertEquals("+46789117229", customer.getPhoneNumber());
    }

    @Test
    void changingEmailSetsNewEmail(){
        String dateOfBirth = "2001-1-3";
        Customer customer = new PrivatePerson("Kim Knutsson", "Karlaplan 66", "kimknutsson@gmail.com", "+46778899066", dateOfBirth);
        customer.setEmail("kimknutsson2@gmail.com");
        assertEquals("kimknutsson2@gmail.com", customer.getEmail());
    }

    @Test
    void changingAddressSetsNewAdress(){
        String orgNumber = "345623-6787";
        Customer customer = new Company("Lina Larsson", "Storgatan 15", "linalarsson89@gmail.com", "0708707066", orgNumber);
        customer.setAddress("Sjöholmsväg 4");
        assertEquals("Sjöholmsväg 4", customer.getAddress());
    }
    @Test
    void isMemberWithoutSettingMembership(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707990998", "1997-06-09");
        assertFalse(customer.isMember());
    }

    @Test
    void customerBecomesMember(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707990998", "1997-06-09");
        customer.setMembership();
        assertTrue(customer.isMember());
    }

    @Test
    void customerMembershipIsSavedInSystem(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707990998", "1997-06-09");
        customer.setMembership();
        assertTrue(customer.getMembership().getMember(customer.getMembership().getMemberID()).equals(customer));
    }

    @Test
    void removingCustomerMembership(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707990998", "1997-06-09");
        customer.setMembership();
        customer.removeMembership();
        assertFalse(customer.isMember());
    }

    @Test
    void becomingMemberAgainAfterRemovingMembership(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707990998", "1997-06-09");
        customer.setMembership();
        customer.removeMembership();
        customer.setMembership();
        assertTrue(customer.getMembership().getMember(customer.getMembership().getMemberID()).equals(customer));
    }

    @Test
    void customerBecomesMemberGetMembership(){
        Customer customer = new PrivatePerson("name", "address", "email", "0707990998", "1997-06-09");
        customer.setMembership();
        assertNotNull(customer.isMember());
    }

    @Test
    void newCustomerIsAddedToCustomerHandler(){
        Customer customer = new Company("name", "address", "email", "0706545567", "989987-1233");
        assertTrue(customer.getCustomerHandler().getAllCustomers().contains(customer));
    }
}
