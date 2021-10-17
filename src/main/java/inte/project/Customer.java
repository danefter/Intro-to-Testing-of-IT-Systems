package inte.project;

public abstract class Customer {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    
    public Customer(String name, String address, String email, String phoneNumber){
        phoneNumber = removePotentialSpaces(phoneNumber);
        phoneNumber = checkForUnacceptableCharacters(phoneNumber);
        checkLengthOfPhoneNumber(phoneNumber);
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    private void checkLengthOfPhoneNumber(String number){
        if(number.length() < 7 || number.length() > 16){
            throw new IllegalArgumentException("Phone number seems to be in the wrong format, please try again");
        }
    }

    private String removePotentialSpaces(String number){
        return number = number.replaceAll("\\s+", "");
    }

    private String checkForUnacceptableCharacters(String number){
        if(number.contains("-")){ 
            number = removeHyphen(number);
        }
        if(!number.matches("[0-9]+")){ // if number does not follow digits 0-9
            if(!number.matches("\\+[0-9]+$")){ //if number does not follow the + followed by digits 0-9
                throw new IllegalArgumentException("The phone number cab only consist of digits or one plus sign followed by digits");
            }
        }
        return number;
    }

    private String removeHyphen(String number){
        return number = number.replaceAll("-", "");
    }

}
