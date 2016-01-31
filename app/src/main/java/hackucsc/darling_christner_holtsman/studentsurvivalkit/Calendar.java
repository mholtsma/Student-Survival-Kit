package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class Calendar extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        setup();

    }

    int year;
    int month;
    int day;
    boolean isEven;

    public void setup() {
        final CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int grabyear, int grabmonth, int dayOfMonth) {
                if(dayOfMonth%2 == 0){
                   cv.setSelectedWeekBackgroundColor(-16776961);
                }else{
                    cv.setSelectedWeekBackgroundColor(-65536);
                }

                String day = Cal_Proccess.getDay(cv, view);
                TextView tx = (TextView) findViewById(R.id.test);
                tx.setText(day);
                //year = grabyear;
               // month = grabmonth;
                //day = dayOfMonth;
            }
        });

    }
}
