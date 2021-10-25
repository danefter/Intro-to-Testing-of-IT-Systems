package inte.project;

public class Employee {
    private final String firstName;
    private final String latsName;
    private String roller;
    private int salary;

    private Store store;

    public Employee(String firstName, String latsName, String roller, int salary) {
        if (firstName.equals("") || latsName.equals("") || roller.equals(""))
            throw new IllegalArgumentException("måste innehåller riktig namn");
        this.firstName = firstName;
        this.latsName = latsName;
        this.roller = roller;
        if (salary < 12000)
            throw new IllegalArgumentException("måste vara mer");
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLatsName() {
        return latsName;
    }

    public String getRoller() {
        return roller;
    }

    public void setRoller(String newRoller) {
        roller = newRoller;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int newSalary) {
        salary = newSalary;
    }

    public void addStore(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }


    public String toString() {
        return firstName + " " + latsName  + " " + roller + " " + salary;
    }

}
