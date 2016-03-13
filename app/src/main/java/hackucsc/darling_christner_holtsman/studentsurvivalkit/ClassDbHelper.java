package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by march on 3/12/2016.
 */
public class ClassDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ClassReaderContract.ClassEntry.TABLE_NAME + " (" +
                    ClassReaderContract.ClassEntry._ID + " INTEGER PRIMARY KEY," +
                    ClassReaderContract.ClassEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    ClassReaderContract.ClassEntry.COLUMN_CLASS + TEXT_TYPE + COMMA_SEP +
                    ClassReaderContract.ClassEntry.COLUMN_UNITS + TEXT_TYPE + COMMA_SEP +
                    ClassReaderContract.ClassEntry.COLUMN_CLASS_DAYS + TEXT_TYPE + COMMA_SEP + "'" +
                    ClassReaderContract.ClassEntry.COLUMN_START_DATE + "'" + TEXT_TYPE + COMMA_SEP + "'" +
                    ClassReaderContract.ClassEntry.COLUMN_END_DATE + "'" + TEXT_TYPE +
                    " );";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ClassReaderContract.ClassEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ClassTable.db";

    public ClassDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
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
