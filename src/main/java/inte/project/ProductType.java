package inte.project;

import java.util.*;

public class ProductType {
    private String type;
    private ArrayList<Product> products = new ArrayList<>();

    public ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    //add product
    public void addProduct(Product product) {
        if(getType().equals(product.getType().getType())) {
            products.add(product);
        }
    }

    //delete product
    public void deleteProduct(Product product) {
        if(getType().equals(product.getType().getType())) {
            products.remove(product);
        }
    }

    //search product
}
