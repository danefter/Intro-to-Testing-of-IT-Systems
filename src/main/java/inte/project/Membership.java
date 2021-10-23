//Author: Lovisa & Lukas
package inte.project;

import java.text.SimpleDateFormat;
import java.util.Date;

// Memebership connected to a customer with creation date and a membershippoints class which exists as a wallet
// that can subtract from a purchase and points beeing added when a purchase is made.

public class Membership {
    private int membershipID;
    private MembershipPoints membershipPoints;
    private final String membershipCreatedDate;

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

    public String getMembershipCreatedDate(){
        return membershipCreatedDate;
    }

    public String toString(){
        return "Membership\n Created on: " + membershipCreatedDate + "\n" + membershipPoints.toString() + "\n";
    }
}
