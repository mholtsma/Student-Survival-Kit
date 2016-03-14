package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;



public class MyCalendar extends AppCompatActivity {
    static final public String MYPREFS = "myprefs";

    ClassDbHelper mDbHelper = new ClassDbHelper(this);
    DateDbHelper mDbHelper1 = new DateDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                TextView monthDate = (TextView) findViewById(R.id.monthDate);
                TextView longStudy = (TextView) findViewById(R.id.longStudy);
                EditText hourText = (EditText) findViewById(R.id.hourText);
                TextView numHours = (TextView) findViewById(R.id.numHours);
                TextView hoursStudied = (TextView) findViewById(R.id.hoursStudied);
                TextView totalHours = (TextView) findViewById(R.id.totalHours);
                TextView totNumHours = (TextView) findViewById(R.id.totNumHours);
                TextView Class = (TextView) findViewById(R.id.Class);
                TextView hoursLeft = (TextView) findViewById(R.id.hoursLeft);
                TextView goalRemainder = (TextView) findViewById(R.id.goalRemainder);
                TextView classStudy = (TextView) findViewById(R.id.classStudy);
                EditText classText = (EditText) findViewById(R.id.classText);
                monthDate.setText(df.format(date));
                if(isStudy()){
                    Log.i("True", "Date is true");
                    longStudy.setVisibility(View.INVISIBLE);
                    hourText.setVisibility(View.INVISIBLE);
                    classStudy.setVisibility(View.INVISIBLE);
                    classText.setVisibility(View.INVISIBLE);
                    String tDate = monthDate.getText().toString();
                    String classStudyTime = getWeeklyStudy(tDate);
                    String[] classHours = howStudy(tDate);
                    LocalDate tmpDate = LocalDate.fromDateFields(date);
                    String totalStudy = calcTotalStudy(tmpDate);
                    String goal = hourDif(totalStudy, classStudyTime);
                    numHours.setText(classHours[1]);
                    Class.setText(classHours[0]);
                    goalRemainder.setText(goal);
                    totNumHours.setText(totalStudy);
                    hoursStudied.setVisibility(View.VISIBLE);
                    numHours.setVisibility(View.VISIBLE);
                    totNumHours.setVisibility(View.VISIBLE);
                    totalHours.setVisibility(View.VISIBLE);
                    Class.setVisibility(View.VISIBLE);
                    goalRemainder.setVisibility(View.VISIBLE);
                    hoursLeft.setVisibility(View.VISIBLE);

                } else {
                    Log.i("False", "Date is False");
                    hoursStudied.setVisibility(View.INVISIBLE);
                    numHours.setVisibility(View.INVISIBLE);
                    totNumHours.setVisibility(View.INVISIBLE);
                    totalHours.setVisibility(View.INVISIBLE);
                    Class.setVisibility(View.INVISIBLE);
                    goalRemainder.setVisibility(View.INVISIBLE);
                    hoursLeft.setVisibility(View.INVISIBLE);
                    Class.setText(" ");
                    numHours.setText(" ");
                    longStudy.setVisibility(View.VISIBLE);
                    hourText.setVisibility(View.VISIBLE);
                    classStudy.setVisibility(View.VISIBLE);
                    classText.setVisibility(View.VISIBLE);
                }
                //Toast.makeText(MyCalendar.this,  df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void calSubmit(View v){
        TextView monthDate = (TextView) findViewById(R.id.monthDate);
        TextView longStudy = (TextView) findViewById(R.id.longStudy);
        EditText hourText = (EditText) findViewById(R.id.hourText);
        TextView numHours = (TextView) findViewById(R.id.numHours);
        TextView hoursStudied = (TextView) findViewById(R.id.hoursStudied);
        TextView totalHours = (TextView) findViewById(R.id.totalHours);
        TextView totNumHours = (TextView) findViewById(R.id.totNumHours);
        TextView Class = (TextView) findViewById(R.id.Class);
        TextView hoursLeft = (TextView) findViewById(R.id.hoursLeft);
        TextView goalRemainder = (TextView) findViewById(R.id.goalRemainder);
        TextView classStudy = (TextView) findViewById(R.id.classStudy);
        EditText classText = (EditText) findViewById(R.id.classText);
        SQLiteDatabase db = mDbHelper1.getReadableDatabase();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        int newId = settings.getInt("date_id", 1);
        newId+=1;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("date_id", newId);
        editor.commit();

