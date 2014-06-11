package com.mindful.data.provider;

import android.provider.BaseColumns;

/**
 * Created by kyle on 4/18/14.
 */
public class Mindful {

    public interface MindfulBaseColumns extends BaseColumns {

        public static final String TIME_CREATED = "time_created";

    }

    public interface InstanceColumns extends MindfulBaseColumns {

        public static final String UID = "uid";

        public static final String CATEGORY = "category";

        public static final String VALUE = "value";

        public static final String LATITUDE = "latitude";

        public static final String LONGITUDE = "longitude";

    }

}
