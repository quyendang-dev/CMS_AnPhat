/*
 * Copyright (C) 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author binhnt22@viettel.com.vn
 * @since May 2012
 * @version 1.1
 */
public final class StringUtils {

    /**
     * alphabeUpCaseNumber.
     */
    private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String mask = "0123456789_aAá�?àÀảẢạẠãÃâÂấẤầẦẩẨậẬẫẪăĂắẮằẰẳẲặẶẵẴbBcCdDđ�?eEéÉèÈẻẺẹẸẽẼêÊếẾ�?ỀểỂệỆễỄfFgGhHiIí�?ìÌỉỈịIHĩĨjJkKlLmMnNoOóÓòÒ�?Ỏ�?ỌõÕôÔố�?ồỒổỔộỘỗỖơƠớỚ�?ỜởỞợỢỡỠpPqQrRsStTuUúÚùÙủỦụỤũŨưƯứỨừỪửỬựỰữỮvVwWxXyYý�?ỳỲỷỶỵỴỹỸzZ";
    private static String maskEN = "0123456789_aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    private static final String dateFormat = "dd/MM/yyyy";
    /**
     * ZERO.
     */
    private static final String ZERO = "0";
    private static final String c[] = {"<", ">"};
    private static final String expansion[] = {"&lt;", "&gt;"};

    public static String HTMLEncode(String sInput) {
        String s = sInput;
        for (int j = 0; j < c.length; j++) {
            s = s.replace(c[j], expansion[j]);
        }
        return s;
    }

    public static String HTMLDecode(String s) {
        String mine = s;
        for (int i = 0; i < c.length; i++) {
            mine.replaceAll(expansion[i], (c[i] + ""));
        }
        return mine;
    }

    /**
     * method compare two string
     *
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static boolean compareString(String strInput1, String strInput2) {
        String str1 = strInput1;
        String str2 = strInput2;

        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }

        if (str1.equals(str2)) {
            return true;
        }
        return false;
    }

    /**
     * method convert long to string
     *
     * @param lng Long
     * @return String
     * @throws abc Exception
     */
    public static String convertFromLongToString(Long lng) throws Exception {
        return Long.toString(lng);
    }

    /*
     * @todo: convert from Long array to String array
     */
    public static String[] convertFromLongToString(Long[] arrLong) throws Exception {
        String[] arrResult = new String[arrLong.length];
        for (int i = 0; i < arrLong.length; i++) {
            arrResult[i] = convertFromLongToString(arrLong[i]);
        }
        return arrResult;
    }

    /*
     * @todo: convert from String array to Long array
     */
    public static long[] convertFromStringToLong(String[] arrStr) throws Exception {
        long[] arrResult = new long[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            arrResult[i] = Long.parseLong(arrStr[i]);
        }
        return arrResult;
    }

    /*
     * @todo: convert from String value to Long value
     */
    public static long convertFromStringToLong(String value) throws Exception {
        return Long.parseLong(value);
    }