        String tDate = monthDate.getText().toString();
        String tClass = classText.getText().toString();
        EditText et = (EditText) findViewById(R.id.hourText);
        String tHours = et.getText().toString();
        ContentValues values = new ContentValues();

        values.put(ClassReaderContract.DateEntry.COLUMN_NAME_ENTRY_ID, newId);
        values.put(ClassReaderContract.DateEntry.COLUMN_MONTH_DATE, tDate);
        values.put(ClassReaderContract.DateEntry.COLUMN_CLASS, tClass);
        values.put(ClassReaderContract.DateEntry.COLUMN_HOURS, tHours);
        values.put(ClassReaderContract.DateEntry.COLUMN_STUDY, true);

        long newRowId;
        newRowId = db.insert(
                ClassReaderContract.DateEntry.TABLE_NAME,
                null,
                values);
        hourText.setText(" ");
        classText.setText(" ");
        longStudy.setVisibility(View.INVISIBLE);
        hourText.setVisibility(View.INVISIBLE);
        classStudy.setVisibility(View.INVISIBLE);
        classText.setVisibility(View.INVISIBLE);
        String[] classHours = howStudy(tDate);
        numHours.setText(classHours[1]);
        hoursStudied.setVisibility(View.VISIBLE);
        numHours.setVisibility(View.VISIBLE);
        totNumHours.setVisibility(View.VISIBLE);
        totalHours.setVisibility(View.VISIBLE);
        Class.setVisibility(View.VISIBLE);
        goalRemainder.setVisibility(View.VISIBLE);
        hoursLeft.setVisibility(View.VISIBLE);

    }

    //function to see if someone studied on a specific day
    public Boolean isStudy() {
        SQLiteDatabase db = mDbHelper1.getReadableDatabase();
        String[] projection = {
                ClassReaderContract.DateEntry._ID,
                ClassReaderContract.DateEntry.COLUMN_NAME_ENTRY_ID,
                ClassReaderContract.DateEntry.COLUMN_MONTH_DATE,
                ClassReaderContract.DateEntry.COLUMN_STUDY,
        };
        TextView tv = (TextView) findViewById(R.id.monthDate);
        String tDate = tv.getText().toString();
        Cursor c = db.query(
                ClassReaderContract.DateEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                            // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        while(c.moveToNext()){
            int itemId = c.getColumnIndexOrThrow(ClassReaderContract.DateEntry.COLUMN_MONTH_DATE);
            String tmp = c.getString(itemId);
            if(tmp.equals(tDate)){
                c.close();
                return true;
            }

        }
        c.close();
        return false;
    }

    //function to find out long someone has studied on a specific day
    public String[] howStudy(String tDate){
        SQLiteDatabase db = mDbHelper1.getReadableDatabase();
        String[] projection = {
                ClassReaderContract.DateEntry._ID,
                ClassReaderContract.DateEntry.COLUMN_NAME_ENTRY_ID,
                ClassReaderContract.DateEntry.COLUMN_MONTH_DATE,
                ClassReaderContract.DateEntry.COLUMN_STUDY,
                ClassReaderContract.DateEntry.COLUMN_CLASS,
                ClassReaderContract.DateEntry.COLUMN_HOURS
        };
        Cursor c = db.query(
                ClassReaderContract.DateEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                            // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        String[] tmp1 = new String[2];
        while(c.moveToNext()){
            int itemId = c.getColumnIndexOrThrow(ClassReaderContract.DateEntry.COLUMN_MONTH_DATE);
            String tmp = c.getString(itemId);
            if(tmp.equals(tDate)) {
                itemId = c.getColumnIndexOrThrow(ClassReaderContract.DateEntry.COLUMN_HOURS);
                tmp1[1] = c.getString(itemId);
                itemId = c.getColumnIndexOrThrow(ClassReaderContract.DateEntry.COLUMN_CLASS);
                tmp1[0] = c.getString(itemId);
                c.close();
                return tmp1;
            }

        }
        c.close();
        tmp1[0] = " ";
        tmp1[1] = "0";
        return tmp1;
    }

    public String calcTotalStudy(LocalDate local){
        DateFormat df = SimpleDateFormat.getDateInstance();
        LocalDate day = local.withDayOfWeek(1);
        int week = local.getWeekOfWeekyear();
        Date tDate;
        String tmp1 = " ";
        String[] tmp2;
        int total = 0;
        while(day.getWeekOfWeekyear() == week){
            tDate = day.toDate();
            tmp1 = df.format(tDate);

            tmp2 = howStudy(tmp1);

            tmp2[1] = tmp2[1].trim();
            total += Integer.parseInt(tmp2[1]);

            day = day.plusDays(1);
        }

        return Integer.toString(total);
    }

    public String getWeeklyStudy(String tDate){
        SQLiteDatabase db = mDbHelper1.getReadableDatabase();
        String[] projection = {
                ClassReaderContract.DateEntry._ID,
                ClassReaderContract.DateEntry.COLUMN_NAME_ENTRY_ID,
                ClassReaderContract.DateEntry.COLUMN_MONTH_DATE,
                ClassReaderContract.DateEntry.COLUMN_STUDY,
                ClassReaderContract.DateEntry.COLUMN_CLASS,
                ClassReaderContract.DateEntry.COLUMN_HOURS
        };
        Cursor c = db.query(
                ClassReaderContract.DateEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                            // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        String tClass = " ";
        c.moveToFirst();
        while(c.moveToNext()) {
            int itemId = c.getColumnIndexOrThrow(ClassReaderContract.DateEntry.COLUMN_MONTH_DATE);
            String tmp = c.getString(itemId);
            if(tmp.equals(tDate)){
                itemId = c.getColumnIndexOrThrow(ClassReaderContract.DateEntry.COLUMN_CLASS);
                tClass = c.getString(itemId);
                break;
            }
        }

        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();
        String[] projection1 = {
                ClassReaderContract.ClassEntry._ID,
                ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID,
                ClassReaderContract.ClassEntry.COLUMN_CLASS,
                ClassReaderContract.ClassEntry.COLUMN_UNITS,
                ClassReaderContract.ClassEntry.COLUMN_STUDY_HOURS,
        };

        Cursor c1 = db1.query(
                ClassReaderContract.ClassEntry.TABLE_NAME,  // The table to query
                projection1,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort orderadasd
        );
        c1.moveToFirst();
        String classHours;
        while(c1.moveToNext()) {
            int itemId1 = c1.getColumnIndexOrThrow(ClassReaderContract.ClassEntry.COLUMN_CLASS);
            String className = c1.getString(itemId1);
            Log.i("className", className);
            Log.i("tClass", tClass);
            if(className.equals(tClass)){
                itemId1 = c1.getColumnIndexOrThrow(ClassReaderContract.ClassEntry.COLUMN_STUDY_HOURS);
                classHours = c1.getString(itemId1);
                return classHours;
            }
        }
        return "0";
    }

    public String hourDif(String total, String goal){
        int x = Integer.parseInt(total);
        int y = Integer.parseInt(goal);
        int z = y-x;
        int zero = 0;
        Log.i("Z", Integer.toString(z));
        Log.i("X", Integer.toString(x));
        Log.i("Y", Integer.toString(y));
        if(z <= zero){
            return "You Hit Your Goal";
        } else{
            return Integer.toString(z);
        }
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */
}


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    String[] projection = {
                ClassReaderContract.DateEntry._ID,
                ClassReaderContract.DateEntry.COLUMN_NAME_ENTRY_ID,
                ClassReaderContract.DateEntry.COLUMN_CLASS,
                ClassReaderContract.DateEntry.COLUMN_DATE,
                ClassReaderContract.DateEntry.COLUMN_HOMEWORK,
                ClassReaderContract.DateEntry.COLUMN_STUDY,
        };
        String selection = "COLUMN_DATE=?";
        String orderBy = "COLUMN_DATE";
        String[] whereArgs = new String[] {
                "value1"
        };

        TextView tv = (TextView) findViewById(R.id.monthDate);
        String tDate = tv.toString();
        whereArgs[0] = tDate;
        Cursor c = db.query(
                ClassReaderContract.ClassEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                            // The columns for the WHERE clause
                whereArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                orderBy                                 // The sort order
        );
        c.moveToFirst();
        int itemId = c.getColumnIndexOrThrow(ClassReaderContract.DateEntry.COLUMN_CLASS);
        String className = c.getString(itemId);




    */



