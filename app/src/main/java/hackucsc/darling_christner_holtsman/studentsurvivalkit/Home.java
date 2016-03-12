package hackucsc.darling_christner_holtsman.studentsurvivalkit;
//Code for the home screen.
//Should include links the the different features
//
//
// This comment is to test Ian's commit blah
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void calPress(View view){
        Intent intent= new Intent (this, Calendar.class);
        startActivity(intent);
    }

    public void gradeTrack(View view){
        Intent intent = new Intent (this, GradeTracker.class);
        startActivity(intent);
    }

    public void registerClass(View v){
        Intent registerIntent = new Intent(this, Registration_activity.class);
        startActivity(registerIntent);
    }
    public void Remind(View v){
        Intent intent = new Intent(this, Reciever_Reminder.class);
        PendingIntent pend = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification w/ no action
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Test test test!")
                .setContentText("This is a test")//.build();
                .setContentIntent(pend)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.bp_material_button_selected)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
    }
}