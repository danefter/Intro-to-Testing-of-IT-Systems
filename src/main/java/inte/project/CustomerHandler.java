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
    // Adderar privateperson so that they may be found by name, adress, memberID and phone if the person doesn't already exist in the database
    // if not member not added to membershipID hashmap
    public void addCustomer(Customer customer) throws IllegalArgumentException{
        if(!customerHashSet.contains(customer)){
            customerHashSet.add(customer);
            customerHashMapName.put(customer.getName(), customer);
            customerHashMapAdress.put(customer.getAddress(), customer);
            if(customer.isMember()){
                customerHashMapMembershipID.put(customer.getMembership().getMemberID(), customer);
            }
            customerHashMapPhoneNumber.put(customer.getPhoneNumber(), customer);
        }else{
            throw new IllegalArgumentException("Customer already exists");
        }
    }
   /* public void addPrivatePerson(String name, String address, String email, String phoneNumber, int birthYear) throws IllegalArgumentException{
        Customer customer = new PrivatePerson(name, address, email, phoneNumber, birthYear);
        if(!customerHashSet.contains(customer)){
            customerHashSet.add(customer);
            customerHashMapName.put(customer.getName(), customer);
            customerHashMapAdress.put(customer.getAddress(), customer);
            if(customer.isMember()){
                customerHashMapMembershipID.put(customer.getMembership().getMemberID(), customer);
            }
            customerHashMapPhoneNumber.put(customer.getPhoneNumber(), customer);
        }else{
            throw new IllegalArgumentException("Customer already exists");
        }
    }
    */
    public Collection<Customer> getAllCustomers(){
        return Collections.unmodifiableCollection(customerHashSet);
    }
    public Customer getCustomerByName(String name){
        return customerHashMapName.get(name);
    }
    public Customer getCustomerByAdress(String adress){
        return customerHashMapAdress.get(adress);
    }
    public Customer getCustomerByMembershipID(int mID){
        return customerHashMapMembershipID.get(mID);
    }
    public Customer getCustomerByPhoneNumber(String phone){
        return customerHashMapPhoneNumber.get(phone);
    }

}
