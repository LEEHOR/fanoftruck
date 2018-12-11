package com.coahr.fanoftruck.Utils;

import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 10:51
 * 正则表达式验证
 */
public class ValidateUtils {
    /**
     * 邮箱验证
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
       // String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strEmail)) {
            return false;
        } else {
            return Pattern.matches(REGEX_EMAIL,strEmail);
        }
    }

    /**
     * 手机格式验证
     * @param number
     * @return
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String num = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }


    /**
     * 验证身份证
     * @param idCardNum
     * @return
     */
    public static boolean isIDCard(String idCardNum){
         String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
            if (TextUtils.isEmpty(idCardNum)){
                return false;
            }else {
                return idCardNum.matches(REGEX_ID_CARD);
            }
    }

    /**
     * 保留两位小数
     * @param f
     * @return
     */
    public static String getDouble(float f){
        NumberFormat ddf1=NumberFormat.getNumberInstance() ;
        ddf1.setMaximumFractionDigits(2);
        String s= ddf1.format(f) ;
        return s;
    }
}
