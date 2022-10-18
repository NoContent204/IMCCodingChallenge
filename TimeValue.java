package Round1;

public class TimeValue {
    

    public static void main(String[] args) {
        System.out.println(calculateTime(365,0,1));
        
    }

    public static double calculateTime(int days, int hours, int minutes) {
        //throw new UnsupportedOperationException("Function not yet implemented.");
        if ((days>10000) || (hours>23) || (minutes>59)){
         	return 0;  
        }else{
            double daysValue = (double) days/365;
            double hoursValue = (double) hours/8760;
            double mintuesValue = (double) minutes/525600;

            return daysValue+hoursValue+mintuesValue;


        	
        }
        
    }
}
