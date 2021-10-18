package inte.project;

public abstract class Product implements Vat {
    private String Id;
    private String name;
    private double price;
    private String type;

    public Product (String Id, String name, double price , String type) {
        //Id must contain 6 numbers
        if (Id.length() != 6) {
            throw new IllegalStateException("Id must contain 6 numbers");
        }
        if (price < 0) {
            throw new IllegalStateException("price must be more");
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

    public String getType() {
        return type;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }

    public double getPricePlusVAT() {
        return  getVat() * getPrice() + getPrice();
    }


}
