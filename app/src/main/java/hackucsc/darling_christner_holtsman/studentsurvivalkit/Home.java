package hackucsc.darling_christner_holtsman.studentsurvivalkit;
//Code for the home screen.
//Should include links the the different features
//
//
//
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
}
