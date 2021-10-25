package inte.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    private static final String FIRSTNAME = "Andres";
    private static final String LASTNAME = "Svansson";
    private static final String ROLLER = "junior";
    private static final String NEW_ROLLER = "manager";
    private static final int SALARY = 30000;
    private static final int NEW_SALARY = 40000;
    private Employee employee;

    @Test
    void constructorFirstName() {
        employee = new Employee(FIRSTNAME,"Larsson","Manager",45000);

        assertEquals(FIRSTNAME, employee.getFirstName());
    }

    @Test
    void constructorInvalidFirstName() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Employee("","Ericksson","manager",30000);
        });

        assertEquals("måste innehåller riktig namn", e.getMessage());
    }


    @Test
    void constructorLastName() {
        employee = new Employee("Anna", LASTNAME,"Junior",30000);

        assertEquals(LASTNAME,employee.getLatsName());
    }

    @Test
    void constructorInvalidLastName() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Employee("anna","","manager",30000);
        });

        assertEquals("måste innehåller riktig namn", e.getMessage());
    }

    @Test
    void constructorRoller() {
        employee = new Employee("Sanna","Ericksson",ROLLER,50000);

        assertEquals(ROLLER,employee.getRoller());
    }

    @Test
    void constructorInvalidRoller() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Employee("anna","svansson","",30000);
        });

        assertEquals("måste innehåller riktig namn", e.getMessage());
    }

    @Test
    void testSetRoller() {
        employee = new Employee("Sanna","Ericksson",ROLLER,50000);
        employee.setRoller(NEW_ROLLER);

        assertEquals(NEW_ROLLER,employee.getRoller());
    }

    @Test
    void constructorSalary() {
        employee = new Employee("Sanna","Ericksson","manager",SALARY);

        assertEquals(SALARY,employee.getSalary());
    }

    @Test
    void testSetSalary() {
        employee = new Employee("Sanna","Ericksson","manager",SALARY);
        employee.setSalary(NEW_SALARY);

        assertEquals(NEW_SALARY,employee.getSalary());
    }

    @Test
    void testInvalidSalary() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Employee("anna","svansson","manager",11000);
        });

        assertEquals("måste vara mer", e.getMessage());
    }

    @Test
    void testAddStore() {
        Store store = new Store("Vasagata 12",12456,"Stockholm","0706524324");
        employee = new Employee("Sanna","Ericksson","manager",30000);

        store.addEmployee(employee);

        assertEquals(store, employee.getStore());
    }

    @Test
    void testToStringMethod() {
        employee = new Employee("Sanna","Ericksson","manager",30000);
        String script = employee.getFirstName() + " " + employee.getLatsName() + " " + employee.getRoller() + " " + employee.getSalary();

        assertEquals(script, employee.toString());

    }

}