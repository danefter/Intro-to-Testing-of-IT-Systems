package inte.project;


public class MembershipPoints {
    int points;
    Period period;

    MembershipPoints(){
        points = 0;
    }
    MembershipPoints(int points){
        if(points < 0){
            this.points = 0;
        }else{
            this.points = points;
        }
        Period period = new Period();
    }
    MembershipPoints(double points){
        if(points < 0){
            this.points = 0;
        }else{
            this.points = (int)Math.round(points);
        }
    }
    public void addPoints(int points){
        this.points += points;
    }

    public Period getPeriod(){
        return period;
    }

    public int getPoints(){
        return points;
    }

    public String toString(){
        return "Points: " + getPoints() + "\nCreated: " + period.startToString() + "\nExpires: " + period.endToString();
    }

}
