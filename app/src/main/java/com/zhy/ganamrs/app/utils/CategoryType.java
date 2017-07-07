package com.zhy.ganamrs.app.utils;

/**
 * Created by Administrator on 2017/7/4.
 */

public class CategoryType {

    public static final String ANDROID_STR = "Android";
    public static final String IOS_STR = "iOS";
    public static final String QIAN_STR = "前端";
    public static final String GIRLS_STR = "福利";


    public static final int ANDROID_IOS = 1;
    public static final int GIRLS = 2;

    public static String getPageTitleByPosition(int position) {
        if (position == 0){
            return ANDROID_STR;
        } else if (position == 1){
            return IOS_STR;
        } else if (position == 2){
            return QIAN_STR;
        } else {
            return "";
        }
    }

}
