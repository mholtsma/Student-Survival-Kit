package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;


public class Registration_activity extends AppCompatActivity implements DatePickerDialogFragment.DatePickerDialogHandler{
    TextView dateView;
    TextView startDateView;
    TextView endDateView;
    Spinner daySpinner;
    boolean isStart;
    boolean isEnd;
    static final public String MYPREFS = "myprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);

        Button startDateButton = (Button) findViewById(R.id.startDateButton);
        Button endDateButton = (Button) findViewById(R.id.endDateButton);
        dateView = (TextView) findViewById(R.id.dateView);

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment_Light);
                dpb.show();
                startDate();
            }
        });


        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment_Light);
                dpb.show();
                endDate();
            }
        });
    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        if(isStart) {
            startDateView = (TextView) findViewById(R.id.startDateView);
            startDateView.setText(getString(R.string.date_picker_result_value, monthOfYear, dayOfMonth, year));

            editor.putInt("startMonth", monthOfYear);
            editor.putInt("startDay", dayOfMonth);
            editor.putInt("startYear", year);
        } else if(isEnd) {
            endDateView = (TextView) findViewById(R.id.endDateView);
            endDateView.setText(getString(R.string.date_picker_result_value, monthOfYear, dayOfMonth, year));

            editor.putInt("endMonth", monthOfYear);
            editor.putInt("endDay", dayOfMonth);
            editor.putInt("endYear", year);
        }
    }

    public void startDate(){
        isStart = true;
        isEnd = false;
    }

    public void endDate(){
        isStart = false;
        isEnd = true;
    }

    public void submitCLassInfo(View v){
        EditText classText = (EditText) findViewById(R.id.classText);
        EditText unitsText = (EditText) findViewById(R.id.unitsText);
        EditText rText = (EditText) findViewById(R.id.registerEdit);
        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("name", rText.getText().toString());
        editor.putString("class", classText.getText().toString());
        editor.putString("units", unitsText.getText().toString());
        editor.putString("classDay", String.valueOf(daySpinner.getSelectedItem()));
        editor.commit();
        finish();
    }


}