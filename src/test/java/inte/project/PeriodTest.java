package inte.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    void getStartDate(){
        Period period = new Period();
        assertEquals("15/10/2021", period.startToString());
    }
    @Test
    void getEndDate(){
        Period date = new Period();
        assertEquals("15/10/2022", date.endToString());
    }
}