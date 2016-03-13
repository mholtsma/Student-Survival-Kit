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
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
//asd
//implements DatePickerDialogFragment.DatePickerDialogHandler
public class Registration_activity extends AppCompatActivity{
    Calendar currentDate = Calendar.getInstance();
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

  /*      Button startDateButton = (Button) findViewById(R.id.startDateButton);
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
        }); */
    }

  //  @Override
    //Got this code from:
    //https://github.com/code-troopers/android-betterpickers
 /*   public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
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
    } */

    public void submitClassInfo(View v){
        EditText classText = (EditText) findViewById(R.id.classText);
        EditText unitsText = (EditText) findViewById(R.id.unitsText);
        EditText hours = (EditText) findViewById(R.id.hours);
        //daySpinner = (Spinner) findViewById(R.id.daySpinner);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        int newId = settings.getInt("class_id", 1);
        newId+=1;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("class_id", newId);
        editor.commit();

        ContentValues values = new ContentValues();
        values.put(ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID, newId);
        values.put(ClassReaderContract.ClassEntry.COLUMN_CLASS, classText.getText().toString());
        values.put(ClassReaderContract.ClassEntry.COLUMN_UNITS, unitsText.getText().toString());
        values.put(ClassReaderContract.ClassEntry.COLUMN_STUDY_HOURS, hours.getText().toString());
        //values.put(ClassReaderContract.ClassEntry.COLUMN_CLASS_DAYS, String.valueOf(daySpinner.getSelectedItem()));
        //values.put(ClassReaderContract.ClassEntry.COLUMN_START_DATE, startDate);
        //values.put(ClassReaderContract.ClassEntry.COLUMN_END_DATE, endDate);

        long newRowId;
        newRowId = db.insert(
                ClassReaderContract.ClassEntry.TABLE_NAME,
                null,
                values);
        //calcClassDays(startDate, endDate, String.valueOf(daySpinner.getSelectedItem()));

        finish();
    }


  /*  public void calcClassDays(String startDate,String endDate, String classDays){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        EditText classText = (EditText) findViewById(R.id.classText);
        int newId = settings.getInt("date_id", 1);
        SharedPreferences.Editor editor = settings.edit();
        String delims = "[,]+";
        String[] tokens = classDays.split(delims);
        Calendar cal = (Calendar) currentDate.clone();
        ParsePosition par = new ParsePosition(0);
        DateFormat df = SimpleDateFormat.getDateInstance();
        Date sDate = df.parse(startDate, par);
        Date eDate = df.parse(endDate,par);
        cal.setTime(sDate);
        if(tokens.length == 2){
            int tmp = cal.get(Calendar.DAY_OF_WEEK);
            String tString = Integer.toString(tmp);
            Date tDate = df.parse(tString,par);
            while(!(tDate.equals(eDate))){
                newId+=1;
                cal.roll(Calendar.DAY_OF_WEEK, true);
                tmp = cal.get(Calendar.DAY_OF_WEEK);
                tString = Integer.toString(tmp);
                tDate = df.parse(tString,par);
                ContentValues values = new ContentValues();
                values.put(ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID, newId);
                values.put(ClassReaderContract.ClassEntry.COLUMN_CLASS, classText.getText().toString());
                values.put(ClassReaderContract.DateEntry.COLUMN_DATE, tString);
                long newRowId;
                newRowId = db.insert(
                        ClassReaderContract.ClassEntry.TABLE_NAME,
                        null,
                        values);
                Log.i("newRowId", Long.toString(newRowId));
                Log.i("date", tString);
            }
            cal.setTime(sDate);
            cal.add(Calendar.DAY_OF_MONTH, 2);
            while(!(tDate.equals(eDate))){
                newId+=1;
                cal.roll(Calendar.DAY_OF_MONTH, true);
                tmp = cal.get(Calendar.DAY_OF_MONTH);
                tString = Integer.toString(tmp);
                tDate = df.parse(tString,par);
                ContentValues values1 = new ContentValues();
                values1.put(ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID, newId);
                values1.put(ClassReaderContract.ClassEntry.COLUMN_CLASS, classText.getText().toString());
                values1.put(ClassReaderContract.DateEntry.COLUMN_DATE, tString);
                long newRowId = db.insert(
                        ClassReaderContract.ClassEntry.TABLE_NAME,
                        null,
                        values1);
                Log.i("newRowId", Long.toString(newRowId));
                Log.i("date", tString);
            }
            editor.putInt("class_id", newId);
            editor.commit();

        } else {
            int tmp = cal.get(Calendar.DAY_OF_MONTH);
            String tString = Integer.toString(tmp);
            Date tDate = df.parse(tString,par);
            while(!(tDate.equals(eDate))){
                newId+=1;
                cal.roll(Calendar.DAY_OF_MONTH, true);
                tmp = cal.get(Calendar.DAY_OF_MONTH);
                tString = Integer.toString(tmp);
                tDate = df.parse(tString,par);
                ContentValues values = new ContentValues();
                values.put(ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID, newId);
                values.put(ClassReaderContract.ClassEntry.COLUMN_CLASS, classText.getText().toString());
                values.put(ClassReaderContract.DateEntry.COLUMN_DATE, tString);
                long newRowId;
                newRowId = db.insert(
                        ClassReaderContract.ClassEntry.TABLE_NAME,
                        null,
                        values);
                Log.i("newRowId", Long.toString(newRowId));
                Log.i("date", tString);
            }
            cal.setTime(sDate);
            cal.add(Calendar.DAY_OF_MONTH, 2);
            while(!(tDate.equals(eDate))){
                newId+=1;
                cal.roll(Calendar.DAY_OF_MONTH, true);
                tmp = cal.get(Calendar.DAY_OF_MONTH);
                tString = Integer.toString(tmp);
                tDate = df.parse(tString,par);
                ContentValues values1 = new ContentValues();
                values1.put(ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID, newId);
                values1.put(ClassReaderContract.ClassEntry.COLUMN_CLASS, classText.getText().toString());
                values1.put(ClassReaderContract.DateEntry.COLUMN_DATE, tString);
                long newRowId = db.insert(
                        ClassReaderContract.ClassEntry.TABLE_NAME,
                        null,
                        values1);
                Log.i("newRowId", Long.toString(newRowId));
                Log.i("date", tString);

            }
            cal.setTime(sDate);
            cal.add(Calendar.DAY_OF_MONTH, 4);
            while(!(tDate.equals(eDate))){
                newId+=1;
                cal.roll(Calendar.DAY_OF_MONTH, true);
                tmp = cal.get(Calendar.DAY_OF_MONTH);
                tString = Integer.toString(tmp);
                tDate = df.parse(tString,par);
                ContentValues values1 = new ContentValues();
                values1.put(ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID, newId);
                values1.put(ClassReaderContract.ClassEntry.COLUMN_CLASS, classText.getText().toString());
                values1.put(ClassReaderContract.DateEntry.COLUMN_DATE, tString);
                long newRowId = db.insert(
                        ClassReaderContract.ClassEntry.TABLE_NAME,
                        null,
                        values1);
                Log.i("newRowId", Long.toString(newRowId));
                Log.i("date", tString);

            }
            editor.putInt("class_id", newId);
            editor.commit();
        }

    } */


}
