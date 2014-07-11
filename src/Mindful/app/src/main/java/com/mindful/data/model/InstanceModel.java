package com.mindful.data.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.mindful.data.provider.InstanceTable;
import com.mindful.data.provider.MindfulContentProvider;

/**
 * Created by kyle on 7/7/14.
 */
public class InstanceModel {

    public static final int MAX_VALUE = 10;
    public static final int MIN_VALUE = -10;

    public String category = "Happiness";
    private String mComment = "";
    private int mValue = 0;
    public long latitude = 0;
    public long longitude = 0;

    public void increment() {
        if (mValue < MAX_VALUE) {
            mValue++;
        }
    }

    public void decrement() {
        if (mValue > MIN_VALUE) {
            mValue--;
        }
    }

    public int getValue() {
        return mValue;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public Uri saveInstance(ContentResolver cr) {

        ContentValues values = new ContentValues();
        values.put(InstanceTable.CATEGORY, category);
        values.put(InstanceTable.COMMENT, mComment);
        values.put(InstanceTable.VALUE, mValue);
        values.put(InstanceTable.LATITUDE, latitude);
        values.put(InstanceTable.LONGITUDE, longitude);

        Uri uri = MindfulContentProvider.getTableUri(InstanceTable.TABLE_NAME);

        return cr.insert(uri, values);
    }
}
