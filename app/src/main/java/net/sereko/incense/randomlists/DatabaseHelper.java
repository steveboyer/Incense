package net.sereko.incense.randomlists;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Process;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by steve on 10/23/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "randolist.db";
    private static final int SCHEMA_VERSION = 1;
    private static DatabaseHelper singleton = null;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE lists (position INTEGER PRIMARY KEY, prose TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("This should not be called.");
    }

    synchronized static DatabaseHelper getInstance(Context context){
        if (singleton == null){
            singleton = new DatabaseHelper(context.getApplicationContext());
        }

        return singleton;
    }

    public void updateNote(int position, String prose){
        new UpdateThread(position, prose).start();
    }

    public void loadNote(int position){
        new LoadThread(position).start();
    }

    private class LoadThread extends Thread {
        private int position = -1;

        LoadThread(int position){
            super();
            this.position = position;
        }

        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            String[] args = {String.valueOf(position)};
            Cursor cursor =
                    getReadableDatabase().rawQuery("SELECT prose FROM notes WHERE position = ?", args);

            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                EventBus.getDefault().post(new ListLoadedEvent(position, cursor.getString(0)));
            }

            cursor.close();
        }
    }

    private class UpdateThread extends Thread {
        private int position = -1;
        private String prose = null;

        UpdateThread(int position, String prose){
            this.position = position;
            this.prose = prose;
        }

        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            String[] args = {String.valueOf(position), prose};
            getWritableDatabase().execSQL("INSERT OR REPLACE INTO notes (position, prose) VALUES (?,?)", args);
        }
    }
}
