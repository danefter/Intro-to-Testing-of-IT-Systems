//Author: Lovisa
package inte.project;

import java.time.LocalDate;

public class PrivatePerson extends Customer{
    private static final int FEBRUARY = 2; 
    private final String dateOfBirth;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private final int[] monthsWith30Days = {4, 6, 9, 11};
    private final int[] monthsWith31Days = {1, 3, 5, 7, 8, 10, 12};
    
    public PrivatePerson(String name, String address, String email, String phoneNumber, String dateOfBirth){
        super(name, address, email, phoneNumber);
        checkDateToBeInTheRightFormat(dateOfBirth);
        if(yearOfBirth < 1900){
            throw new IllegalArgumentException("Year of birth is too low, please try again");
        }
        if(yearOfBirth > LocalDate.now().getYear()){
            throw new IllegalArgumentException("Year of birth can not be in the future, please try again");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth(){ //in format yyyy-mm-dd
        return dateOfBirth;
    } 

    public int getYearOfBirth(){
        return yearOfBirth;
    }

    public int getMonthOfBirth(){
        return monthOfBirth;
    }

    public int getDayOfBirth(){
        return dayOfBirth;
    }

    private void checkDateToBeInTheRightFormat(String date){ //format should be in yyyy-mm-dd 
        date = date.replaceAll("\\s+", "");
        try{
            String[] dateSplitted = date.split("-");
            int yearOfDate = Integer.parseInt(dateSplitted[0]);
            int monthOfDate = Integer.parseInt(dateSplitted[1]);
            int dayOfDate = Integer.parseInt(dateSplitted[2]);
            checkRulesOfMonths(yearOfDate, monthOfDate, dayOfDate);
            setYearMonthAndDay(yearOfDate, monthOfDate, dayOfDate);
        }catch(IllegalArgumentException m){
            System.err.println("Please write in format yyyy-mm-dd");
        }
    }

    private void checkRulesOfMonths(int yearOfDate, int monthOfDate, int dayOfDate){
        if(monthOfDate < 1 || monthOfDate > 12){
            throw new IllegalArgumentException("Month needs to be between 1 and 12");
        }
        if(monthOfDate == FEBRUARY){
            checkRulesForFebruary(dayOfDate, yearOfDate);
        }
        for(int i = 0; i < monthsWith30Days.length; i++){
            if(monthOfDate == monthsWith30Days[i] && dayOfDate > 30){
                throw new IllegalArgumentException("There are only 30 days in this month");
            }
        }
        for(int i = 0; i < monthsWith31Days.length; i++){
            if(monthOfDate == monthsWith31Days[i] && dayOfDate > 31){
                throw new IllegalArgumentException("There are only 31 days in this month");
            }
        }
    }

    private void checkRulesForFebruary(int day, int year){
        if(day > 29 && leapyear(year)){
            throw new IllegalArgumentException("This year is a leapyear, so there are only 29 days this month");
        }else if(day > 28 && !leapyear(year)){
            throw new IllegalArgumentException("There are only 28 days this month");
        }
    }

    private boolean leapyear(int year){
        if(year % 4 == 0 || year % 100 == 0 || year % 400 == 0){
            return true;
        }
        return false;
    }

    private void setYearMonthAndDay(int yearOfDate, int monthOfDate, int dayOfDate){
        this.yearOfBirth = yearOfDate;
        this.monthOfBirth = monthOfDate;
        this.dayOfBirth = dayOfDate;
    }
}
