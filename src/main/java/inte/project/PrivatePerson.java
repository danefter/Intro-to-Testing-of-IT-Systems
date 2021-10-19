//Author: Lovisa
package inte.project;

import java.time.LocalDate;

public class PrivatePerson extends Customer{
    private int yearOfBirth;
    
    public PrivatePerson(String name, String address, String email, String phoneNumber, int yearOfBirth){
        super(name, address, email, phoneNumber);
        if(yearOfBirth < 1900){
            throw new IllegalArgumentException("Year of birth is too low, please try again");
        }
        if(yearOfBirth > LocalDate.now().getYear()){
            throw new IllegalArgumentException("Year of birth can not be in the future, please try again");
        }
        this.yearOfBirth = yearOfBirth;
    }

    public int getYearOfBirth(){
        return yearOfBirth;
    }

    
}
