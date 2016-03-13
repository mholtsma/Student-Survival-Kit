package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

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
    String startDate ="";
    String endDate = "";
    boolean isStart;
    boolean isEnd;
    ClassDbHelper mDbHelper = new ClassDbHelper(this);
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
    //Got this code from:
    //https://github.com/code-troopers/android-betterpickers
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        if(isStart) {
            startDateView = (TextView) findViewById(R.id.startDateView);
            startDateView.setText(getString(R.string.date_picker_result_value, monthOfYear, dayOfMonth, year));
            startDate = getString(R.string.date_picker_result_value, monthOfYear, dayOfMonth, year);

        } else if(isEnd) {
            endDateView = (TextView) findViewById(R.id.endDateView);
            endDateView.setText(getString(R.string.date_picker_result_value, monthOfYear, dayOfMonth, year));
            endDate = getString(R.string.date_picker_result_value, monthOfYear, dayOfMonth, year);

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

    public void submitClassInfo(View v){
        EditText classText = (EditText) findViewById(R.id.classText);
        EditText unitsText = (EditText) findViewById(R.id.unitsText);
        EditText rText = (EditText) findViewById(R.id.registerEdit);
        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        int newId = settings.getInt("id", 1);
        newId+=1;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id", newId);
        editor.commit();

        ContentValues values = new ContentValues();
        values.put(ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID, newId);
        values.put(ClassReaderContract.ClassEntry.COLUMN_CLASS, classText.getText().toString());
        values.put(ClassReaderContract.ClassEntry.COLUMN_UNITS, unitsText.getText().toString());
        values.put(ClassReaderContract.ClassEntry.COLUMN_CLASS_DAYS, String.valueOf(daySpinner.getSelectedItem()));
        values.put(ClassReaderContract.ClassEntry.COLUMN_START_DATE, startDate);
        values.put(ClassReaderContract.ClassEntry.COLUMN_END_DATE, endDate);

        long newRowId;
        newRowId = db.insert(
                ClassReaderContract.ClassEntry.TABLE_NAME,
                null,
                values);

      /*  EditText classText = (EditText) findViewById(R.id.classText);
        EditText unitsText = (EditText) findViewById(R.id.unitsText);
        EditText rText = (EditText) findViewById(R.id.registerEdit);
        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("name", rText.getText().toString());
        editor.putString("class", classText.getText().toString());
        editor.putString("units", unitsText.getText().toString());
        editor.putString("classDay", String.valueOf(daySpinner.getSelectedItem()));
        editor.commit(); */
        finish();
    }


}
