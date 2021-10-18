package inte.project;

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
    }

    public boolean isMember(){ 
        return member;
    }

    public void setIsMember(boolean value){
        this.member = value; 
    }

    public void setMembership(){ 
       membership = new Membership(this);
       this.member = true;
    }

    public void removeMembership(){
        if(this.isMember()){
            membership.removeMembership(this);
            this.member = false;
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
        this.phoneNumber = newNumber;
    }

    public Membership getMembership(){
        return membership;
    }

    public int hashCode(){ 
        return name.hashCode() * 1000 + email.hashCode();
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

}
