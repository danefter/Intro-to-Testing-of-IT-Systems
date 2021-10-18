package inte.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Membership {
    private int membershipID;
    private MembershipPoints membershipPoints;
    private String membershipCreatedDate;
    private HashMap<Customer, Membership> members = new HashMap<>();

    Membership(Customer customer){
        addMember(customer);
        setMembershipID(); 
        membershipPoints = new MembershipPoints();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        membershipCreatedDate = formatter.format(today);
    }
    
    /*Membership(int points){
        membershipPoints = new MembershipPoints(points);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        membershipCreatedDate = formatter.format(today);
    }
    */

    public int getMemberID(){
        return membershipID;
    }

    public MembershipPoints getMembershipPoints(){
        return membershipPoints;
    }

    public Membership getMembership(Customer customer){ 
        return members.get(customer);
    }

    private void addMember(Customer customer){
        if(!members.containsKey(customer)){
            members.put(customer, this);
        }      
        customer.setIsMember(true);
    }

    public void removeMembership(Customer customer){ 
        if(members.containsKey(customer)){
            members.remove(customer);
        }
        customer.setIsMember(false);
    }

    public Customer getMember(int id){ 
        Customer customerToBeReturned = null;
        Iterator<Map.Entry<Customer, Membership>> iter = members.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Customer, Membership> entry = iter.next();
            Customer customer = entry.getKey();
            Membership membership = entry.getValue();
            if(membership.getMemberID() == id){
                customerToBeReturned = customer;
            }
        }
        return customerToBeReturned;
    }   

    private void setMembershipID(){
        Random rnd = new Random(); 
        int n = 100000 + rnd.nextInt(900000);
        membershipID = n;
    }

    public String toString(){
        return "Membership\n Created on: " + membershipCreatedDate + membershipPoints.toString();
    }
}
