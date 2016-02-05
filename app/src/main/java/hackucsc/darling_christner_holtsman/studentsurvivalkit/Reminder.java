package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Holds the info to send reminders to users
 * Reminds to
 * Created by Bailey on 1/30/2016.
 */

public class Reminder extends Activity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.reciever_reminder);
        }

    public void createReminder(View view) {
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
