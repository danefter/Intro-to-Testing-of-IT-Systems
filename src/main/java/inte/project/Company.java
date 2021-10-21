//Author: Lovisa 
package inte.project;

public class Company extends Customer{
    private String orgNumber;

    public Company(String name, String address, String email, String phoneNumber, String orgNumber){
        super(name, address, email, phoneNumber);
        orgNumber = removeSpaces(orgNumber);
        checkForUnacceptableCharacters(orgNumber);
        if(orgNumber.length() != 11){ //10 digits and one hyphen gives length of 11
            throw new IllegalArgumentException("Organisation number must consist of 10 digits devided by a hyphen");
        }
        this.orgNumber = orgNumber;
    }

    public String getOrgNumber(){
        return orgNumber;
    }

    private String removeSpaces(String orgNumber){
        return orgNumber.replaceAll("\\s+", ""); 
    }

    private void checkForUnacceptableCharacters(String number){
        String temp = number;
        temp = temp.replaceAll("-", ""); 
        if(!temp.matches("[0-9]+")){ // if number does not follow digits 0-9
            throw new IllegalArgumentException("The organisation number can only consist of 10 digits and one hyphen");
        }
    }

    public String toString(){
        return "Organisation number: " + orgNumber;
    }
}
