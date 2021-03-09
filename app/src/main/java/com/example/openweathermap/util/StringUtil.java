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

    public static String getNvlIntString(String strOrg) {
        if (strOrg == null || strOrg.trim().length() == 0) {
            return "0";
        }
        return String.valueOf(Integer.parseInt(strOrg));
    }

    public static int getNvlInt(String strOrg, int intInt) {
        if (strOrg == null || strOrg.trim().length() == 0) {
            return intInt;
        }
        return Integer.parseInt(strOrg);
    }

    public static long getNvlLong(String strOrg) {
        if (strOrg == null || strOrg.trim().length() == 0) {
            return 0;
        }
        return Long.parseLong(strOrg);
    }

    public static long getNvlLong(String strOrg, long lngLng) {
        if (strOrg == null || strOrg.trim().length() == 0) {
            return lngLng;
        }
        return Long.parseLong(strOrg);
    }

    public static String readFromAssets(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    // 따옴표를 제거하고 리턴..
    public static String getRemoveQuota(String strOrg) {
        if (strOrg == null || strOrg.trim().length() == 0) {
            return strOrg;
        }
        return strOrg.replace("\"", "").toString();
    }

    /**
     * Right 함수
     */
    public static String Right(String Str, int Num) {
        if (Num <= 0)
            return "";
        else if (Num > Str.length())
            return Str;
        else {
            return Str.substring(Str.length() - Num, Str.length());
        }
    }

    /**
     * 고정길이 문자열 리턴
     *
     * @param string
     * @param length
     * @return
     */
    public static String fixedLengthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }

}
