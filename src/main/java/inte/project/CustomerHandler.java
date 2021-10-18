package inte.project;
// Klass som innehåller datastrukturen med alla customers och kan hantera dessa.
import java.util.HashMap;
import java.util.HashSet;

public class CustomerHandler {
    private HashSet<Customer> customerHashSet;
    private HashMap<String, Customer> customerHashMapName;
    private HashMap<Integer, Customer> customerHashMapMembershipID;
    private HashMap<String, Customer> customerHashMapAdress;
    private HashMap<String, Customer> customerHashMapPhoneNumber;


    // Initierar klass
    CustomerHandler(){
        customerHashSet = new HashSet<>();
        customerHashMapMembershipID = new HashMap<>();
        customerHashMapName = new HashMap<>();
        customerHashMapAdress = new HashMap<>();
        customerHashMapPhoneNumber = new HashMap<>();
    }
    public void addPrivatePerson(String name, String address, String email, String phoneNumber){
        Customer customer = new PrivatePerson(name, address, email, phoneNumber);
        if(!customerHashSet.contains(customer)){
            customerHashSet.add(customer);
            customerHashMapName.put(customer.getName(), customer);
            customerHashMapAdress.put(customer.getAddress(), customer);
            customerHashMapMembershipID.put(customer.getMembership().getMemberID(), customer);
            customerHashMapPhoneNumber.put(customer.getPhoneNumber(), customer);
        }

    }
    public Customer getCustomerByName(String name){
        return customerHashMapName.get(name);
    }
    public Customer getCustomerByName(String adress){
        return customerHashMapName.get(adress);
    }
    public Customer getCustomerByName(String name){
        return customerHashMapName.get(name);
    }
    public Customer getCustomerByName(String name){
        return customerHashMapName.get(name);
    }

}
