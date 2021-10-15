package inte.project;

import java.text.SimpleDateFormat;

import java.util.Date;


public class Period {
    private int startYear, startMonth, startDay, endYear, endMonth, endDay;


    public Period() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String[] dateString = formatter.format(date).split("/");
        this.startYear = Integer.parseInt(dateString[2]);
        this.startMonth = Integer.parseInt(dateString[1]);
        this.startDay = Integer.parseInt(dateString[0]);
        this.endYear = Integer.parseInt(dateString[2]) + 1;
        this.endMonth = Integer.parseInt(dateString[1]);
        this.endDay = Integer.parseInt(dateString[0]);
    }
    public int getStartYear(){
        return startYear;
    }
    public int getStartMonth(){
        return startMonth;
    }
    public int getStartDay(){
        return startDay;
    }
    public int getEndYear(){
        return endYear;
    }
    public int getEndMonth(){
        return endMonth;
    }
    public int getEndDay(){
        return endDay;
    }

    public String startToString(){
        return  startDay + "/" + startMonth + "/" + startYear;
    }
    public String endToString(){
        return endDay + "/" + endMonth + "/" + endYear;
    }
}
