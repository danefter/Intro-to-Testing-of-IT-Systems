package inte.project;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {
    @Test
    void getStartDate(){
        Period period = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(formatter.format(today), period.startToString());
    }
    @Test
    void getEndDate(){
        Period date = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        stringDate[2] = "2022";
        String finalString = stringDate[0] + "/";
        finalString += stringDate[1];
        finalString += "/";
        finalString += stringDate[2];
        assertEquals(finalString, date.endToString());
    }
}