package inte.project;

// Wallet with points and the period which the points are valid. When a member checks out/makes a purchase
// the class can be called to add and subtract points to the member. Thought is that the register(or other) handles the
// points to determine what amount should be subtracted or added.

public class MembershipPoints {
    private int points;
    private Period period;

    public MembershipPoints(){
        points = 0;
    }
    public MembershipPoints(int points){
        if(points < 0){
            this.points = 0;
        }else{
            this.points = points;
        }
        Period period = new Period();
    }
    public MembershipPoints(double points){
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
            this.points += addPoints;
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

    public int getAllPoints(){
        return points;
    }

    public int getCertainAmountOfPoints(int amount){ 
        if(points < amount){
            throw new IllegalArgumentException("You dont have enough points to collect");
        }else{
            points -= amount;
        }
        return amount;
    }

    public String toString(){
        return "Points: " + getAllPoints() + "\nCreated: " + period.startToString() + "\nExpires: " + period.endToString();
    }

}
