package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by march on 3/13/2016.
 */
public class DateDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";


    private static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE " + ClassReaderContract.DateEntry.TABLE_NAME + " (" +
                    ClassReaderContract.DateEntry._ID + " INTEGER PRIMARY KEY," +
                    ClassReaderContract.DateEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    ClassReaderContract.DateEntry.COLUMN_CLASS + TEXT_TYPE + COMMA_SEP + "'" +
                    ClassReaderContract.DateEntry.COLUMN_MONTH_DATE + "'" + TEXT_TYPE + COMMA_SEP +
                    ClassReaderContract.DateEntry.COLUMN_HOMEWORK + " BOOLEAN" + COMMA_SEP  +
                    ClassReaderContract.DateEntry.COLUMN_STUDY + " BOOLEAN" + COMMA_SEP +
                    ClassReaderContract.DateEntry.COLUMN_HOURS + " TEXT_TYPE" +
                    " );";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ClassReaderContract.ClassEntry.TABLE_NAME;

    public static final int DATABASE_VERSION1 = 1;
    public static final String DATABASE_NAME1 = "DateTable.db";

    public DateDbHelper(Context context1) {super(context1, DATABASE_NAME1, null, DATABASE_VERSION1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
