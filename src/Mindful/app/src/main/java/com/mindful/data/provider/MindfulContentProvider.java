package com.mindful.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by kyle on 4/18/14.
 */
public class MindfulContentProvider extends ContentProvider {

    private static final String TAG = MindfulContentProvider.class.getSimpleName();

    public static final String AUTHORITY = "com.mindful.data.provider";
    public static final String SCHEME = "content";

    private static final int ID_INSTANCE = 0;
    private static final int ID_INSTANCE_SINGLE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AUTHORITY, InstanceTable.TABLE_NAME, ID_INSTANCE);
        sUriMatcher.addURI(AUTHORITY, InstanceTable.TABLE_NAME + "/*", ID_INSTANCE_SINGLE);
    }

    // Members
    private SQLiteOpenHelper mDatabaseHelper;

    // Access the provider's data - static helpers

    /**
     * Get the table Uri
     *
     * @param tableName {@link String}
     * @return {@link android.net.Uri}
     */
    public static Uri getTableUri(String tableName) {
        return new Uri.Builder().scheme(SCHEME).authority(AUTHORITY).path(tableName).build();
    }

    public static Uri getColumnUri(String tableName, String uid) {
        Uri uri = getTableUri(tableName);
        return Uri.withAppendedPath(uri, uid);
    }

    // ContentProvider implementation

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new MindfulDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {

        // Try to get a cursor from the database
        Cursor cursor = null;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db != null) {
            try {
                final String tableName = getTableName(uri);
                cursor = db.query(false, tableName, projection, selection, selectionArgs,
                        null, null, sortOrder, null);

                // By default notify the cursor with the given uri
                Context context = getContext();
                if (context != null) {
                    cursor.setNotificationUri(context.getContentResolver(), uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        switch (sUriMatcher.match(uri)) {
            case ID_INSTANCE:
                return "vnd.android.cursor.dir/vnd.com.mindful.data.provider.instance";
            case ID_INSTANCE_SINGLE:
                return "vnd.android.cursor.item/vnd.com.mindful.data.provider.instance";
            default:
                throw new IllegalArgumentException("on getType: Unknown URI " + uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        // Try to insert the content values
        Uri result = null;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db != null) {
            try {
                final String tableName = getTableName(uri);
                long count = db.insert(tableName, null, values);

                // If there was an error, return null
                result = (count == -1) ? null : uri;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        // Try to delete the selection
        int count = 0;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db != null) {
            try {
                final String tableName = getTableName(uri);
                count = db.delete(tableName, selection, selectionArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Try to update the selection
        int count = 0;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db != null) {
            try {
                final String tableName = getTableName(uri);
                count = db.update(tableName, values, selection, selectionArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;

    }

    // Class methods

    private String getTableName(Uri uri) {
        final String tableName;
        switch (sUriMatcher.match(uri)) {
            case ID_INSTANCE:
            case ID_INSTANCE_SINGLE:
                tableName = InstanceTable.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);
        }
        return tableName;
    }
}
