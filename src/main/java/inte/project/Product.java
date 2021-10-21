package inte.project;
//author Marah Zeibak
public abstract class Product implements Vat , Comparable<Product>{
    private final String Id;
    private final String name;
    //private double price;
    private Money price;
    private final String type;
    private Store store;

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

    public int getPricePlusVAT() {

        return  (int)(getVat() * getPrice().getAmountInOre() + getPrice().getAmountInOre());
    }

    public int getVatValue() {
        int pricePlusVat = getPricePlusVAT();
        int originalPrice = getPrice().getAmountInOre();
        return pricePlusVat - originalPrice;
    }

    public void addStore(Store s) {
        this.store = s;
    }

    public void addProductsToStore(Store s , int quantity) {
        s.addProduct(this,quantity);
    }

    public Store getStore() {
        return store;
    }


    public int compareTo(Product product) {
        if (price.getAmountInOre() < product.getPrice().getAmountInOre())
            return -1;
        else if(price.getAmountInOre() > product.getPrice().getAmountInOre())
            return 1;
        else if(Id.compareTo(product.getId()) < product.getId().compareTo(Id))
            return -1;
        else if(Id.compareTo(product.getId()) > product.getId().compareTo(Id))
            return 1;
        else
            return 0;
    }

    public String toString() {
        return Id + " " + name + " " + price + " " +type + " Store: " + store;
    }

}
