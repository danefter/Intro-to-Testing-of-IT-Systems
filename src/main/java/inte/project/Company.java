package inte.project;

public class Company extends Customer{
    private String orgNumber;


    public Company(String name, String address, String email, String phoneNumber, String orgNumber){
        super(name, address, email, phoneNumber);
        orgNumber = removeSpaces(orgNumber);
        if(orgNumber.length() != 11){ //10 digits and one hyphen gives length of 11
            throw new IllegalArgumentException("Organisation number must be 10 digits");
        }
        this.orgNumber = orgNumber;
    }

    public String getOrgNumber(){
        return orgNumber;
    }

    private String removeSpaces(String orgNumber){
        return orgNumber.replaceAll("\\s+", ""); 
    }
}
