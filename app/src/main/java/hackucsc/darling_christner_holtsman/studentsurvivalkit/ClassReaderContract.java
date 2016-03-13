package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
//sdfsdf
/**
 * Created by march on 3/12/2016.
 */
public final class ClassReaderContract {
    public ClassReaderContract() {}

    public static abstract class ClassEntry implements BaseColumns {
        public static final String TABLE_NAME = "CLASS_TABLE";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_CLASS = "CLASS";
        public static final String COLUMN_UNITS = "UNITS";
        public static final String COLUMN_CLASS_DAYS = "CLASS_DAYS";
        public static final String COLUMN_START_DATE = "START_DATE";
        public static final String COLUMN_END_DATE = "END_DATE";

    }

    public static abstract class DateEntry implements BaseColumns {
        public static final String TABLE_NAME = "CLASS_TABLE";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_CLASS = "CLASS";
        public static final String COLUMN_UNITS = "UNITS";
        public static final String COLUMN_CLASS_DAYS = "CLASS_DAYS";
        public static final String COLUMN_START_DATE = "START_DATE";
        public static final String COLUMN_END_DATE = "END_DATE";

    }

}

