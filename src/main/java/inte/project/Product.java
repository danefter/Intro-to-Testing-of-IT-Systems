package inte.project;
//author Marah Zeibak
public abstract class Product implements Vat {
    private String Id;
    private String name;
    //private double price;
    private Money price;
    private String type;

    public Product (String Id, String name, Money price , String type) {
        //Id must contain 6 numbers
        if (Id.length() != 6) {
            throw new IllegalArgumentException("Id must contain 6 numbers");
        }
        if (price.getAmountInOre() < 0) {
            throw new IllegalArgumentException("price must be more");
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

    public Money getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setPrice(Money newPrice) {
        price = newPrice;
    }

    public double getPricePlusVAT() {
        return  getVat() * getPrice().getAmountInOre() + getPrice().getAmountInOre();
    }


}
