package inte.project;


public class PrivatePerson extends Customer{
    private int yearOfbirth;
    
    public PrivatePerson(String name, String address, String email, String phoneNumber, int yearOfBirth){
        super(name, address, email, phoneNumber);
        this.yearOfbirth = yearOfBirth;
    }

    public int getYearOfBirth(){
        return yearOfbirth;
    }

    
}
