package net.sereko.incense.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by steve on 2/15/17.
 */

public class TaskOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String ACTIVITY_TABLE_NAME = "activity";
    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String TABLE_COMMENTS = "comments";
    private static final String DATABASE_NAME = "incense.db";
    private static final String DATABASE_CREATE =
            "CREATE TABLE" + ACTIVITY_TABLE_NAME + "( " + COLUMN_ID
                    + " integer primary key autoincrement, " + COLUMN_COMMENT
                    + " text not null);";

    public TaskOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
