package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Calendar;
import android.widget.CalendarView;
import android.widget.TextView;
import java.util.Date;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MyCalendar extends AppCompatActivity {


    int year;
    int month;
    int day;
    boolean isEven;
    static final public String MYPREFS = "myprefs";
    long startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        setup();

    }

    Date date;

    public Date date_Get(int Day, int Month, int Year){

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Year, Month, Day);
        date = cal.getTime();
        return date;
    }

    public void imp(){
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        int startDate = settings.getInt("startDay",1);
        int startYear = settings.getInt("startYear",1);
        int startMonth = settings.getInt("startMonth",1);
        Date startDay= date_Get(startDate, startMonth, startYear);

        int endDate = settings.getInt("endDay", 1);
        int endYear = settings.getInt("endYear", 1);
        int endMonth = settings.getInt("endMonth",1);
        Date endDay= date_Get(startDate,startMonth,startYear);
    }


    public void setup() {
        final CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        final long currentDate = cv.getDate();
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int grabyear, int grabmonth, int dayOfMonth) {
                if(cv.getDate() < currentDate) {
                    //if its in the past
                    if (dayOfMonth % 2 == 0) {
                        cv.setSelectedWeekBackgroundColor(getResources().getColor(R.color.bpBlue));
                    } else {
                        cv.setSelectedWeekBackgroundColor(getResources().getColor(R.color.bpDarker_red));
                    }
                }else if (cv.getDate() >= currentDate){
                    cv.setSelectedWeekBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                String day = Cal_Proccess.getDay(cv);
                TextView tx = (TextView) findViewById(R.id.test);
                TextView tx2 = (TextView) findViewById(R.id.test2);
                tx.setText(day);
                if(Cal_Proccess.classDay(cv, "MWF")){
                    tx2.setText("You have class");
                }else{
                    tx2.setText("No class today!");
                }

            }
        });

    }





}
