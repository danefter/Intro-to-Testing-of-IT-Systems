package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    
    @Test
    void constructorSetsName(){
        Customer customer = new Customer("Anna Andersson", "Vasagatan 12", "anna.andersson@gmail.com", "0702445137"); 
        assertEquals("Anna Andersson", customer.getName());
    } 

    @Test
    void constructorSetsAddress(){
        Customer customer = new Customer("Benny Björk", "Sveagatan 43", "bjorn_gunnarsson@gmail.com", "0734778559");
        assertEquals("Sveagatan 43", customer.getAddress());
    }

    @Test 
    void constructorSetsEmail(){
        Customer customer = new Customer("Camilla Carlsson", "Hamngatan 20", "camilla77@hotmail.com", "0708657840");
        assertEquals("camilla77@hotmail.com", customer.getEmail());
    }

    @Test
    void contructorSetsPhoneNumber(){
        Customer customer = new Customer("Danny Danielsson", "Flimmervägen 3", "dannydanny@outlook.com", "0773535788");
        assertEquals("0773535788", customer.getPhoneNumber());
    }

    @Test
    void cunstructorAcceptsPhoneNumberThatStartsWithPlus(){
        Customer customer = new Customer("Daniella Didriksson", "Snövägen 55", "danni@gmail.com", "+46773535788");
        assertEquals("+46773535788", customer.getPhoneNumber());
    }

    @Test
    void lessDigitsThanAcceptedInPhoneNumberThrowsException(){ //the min length is 4 digits, but those places have an area code of 3 digitf, which means min of total 7 digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Eva Ekman", "Ekerövägen 56", "evasmail@gmail.com", "+46799");
        });
    }

    @Test
    void moreDigitsThanAcceptedInPhoneNumberThrowsException(){ //12 is the maximum lenth exlusive the area code, which is 4 if you count the + sign. 16 is the maximum of characters.
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Fanny Fylke", "Hammarbyvägen 33", "fannyfylke@hotmail.com", "+4677123456789121");
        });
    }

    @Test
    void spacesInPhoneNumberAreRemoved(){
        Customer customer = new Customer("Gunanr Gabrielsson", "Huddingevägen 2", "gunnar123@gmail.com", "   +46 768393877    ");
        assertEquals(12, customer.getPhoneNumber().length());
    }

    @Test
    void wrongCharacterUsedInPhoneNumberThrownsException(){ // eg @, &, /
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Hanna Håkansson", "Hammarbystigen 87", "hannahakansson@hotmail.com", "070@788 9668");
        });
    }

    @Test
    void hyphenInPhoneNumberIsRemovedAndNumberAccepted(){ //hyphen is this character: -
        Customer customer = new Customer("Ida Ivarsson", "Ludvikavägen 11", "idaivarsson@outlook.com", "0771- 871668");
        assertEquals("0771871668", customer.getPhoneNumber());
    }

    @Test
    void changingPhoneNumberSetsNewPhoneNumber(){
        Customer customer = new Customer("Jakob Jansson", "Sibyllavägen 2", "jakke@gmail.com", "+46 779981332");
        customer.setPhoneNumber(" +46 789 117229");
        assertEquals("+46789117229", customer.getPhoneNumber());
    }

    @Test
    void changingEmailSetsNewEmail(){
        Customer customer = new Customer("Kim Knutsson", "Karlaplan 66", "kimknutsson@gmail.com", "+46778899066");
        customer.setEmail("kimknutsson2@gmail.com");
        assertEquals("kimknutsson2@gmail.com", customer.getEmail());
    }

    @Test
    void changingAddressSetsNewAdress(){
        Customer customer = new Customer("Lina Larsson", "Storgatan 15", "linalarsson89@gmail.com", "0708707066");
        customer.setAddress("Sjöholmsväg 4");
        assertEquals("Sjöholmsväg 4", customer.getAddress());
    }
}
