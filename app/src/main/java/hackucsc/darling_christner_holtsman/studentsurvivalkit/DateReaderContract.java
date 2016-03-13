package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.provider.BaseColumns;

/**
 * Created by march on 3/13/2016.
 */

public class DateReaderContract {
    public DateReaderContract() {}



    public static abstract class DateEntry implements BaseColumns {
        public static final String TABLE_NAME = "DATE_TABLE";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_CLASS = "CLASS";
        public static final String COLUMN_MONTH_DATE = "MONTH_DATE";
        public static final String COLUMN_HOMEWORK = "HOMEWORK";
        public static final String COLUMN_STUDY = "STUDY";
        public static final String COLUMN_HOURS = "HOURS";

    }
}
