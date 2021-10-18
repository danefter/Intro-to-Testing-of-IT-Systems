package inte.project;

// Wallet with points and the period which the points are valid. When a member checks out/makes a purchase
// the class can be called to add and subtract points to the member. Thought is that the register(or other) handles the
// points to determine what amount should be subtracted or added.

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
    // Lägg till if empty add period and points
    public void addPoints(int addPoints) throws IllegalArgumentException{
        if(addPoints < 0){
            throw new IllegalArgumentException("Added point can't be negative");
        }else{
            this.points += points;
        }
    }
    // Illegalarg if points becomes negative
    public void subtractPoints(int reduction) throws IllegalArgumentException{
        if(points - reduction < 0){
            throw new IllegalArgumentException("Not enough points for the subtraction");
        }else{
            points -= reduction;
        }
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
