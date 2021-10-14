package inte.project;

public class Product {
    private String Id;
    private String name;
    private double prise;

    public Product (String Id, String name, double prise) {
        //Id must contain 6 numbers
        if (Id.length() != 6) {
            throw new IllegalStateException("Id must contain 6 numbers");
        }
        this.Id = Id;
        this.name = name;
        this.prise = prise;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public double getPrise() {
        return prise;
    }
    public void setPrise(double newPrise) {
        prise = newPrise;
    }

}
