package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.CalendarView;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bailey on 1/30/2016.
 * Handles all of the technical
 */
public class Cal_Proccess {

    //Takes input date and returns the day of the week
    public static String getDay(CalendarView cv) {
        Calendar cal = Calendar.getInstance();
        Long selected =  cv.getDate();
        cal.setTimeInMillis(selected);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek==1){       return "Sun";
        }else if(dayOfWeek==2){ return "Mon";
        }else if(dayOfWeek==3){ return "Tues";
        }else if(dayOfWeek==4){ return "Wed";
        }else if(dayOfWeek==5){ return "Thu";
        }else if(dayOfWeek==6){ return "Fri";
        }else if(dayOfWeek==7){ return "Sat";}

        else{
            return "err";
        }
    }


    Date date;

    public void date_Get(int Day, int Month, int Year){

        Calendar cal = Calendar.getInstance();
        cal.set(Year, Month, Day);
        date = cal.getTime();
    }

    public static boolean classDay(CalendarView cv, String days){
        String Day = getDay(cv);

        if(days.equals("MWF")){
            //Classes are on mondays, wednesdays, and fridays
            //inform them they have class
            if(Day.equals("Mon") || Day.equals("Wed") || Day.equals("Fri")){
                return true;
            }else{
                return false;
            }
        }else if(days.equals("TuTh")){
            // Tuesday Thursday classes
            //returns true if Tuesday or thursday is selected
            if(Day.equals("Tues") || Day.equals("Thu")){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }



}
