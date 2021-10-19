//Author: Lovisa & Lukas
package inte.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Memebership connected to a customer with creation date and a membershippoints class which exists as a wallet
// that can subtract from a purchase and points beeing added when a purchase is made.

// Författare Lukas

public class Membership {
    private int membershipID;
    private MembershipPoints membershipPoints;
    private String membershipCreatedDate;

    Membership(){
        membershipPoints = new MembershipPoints();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        membershipCreatedDate = formatter.format(today);
    }
    
    Membership(int points){
        membershipPoints = new MembershipPoints(points);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        membershipCreatedDate = formatter.format(today);
    }


    public int getMemberID(){
        return membershipID;
    }

    public MembershipPoints getMembershipPoints(){
        return membershipPoints;
    }
<<<<<<< HEAD
 /*   private void setMembershipID(Customer customer){ //ID is set via hashCode from orgNumber or just the yearOfBirth (depending on if customer is PrivatePerson or Company)
        if(customer instanceof PrivatePerson){
            PrivatePerson cu = (PrivatePerson)customer;
            customer.getMembership().setMembershipID(cu.getYearOfBirth() * cu.getEmail().hashCode() * 2000);
=======

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

    private void setMembershipID(){ //ID is set via hashCode from orgNumber or dateOfBirth (depending on if customer is PrivatePerson or Company)
        Set<Customer> customers = members.keySet(); // multiply by hashCode of email and that multiply by 2000
        Customer customer = null;
        for(Customer c : customers){
            if(members.get(c).equals(this)){
                customer = c;
            }
        }
        if(customer instanceof PrivatePerson){
            PrivatePerson cu = (PrivatePerson)customer;
            membershipID = cu.getDateOfBirth().hashCode() * cu.getEmail().hashCode() * 2000;
>>>>>>> 828ecdf83aec0b25cd5b9296822abcb5eee7258a
        }else if(customer instanceof Company){
            Company cu = (Company)customer;
            customer.getMembership().setMembershipID(cu.getOrgNumber().hashCode() * cu.getEmail().hashCode() * 2000);
        }
    }
*/

    public String toString(){
        return "Membership\n Created on: " + membershipCreatedDate + membershipPoints.toString();
    }
}
