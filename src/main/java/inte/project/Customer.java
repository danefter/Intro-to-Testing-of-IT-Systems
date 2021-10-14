package inte.project;

public class Customer {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;

    public Customer(String name, String address, String email, String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("\\s+", "");
        if(phoneNumber.length() < 7 ||phoneNumber.length() > 16){
            throw new IllegalArgumentException("Phone number seems to be in the wrong format, please try again");
        }
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

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}
