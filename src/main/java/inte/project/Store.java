package inte.project;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private String address;
    private int postCode;
    private String city;
    private String phoneNumber;

    private Map<Product,Integer> products = new HashMap<>();

    public Store(String address, int postCode, String city, String phoneNumber) {
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}

