package inte.project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductType {
    private String type;
    private Map<ProductType,Collection<Product>> collection = new HashMap<>();

    public ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    //add product

    //delet product

    //search product
}
