package inte.project;

public class Product {
    private int Id;
    private String name;
    private double prise;

    public Product (int Id, String name, double prise) {
        this.Id = Id;
        this.name = name;
        this.prise = prise;
    }

    public int getId() {
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
