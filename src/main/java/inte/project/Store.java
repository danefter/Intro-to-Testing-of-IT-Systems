package inte.project;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private String address;
    private int postCode;
    private String city;
    private String phoneNumber;

    protected Map<Product,Integer> products = new HashMap<>();

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

    public int getQuantity(Product product) {
        return products.get(product);
    }

    //add product
    public void addProduct(Product product, int qua) {
        if (!products.containsKey(product)) {
            products.put(product,qua);
        } else {
            int q = products.get(product) + qua;
            products.put(product,q);
        }
    }

    //delete product
    public void deleteProduct(Product product, int qua) {
        if (qua > products.get(product))
            throw new IllegalStateException("");

        int q = products.get(product) - qua ;
        products.put(product,q);

    }

    //search product after type
    public Map<Product,Integer> searchProduct(String type) {
        Map<Product,Integer> productsType = new HashMap<>();
        for (Map.Entry<Product,Integer> me : products.entrySet()) {
            Product p = me.getKey();
            int q = me.getValue();
            if(p.getType().equals(type)) {
                productsType.put(p,q);
            }
        }
        return productsType;
    }


}

