package com.mindful.data.provider;

/**
 * Created by kyle on 4/19/14.
 */
public class InstanceTable implements Mindful.InstanceColumns {

    public static final String TABLE_NAME = "Instance";
    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( "
            + _ID + " integer primary key autoincrement, "
            + TIME_CREATED + " timestamp default current_timestamp, "
            + CATEGORY + " varchar default '', "
            + COMMENT + " varchar default '', "
            + VALUE + " integer default 0, "
            + LATITUDE + " varchar, "
            + LONGITUDE + " varchar"
            + ")";

}
