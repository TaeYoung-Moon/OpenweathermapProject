package com.example.openweathermap.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author dkpark
 */
public class StringUtil {
    public static final String STR_EMPTY_STRING = "";

    /**
     * 주어진 문자열의 값이 널인 경우 blank로 변환한다.
     *
     * @param object
     * @return
     */
    public static Object getNvlStr(Object object) {
        if (object == null) {
            return STR_EMPTY_STRING;
        }
        return object;
    }

    /**
     * 원 문자열의 값이 null인경우 해당 문자열로 치환한다.
     *
     * @param strSrcData
     * @param strReplaceData
     * @return
     */
    public static String getNvlStr(String strSrcData, String strReplaceData) {
        if ((strSrcData == null) || (strSrcData.equals(STR_EMPTY_STRING))) {
            return strReplaceData;
        }
        return strSrcData;
    }

    public static int getNvlInt(String strOrg) {
        if (strOrg == null || strOrg.trim().length() == 0) {
            return 0;
        }
        return Integer.parseInt(strOrg);
    }



}
