//Author: Lovisa & Lukas
package inte.project;

import java.text.SimpleDateFormat;
import java.util.Date;

// Memebership connected to a customer with creation date and a membershippoints class which exists as a wallet
// that can subtract from a purchase and points beeing added when a purchase is made.

public class Membership {
    private int membershipID;
    private MembershipPoints membershipPoints;
    private String membershipCreatedDate;

    public Membership(){
        membershipPoints = new MembershipPoints();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        membershipCreatedDate = formatter.format(today);
    }

    public Membership(int points){
        membershipPoints = new MembershipPoints(points);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        membershipCreatedDate = formatter.format(today);
    }

    public int getMemberID(){
        return membershipID;
    }
    public void setMemberID(int mID){
        membershipID = mID;
    }

    public MembershipPoints getMembershipPoints(){
        return membershipPoints;
    }

 /*   private void setMembershipID(Customer customer){ //ID is set via hashCode from orgNumber or just the yearOfBirth (depending on if customer is PrivatePerson or Company)
        if(customer instanceof PrivatePerson){
            PrivatePerson cu = (PrivatePerson)customer;
            customer.getMembership().setMembershipID(cu.getYearOfBirth() * cu.getEmail().hashCode() * 2000);


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


*/

    public String toString(){
        return "Membership\n Created on: " + membershipCreatedDate + membershipPoints.toString() + "\n";
    }
}
