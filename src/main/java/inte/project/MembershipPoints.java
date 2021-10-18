package inte.project;


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

    public int getPoints(){
        return points;
    }

    public String toString(){
        return "Points: " + getPoints() + "\nCreated: " + period.startToString() + "\nExpires: " + period.endToString();
    }

}
