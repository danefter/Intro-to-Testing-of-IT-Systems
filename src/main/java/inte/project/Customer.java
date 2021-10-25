//Author: Lovisa
package inte.project;
import java.time.LocalDate;
// May introduce null in generateMembership

// Better to save removedmembership as inactive due to potential future functionality, minor

public abstract class Customer {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private boolean member;
    private Membership membership;
    
    public Customer(String name, String address, String email, String phoneNumber){
        phoneNumber = removePotentialSpaces(phoneNumber);
        phoneNumber = removeHyphen(phoneNumber);
        checkForUnacceptableCharacters(phoneNumber);
        checkLengthOfPhoneNumber(phoneNumber);
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        membership = null;
    }

    public boolean isMember(){ 
        return member;
    }

    public void addMembership(){ //create membership better name?
        if(this instanceof PrivatePerson){
            if(((PrivatePerson)this).getYearOfBirth() > LocalDate.now().getYear() - 18){
                throw new IllegalArgumentException("To become a member one needs to be older than 18");
            }
        }
        membership = new Membership();
        this.member = true;
        generateMembershipID();
    }

    public void removeMembership(){
        if(this.isMember()){
            membership = null;
            this.member = false;
        }else{
            throw new IllegalStateException("Customer is not a member");
        }
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String newNumber){
        newNumber = removePotentialSpaces(newNumber);
        newNumber = removeHyphen(newNumber);
        checkForUnacceptableCharacters(newNumber);
        checkLengthOfPhoneNumber(newNumber);
        this.phoneNumber = newNumber;
    }

    public Membership getMembership() throws IllegalArgumentException{
        if(membership != null){
            return membership;
        }else{
            throw new IllegalArgumentException("Customer is not a member");
        }
    }

    private void checkLengthOfPhoneNumber(String number){
        if(number.length() < 7 || number.length() > 16){
            throw new IllegalArgumentException("Phone number seems to be in the wrong format, please try again");
        }
    }

    private String removePotentialSpaces(String number){
        return number = number.replaceAll("\\s+", "");
    }

    private void checkForUnacceptableCharacters(String number){
        if(!number.matches("[0-9]+")){ // if number does not follow digits 0-9
            if(!number.matches("\\+[0-9]+$")){ //if number does not follow the + followed by digits 0-9
                throw new IllegalArgumentException("The phone number can only consist of digits or one plus sign followed by digits");
            }
        }
    }

    private String removeHyphen(String number){
        return number = number.replaceAll("-", "");
    }

    private void generateMembershipID(){
        Customer customer = this;
        if(this instanceof Company){
            Company cu = (Company)  customer;
            getMembership().setMemberID(cu.getOrgNumber().hashCode() * cu.getEmail().hashCode() * 2000);
        }else if(customer instanceof PrivatePerson){
            PrivatePerson cu = (PrivatePerson)customer;
            getMembership().setMemberID(cu.getDateOfBirth().hashCode() * cu.getEmail().hashCode() * 2000);
        }
    }


    public String toString(){
        String stringCustomer = "Name: " + name + "\nAddress: " + address + "\nEmail: " + email + "\nPhonenumber: " + phoneNumber + "\n";
        if(membership != null){
            stringCustomer += getMembership().toString();
        }
        return stringCustomer;
    }
    @Override
    public int hashCode(){
        return name.hashCode() * 1000 + email.hashCode();
    }

}
