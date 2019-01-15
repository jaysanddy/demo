package com.vk.util;

/**
 * Created by wj on 18-4-19.
 */
public class TextUtil {

    public static boolean isEmpty(String text){
        if(text==null||text.trim().length()==0){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String text){
        if(text!=null&&text.trim().length()>0){
            return true;
        }
        return false;
    }

    public static boolean integerNotEmpty(Integer str){
        if(str != null && str.toString() != ""){
            return true;
        }else{
            return false;
        }
    }
}
