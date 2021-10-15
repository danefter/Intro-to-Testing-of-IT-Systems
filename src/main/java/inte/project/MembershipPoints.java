package inte.project;


public class MembershipPoints {
    int points;
    Period period;

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

    public Period getPeriod(){
        return period;
    }

    public int getPoints(){
        return points;
    }

}
