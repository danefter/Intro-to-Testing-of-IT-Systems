package inte.project;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {
    @Test
    void getStartDay(){
        Period period = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        assertEquals(Integer.parseInt(stringDate[0]), period.getStartDay());
    }
    @Test
    void getStartMonth(){
        Period period = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        assertEquals(Integer.parseInt(stringDate[1]), period.getStartMonth());
    }
    @Test
    void getStartYear(){
        Period period = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        assertEquals(Integer.parseInt(stringDate[2]), period.getStartYear());
    }
    @Test
    void getEndDay(){
        Period period = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        assertEquals(Integer.parseInt(stringDate[0]), period.getEndDay());
    }
    @Test
    void getEndMonth(){
        Period period = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        assertEquals(Integer.parseInt(stringDate[1]), period.getEndMonth());
    }
    @Test
    void getEndYear(){
        Period period = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        assertEquals(Integer.parseInt(stringDate[2]) + 1, period.getEndYear());
    }
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
    @Test
    void toStringTest(){
        Period date = new Period();
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] stringDate = formatter.format(today).split("/");
        stringDate[2] = "2022";
        String finalString = "\nCreated: ";
        finalString += formatter.format(today);
        finalString += "\nExpires: ";
        finalString += stringDate[0] + "/";
        finalString += stringDate[1];
        finalString += "/";
        finalString += stringDate[2];
        assertEquals(finalString, date.toString());
    }
}