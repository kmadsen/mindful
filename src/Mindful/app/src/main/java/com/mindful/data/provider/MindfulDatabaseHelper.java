package com.mindful.data.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Manages the Mindful database. All creation and upgrade database logic happens here.
 */
public class MindfulDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = MindfulDatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "mindful.db";
    private static final int DATABASE_VERSION = 1;

    public MindfulDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InstanceTable.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version "
                + oldVersion + " to " + newVersion);

        // This switch represents how the tables are upgraded. When ones alters the database,
        // increase the version and add the sqlite triggers and upgrades necessary.
        //
        // Note that this code is closed for modification, it represents the database history.
        // Only add cases for the new versions.
        switch (oldVersion) {
            case 1:
                if (newVersion <= 1) {
                    return;
                }

                // TODO when you upgrade to database version 2, put in logic to upgrade here
                // Fall through to handle next upgrade

            case 2:
                if (newVersion <= 2) {
                    return;
                }

                // Fall through to handle next upgrade
            default:

        }

    }
}
