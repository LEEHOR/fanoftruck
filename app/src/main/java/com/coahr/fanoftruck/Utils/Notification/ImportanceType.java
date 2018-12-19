package com.coahr.fanoftruck.Utils.Notification;

import android.support.annotation.IntDef;

/**
 * Created by Leehor
 * on 2018/12/19
 * on 10:20
 */
@IntDef({ImportanceType.IMPORTANCE_NONE,
        ImportanceType.IMPORTANCE_MIN,
        ImportanceType.IMPORTANCE_LOW,
        ImportanceType.IMPORTANCE_DEFAULT,
        ImportanceType.IMPORTANCE_HIGH})
public @interface ImportanceType {

    int IMPORTANCE_NONE = 0;
    int IMPORTANCE_MIN = 1;
    int IMPORTANCE_LOW = 2;
    int IMPORTANCE_DEFAULT = 3;
    int IMPORTANCE_HIGH = 4;
}