    /*
     * Check String that containt only AlphabeUpCase and Number Return True if
     * String was valid, false if String was not valid
     */
    public static boolean checkAlphabeUpCaseNumber(String value) {
        boolean result = true;
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (alphabeUpCaseNumber.indexOf(temp) == -1) {
                result = false;
                return result;
            }
        }
        return result;
    }

    public static boolean validString(Object temp) {
        if (temp == null || temp.toString().trim().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean maskVN(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (mask.indexOf(str.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean maskEN(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (maskEN.indexOf(str.charAt(i)) < 0) {
                return false;
            }
        }
        if (str.toLowerCase().charAt(0) < 'a' || str.toLowerCase().charAt(0) > 'z') {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str) {
        if (str == null || !str.matches("[0-9]+$")) {
            return false;
        }
        return true;
    }

    public static String formatString(String str) {
        return " '" + str.trim().toLowerCase() + "'";
    }

    public static String formatLike(String str) {
        return "%" + str.trim().toLowerCase().replaceAll("_", "\\\\_") + "%";
    }

    public static String formatOrder(String str, String direction) {
        return " NLSSORT(" + str + ",'NLS_SORT=vietnamese') " + direction;
    }

    public static String formatDate(Date date) {
//        return " to_date('" + DateTimeUtils.convertDateToString(date, ParamUtils.ddMMyyyy) + "', '" + ParamUtils.ddMMyyyy + "')";
        return DateTimeUtils.convertDateToString(date, dateFormat);
    }

    public static String formatFunction(String function, String str) {
        return " " + function + "(" + str + ") ";
    }

    public static String formatConstant(String str) {
        String str1 = "";
        int index = 0;
        String alphabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 1; i < str.length(); i++) {
            if (alphabe.indexOf(str.charAt(i)) > 0) {
                str1 = str1 + str.substring(index, i).toUpperCase() + "_";
                index = i;
            }
        }
        str1 = str1 + str.substring(index, str.length()).toUpperCase() + "_";
        return str1;
    }

    public static boolean isLong(String str) {
        try {
            Long.valueOf(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean containSpecialCharacteristic(String str) {
        if (str == null) {
            return false;
        }
        List lstSpecialCharacteristic = new ArrayList<String>();
        lstSpecialCharacteristic.add("!");
        lstSpecialCharacteristic.add("@");
        lstSpecialCharacteristic.add("#");
        lstSpecialCharacteristic.add("%");
        lstSpecialCharacteristic.add("^");
        lstSpecialCharacteristic.add("&");
        lstSpecialCharacteristic.add("*");
        lstSpecialCharacteristic.add("(");
        lstSpecialCharacteristic.add(")");
        lstSpecialCharacteristic.add(" ");
        for (int i = 0; i < lstSpecialCharacteristic.size(); i++) {
            if (str.contains(lstSpecialCharacteristic.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    public static String[] convertSetToArray(Set<String> set) {
        if (set != null) {
            String[] result = new String[set.size() + 1];
            result[0] = Constants.STT;
            int i = 1;
            for (String s : set) {
//                System.out.println(i + ":" + s);
                result[i++] = s;
            }
            return result;
        } else {
            return new String[0];
        }
    }

    public static String[] convertFromOptionAndTreeValueToArrayString(String string) {
        String tmp = string.replace("[", " ");
        string.replace("]", " ");
        string.replace(", ", "");
        return string.split(",");
    }
    
    public static boolean isNullOrEmpty(String obj1) {
        return (obj1 == null || "".equals(obj1.trim()));
    }
	
    public static String getStringToComponentValue(Object obj1, String defaultValue) {
        if (obj1 == null || obj1.toString().trim().equals("")) {
            if (isNullOrEmpty(defaultValue)) {
                return "";
            } else {
                return defaultValue;
            }
        } else {
            return obj1.toString().trim();
        }
    }
    public static boolean isStringNullOrEmpty(Object obj1) {
        return obj1 == null || obj1.toString().trim().equals("");
    }
        
   /**
    * sondt20 20150916
    */
    public static String ESCAPE_SQL_SPECIAL_CHARACTER = " escape '\\'";
    
   /**
    * sondt20 20150811
    * @param str
    * @return 
    */
    public static String replaceSpecicalChracterSQL(String str){
        return str.trim()
                .replaceAll("%", "\\\\%")
                .replaceAll("_", "\\\\_");
    }
    
    /**
     * sondt20 20150817
     * @param maxlength
     * @param str
     * @return 
     */
    public static boolean checkMaxlength(Long maxlength, String str){
        if (str!=null && str.trim().length()<maxlength){
            return true;
        }
        return false;
    }
        
    /**
     * convert Key to Value by Map
     * sondt20 20150828
     * @param state
     * @return 
     */
//    public static String convertKeyToValueByMap(Map<Long, String> map, String key){
//        String priStr = "";
//        try {
//            if (key!=null){
//                priStr = key.toString();
//                Long priLong = Long.valueOf(key);
//                if (map.containsKey(priLong)){
//                    priStr = BundleUtils.getChangeManageString(map.get(priLong));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return priStr;
//    }
//    /**
//     * convert Key to Value by Map String
//     * sondt20 20150828
//     * @param state
//     * @return 
//     */
//    public static String convertKeyToValueByMapString(Map<String, String> map, String key){
//        String priStr = "";
//        try {
//            if (key!=null){
//                priStr = key.toString();
//                if (map.containsKey(key.trim())){
//                    priStr = BundleUtils.getChangeManageString(map.get(key.trim()));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return priStr;
//    }

    public static String removeSeparator(String pathInput) {
        String path = pathInput;
        path = path.replace("\\\\", "\\").replace("//", "/");
        path = path.replace("\\/", "/").replace("/\\", "/");
        return path;
    }

    public static String getStringParttern(String input, String pattern) {
        try {
            Pattern patterns = Pattern.compile(pattern);
        Matcher matcher = patterns.matcher(input);
        if (matcher.find()) {
             return matcher.group(0);
        }
        } catch (Exception e) {
        }
        
        return input;
    }
}
