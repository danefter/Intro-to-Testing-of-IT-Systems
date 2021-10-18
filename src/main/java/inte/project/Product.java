package inte.project;

public class Product {
    private String Id;
    private String name;
    private double price;
    private ProductType type;

    public Product (String Id, String name, double price , ProductType type) {
        //Id must contain 6 numbers
        if (Id.length() != 6) {
            throw new IllegalStateException("Id must contain 6 numbers");
        }
        this.Id = Id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }
    public void setPrice(double newPrice) {
        price = newPrice;
    }

}
