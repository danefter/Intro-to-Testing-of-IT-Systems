//Author: Lovisa & Lukas
package inte.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
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
 /*   private void setMembershipID(Customer customer){ //ID is set via hashCode from orgNumber or just the yearOfBirth (depending on if customer is PrivatePerson or Company)
        if(customer instanceof PrivatePerson){
            PrivatePerson cu = (PrivatePerson)customer;
            customer.getMembership().setMembershipID(cu.getYearOfBirth() * cu.getEmail().hashCode() * 2000);
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
