package inte.project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Membership {
    MembershipPoints membershipPoints;
    String membershipCreatedDate;

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
    public MembershipPoints getMembershipPoints(){
        return membershipPoints;
    }
    public String toString(){
        return "Membership\n Created on: " + membershipCreatedDate + membershipPoints.toString();
    }
}
