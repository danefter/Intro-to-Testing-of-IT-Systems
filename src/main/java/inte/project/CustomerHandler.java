// Author: Lukas
package inte.project;
// Klass som innehåller datastrukturen med alla customers och kan hantera dessa.
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class CustomerHandler {
    private HashSet<Customer> customerHashSet;
    private HashMap<String, Customer> customerHashMapName;
    private HashMap<String, Customer> customerHashMapAdress;
    private HashMap<String, Customer> customerHashMapPhoneNumber;

    // Initierar klass
    public CustomerHandler(){
        customerHashSet = new HashSet<>();
        customerHashMapName = new HashMap<>();
        customerHashMapAdress = new HashMap<>();
        customerHashMapPhoneNumber = new HashMap<>();
    }
    // Adderar privateperson so that they may be found by name, adress, memberID and phone if the person doesn't already exist in the database
    // if not member not added to membershipID hashmap
    public void addCustomer(Customer customer) throws IllegalArgumentException{
        if(!customerHashSet.contains(customer)){
            customerHashSet.add(customer);
            customerHashMapName.put(customer.getName(), customer);
            customerHashMapAdress.put(customer.getAddress(), customer);
            customerHashMapPhoneNumber.put(customer.getPhoneNumber(), customer);
        }else{
            throw new IllegalArgumentException("Customer already exists");
        }
    }

    public Collection<Customer> getAllCustomers(){
        return Collections.unmodifiableCollection(customerHashSet);
    }

    public Customer getCustomerByName(String name) throws IllegalArgumentException{
        if(customerHashMapName.containsKey(name)){
            return customerHashMapName.get(name);
        }else{
            throw new IllegalArgumentException("No costumer with that name exists");
        }
    }

    public Customer getCustomerByAdress(String adress){
        if(customerHashMapAdress.containsKey(adress)){
            return customerHashMapAdress.get(adress);
        }else{
            throw new IllegalArgumentException("No costumer with that address exists");
        }
    }

    public Customer getCustomerByPhoneNumber(String phone){
        if(customerHashMapPhoneNumber.containsKey(phone)){
            return customerHashMapPhoneNumber.get(phone);
        }else{
            throw new IllegalArgumentException("No costumer with that phonenumber exists");
        }
    }

    public void removeCustomer(Customer customer){
        customerHashSet.remove(customer);
        customerHashMapName.remove(customer.getName());
        customerHashMapAdress.remove(customer.getAddress());
        customerHashMapPhoneNumber.remove(customer.getPhoneNumber());
    }

}
