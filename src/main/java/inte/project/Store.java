package inte.project;
//author Marah Zeibak

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Store {
    private String address;
    private int postCode;
    private final String city;
    private String phoneNumber;

    protected Map<Product,Integer> products = new TreeMap<>();

    protected ArrayList<Employee> employees = new ArrayList<>();

    public Store(String address, int postCode, String city, String phoneNumber) {
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        checkPhoneNumberISDigits(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int newPostCode) {
        postCode = newPostCode;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

    public int getQuantity(Product product) {
        return products.get(product);
    }

    //add product
    public void addProduct(Product product, int quantity) {
        if (!products.containsKey(product)) {
            products.put(product,quantity);
        } else {
            int q = products.get(product) + quantity;
            products.put(product,q);
        }
        product.addStore(this);
    }

    //delete product
    public void deleteProduct(Product product, int quantity) {
        if (!products.containsKey(product) || quantity > products.get(product))
            throw new IllegalStateException("");

        int q = products.get(product) - quantity ;
        products.put(product,q);

    }

    //search product after type
    public String searchProduct(String type) {
        String script = "";
        for (Map.Entry<Product,Integer> map : products.entrySet()) {
            Product p = map.getKey();
            int q = map.getValue();
            if(p.getType().equals(type)) {
                script += p + " " + q + "\n";
            }
        }
        return script;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.addStore(this);
    }

    public void checkPhoneNumberISDigits(String phoneNumber) {
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Phone Number must contain only digits");
            }
        }
    }

    public String toString() {
        return address +" " + postCode + " " + city + " " + phoneNumber;
    }


}

