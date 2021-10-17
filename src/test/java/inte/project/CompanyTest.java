package inte.project;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CompanyTest {
    @Test
    void constructorSetsOrgNumber(){
        String orgNumber = "555577-7778";
        Company company = new Company("SmartTeams", "Göteborgstorg 2", "smartTeams@contact.se", "+4677-444 1332", orgNumber);
        assertEquals(orgNumber, company.getOrgNumber());
    }

    @Test
    void moreThanTenDigitsInOrgNumberThrowsException(){
        String orgNumber = "55599999-7778";
        assertThrows(IllegalArgumentException.class, () ->{
            new Company("GoodCrew", "Gävlevägen 12", "goodcrew@contact.se", "+4623-123 1982", orgNumber);
        });
    }

    @Test
    void lessThanTenDigitsInOrgNumberThrowsException(){
        String orgNumber = "890-322";
        assertThrows(IllegalArgumentException.class, () ->{
            new Company("Motors AB", "Sturegatan 97", "motors@contact.se", "+4611-889 3455", orgNumber);
        });   
    }

    @Test
    void unacceptableCharactersInOrgNumberThrowsException(){
        String orgNumber = "jk123-56660";
        assertThrows(IllegalArgumentException.class, () ->{
            new Company("Company", "Folkvägen 5", "company@contact.se", "+4677 899 2336", orgNumber);
        });
    }
}
