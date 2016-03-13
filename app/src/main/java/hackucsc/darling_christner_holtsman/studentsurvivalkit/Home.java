package hackucsc.darling_christner_holtsman.studentsurvivalkit;
//Code for the home screen.
//Should include links the the different features
//
//
//
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /*Buttons*/

    public void calPress(View view){
        Intent Calintent= new Intent (this, MyCalendar.class);
        startActivity(Calintent);
    }

    public void gradeTrack(View view){
        Intent intent = new Intent (this, GradeTracker.class);
        startActivity(intent);
    }

    public void registerClass(View v){
        Intent registerIntent = new Intent(this, Registration_activity.class);
        startActivity(registerIntent);
    }


    /*Creates a new notifiction*/
    public void Remind(View v){
        ClassDbHelper mDbHelper = new ClassDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ClassReaderContract.ClassEntry._ID,
                ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID,
                ClassReaderContract.ClassEntry.COLUMN_CLASS,
                ClassReaderContract.ClassEntry.COLUMN_UNITS,
                ClassReaderContract.ClassEntry.COLUMN_CLASS_DAYS,
                ClassReaderContract.ClassEntry.COLUMN_START_DATE,
                ClassReaderContract.ClassEntry.COLUMN_END_DATE,
        };

        Cursor c = db.query(
                ClassReaderContract.ClassEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        int itemId = c.getColumnIndexOrThrow(ClassReaderContract.ClassEntry.COLUMN_CLASS);
        String className = c.getString(itemId);

        Intent intent = new Intent(this, Reciever_Reminder.class);
        PendingIntent pend = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification w/ no action
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Test of database!")
                .setContentText(className)//.build();
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