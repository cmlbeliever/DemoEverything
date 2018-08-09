package com.cml.framework.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTools  {

    private static Logger log = LoggerFactory.getLogger(DateTools.class);

    private static String datePattern = "yyyyMMdd";
    private static String timePattern;
    public static String[] STR_YEAR;
    public static String[] WEEK_ZH;
    public static String[] MONTH_ZH;
    public static String[] MONTH_UP;
    public static String[] MONTH_LO;
    public static String[] MON_UP;
    public static String[] MON_LO;
    private static String divisionChars;

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(DateTools.addDay(new Date(System.currentTimeMillis()), -3))+"000000000";
        System.out.println(date);

        System.out.println("12341".compareTo("1234"));
    }

    /**
     * 获取当前月的第一天零点
     * @return
     */
    public static String getMonthFirstDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND,0);
        String a =format.format(c.getTime());
        return a;
    }

    /**
     * 根据当前时间倒退多少天之后的日期
     * @return
     */
    public static String getReverseDay(int days,String format){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -days);
        String endDate = new SimpleDateFormat(format).format(now.getTime());
        return endDate;
    }

    /**
     * 获取上个月
     * @return
     */
    public static String getLastMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        String a =format.format(c.getTime());
        return a;
    }


    /**
     * 获取时间差
     * @return
     */
    public static String getTimeMinus(String str1,String str2)
    {
        DateFormat df=null;
        if(str1.length()==17) {
            df=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        }else{
            df=new SimpleDateFormat("yyyyMMddHHmmss");
        }
        Date date1 = null;
        Date date2 =null;
        try {
            date1 = df.parse(str1);
            date2=df.parse(str2);
        } catch (ParseException e1) {
            return " ";
        }
        try {
            long d=(date1.getTime()>date2.getTime())?(date1.getTime()-date2.getTime()):(date2.getTime()-date1.getTime());
            long dd=d/1000;//转换成秒
            long day1=dd/(24*60*60);
            long hour1=dd%(24*3600)/3600;
            long minute1=dd%3600/60;
            if(day1>0)
                return (day1+"天"+hour1+"小时 "+minute1+"分钟");
            else if(hour1>0)
                return (hour1+"小时 "+minute1+"分钟");
            else
                return (minute1+"分钟");
        } catch (Exception e) {
            e.printStackTrace();
            return " ";
        }
    }



    /***
     * 获取当前时间 格式：20100101120000
     * @return
     */
    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String nowString = sdf.format(now);
        return nowString;
    }

    /***
     * 时间格式转换
     * 获取当前时间 格式：2016年12月26日12时01分
     */
    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        String temp = format.format(date);
        return temp;
    }

    /***
     * 获取当前时间 格式：2010-01-01 12:00:00
     * @return
     */
    public static String getTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String nowString = sdf.format(now);
        return nowString;
    }

    /***
     * 获取当前时间 格式：20150101120101000
     * @return
     */
    public static String getTime17(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date now = new Date();
        String nowString = sdf.format(now);
        return nowString;
    }

    /***
     * 日期 格式转换：20100101120000 -->> 20100101
     * @param date
     * @return
     */
    public static String parseDateToDay(String date) {
        if (date.length()==8) {
            return date;
        }
        SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat smf1 = new SimpleDateFormat("yyyyMMdd");
        Date date1 = new Date();
        try {
            date1 = smf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return smf1.format(date1);
    }


    /***
     * 日期 格式转换： 传入：2010-01-01 12:00:00 转出：20100101
     * @param date
     * @return
     */
    public static String parseLongDateToDay(String date) {
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat smf1 = new SimpleDateFormat("yyyyMMdd");
        Date date1 = new Date();
        try {
            date1 = smf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return smf1.format(date1);
    }


    /**
     *日期转换
     * @param str
     * @return
     */
    public static String DateParse(String str) {// 将”01/01/2015“类型的字符串转成”2015/01/01“
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {

        }
        sdf = new SimpleDateFormat("yyyy/MM/dd");
        str = sdf.format(date);
        return str;
    }

    /***
     * 获取当前时间 格式：20160601
     * @return
     */
    public static String getTimeNew(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String nowString = sdf.format(now);
        return nowString;
    }

    /**
     *日期转换
     * @param str
     * @return
     */
    public static String DateParseNew(String str) {// 将”20150101“类型的字符串转成”01/01/2015“
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd") ;
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy") ;
        Date date = null;
        try {
            date = sdf1.parse(str);
        } catch (ParseException e) {

        }
        str = sdf2.format(date);
        return str;
    }

    /***
     * 获取当前日期 	格式：20100101
     * @return
     */
    public static String getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String nowString = sdf.format(now);
        return nowString;
    }

    /***
     * 获取当前日期 	格式：201001
     * @return
     */

    public static String getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date now = new Date();
        String nowString = sdf.format(now);
        return nowString;
    }



    public static String getDatePattern() {
        return datePattern;
    }

    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if(aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return returnValue;
    }

    public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        if(log.isDebugEnabled()) {
            log.debug("converting \'" + strDate + "\' to date with mask \'" + aMask + "\'");
        }

        try {
            date = df.parse(strDate);
            return date;
        } catch (ParseException var5) {
            throw new ParseException(var5.getMessage(), var5.getErrorOffset());
        }
    }

    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        String todayAsString = df.format(today);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }

    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if(aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return returnValue;
    }

    public static final String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }

    public static Date convertStringToDate(String strDate) throws ParseException {
        Date aDate = null;

        try {
            if(log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + datePattern);
            }

            aDate = convertStringToDate(datePattern, strDate);
            return aDate;
        } catch (ParseException var3) {
            log.error("Could not convert \'" + strDate + "\' to a date, throwing exception");
            var3.printStackTrace();
            throw new ParseException(var3.getMessage(), var3.getErrorOffset());
        }
    }

    private static String intToString(int val, double len) {
        Double dmask = new Double(Math.pow(10.0D, len));
        int mask = dmask.intValue();
        return String.valueOf(val + mask).substring(1);
    }

    private static String intToString(int val, int len) {
        Integer ilen = new Integer(len);
        return intToString(val, ilen.doubleValue());
    }

    private static StringTokenizer filterDivision(String str) {
        StringTokenizer divisionToken = new StringTokenizer(divisionChars, " ", false);

        StringTokenizer strToken;
        while(divisionToken.hasMoreTokens()) {
            strToken = new StringTokenizer(str, divisionToken.nextToken(), false);

            for(str = ""; strToken.hasMoreTokens(); str = str + " ") {
                str = str + strToken.nextToken();
            }
        }

        strToken = new StringTokenizer(str, " ", false);
        String temp = "";
        if(strToken.countTokens() == 1) {
            if(str.trim().length() == 8) {
                temp = str.substring(0, 4) + " " + str.substring(4, 6) + " " + str.substring(6, 8);
                str = temp;
            } else if(str.trim().length() == 7) {
                if("0".equals(str.substring(4, 5))) {
                    temp = str.substring(0, 4) + " " + str.substring(4, 6) + " " + str.substring(6, 7);
                } else {
                    temp = str.substring(0, 4) + " " + str.substring(4, 5) + " " + str.substring(5, 7);
                }

                str = temp;
            } else if(str.trim().length() == 6) {
                temp = str.substring(0, 4) + " " + str.substring(4, 5) + " " + str.substring(5, 6);
                str = temp;
            }
        }

        strToken = new StringTokenizer(str, " ", false);
        return strToken;
    }

    private static HashMap parseInput(StringTokenizer strToken, StringTokenizer fmtToken) throws Exception {
        if(strToken.countTokens() != fmtToken.countTokens()) {
            throw new Exception("Not match value string and format string!");
        } else {
            HashMap hm = new HashMap();

            while(fmtToken.hasMoreTokens()) {
                hm.put(fmtToken.nextToken(), strToken.nextToken());
            }

            return hm;
        }
    }

    private static Calendar parseYear(Calendar cal, HashMap hm, Locale lo) throws Exception {
        int year;
        if(hm.get("YYYY") != null) {
            try {
                year = Integer.parseInt((String)hm.get("YYYY"));
                cal.set(1, year);
                return cal;
            } catch (NumberFormatException var5) {
                throw new Exception("The year string is not valid!");
            }
        } else if(hm.get("YY") != null) {
            try {
                year = Integer.parseInt((String)hm.get("YY"));
                if(year < 50) {
                    year += 2000;
                } else {
                    year += 1900;
                }

                cal.set(1, year);
                return cal;
            } catch (NumberFormatException var6) {
                throw new Exception("The year string is not valid!");
            }
        } else {
            return cal;
        }
    }

    private static Calendar parseMonth(Calendar cal, HashMap hm, Locale lo) throws Exception {
        int month = 0;
        if(hm.get("MM") != null) {
            try {
                month = Integer.parseInt((String)hm.get("MM")) - 1;
                cal.set(2, month);
                return cal;
            } catch (NumberFormatException var7) {
                throw new Exception("The month string is not valid!");
            }
        } else if(hm.get("Mon") == null && hm.get("MON") == null) {
            return cal;
        } else {
            try {
                String nfe = ((String)hm.get("Mon")).toUpperCase();
                int i = 0;

                for(int l = MON_UP.length; i < l; ++i) {
                    if(nfe.equals(MON_UP[i])) {
                        month = i;
                    }
                }

                cal.set(2, month);
                return cal;
            } catch (NumberFormatException var8) {
                throw new Exception("The month string is not valid!");
            }
        }
    }

    private static Calendar parseDate(Calendar cal, HashMap hm, Locale lo) throws Exception {
        if(hm.get("DD") != null) {
            try {
                int date = Integer.parseInt((String)hm.get("DD"));
                cal.set(5, date);
                return cal;
            } catch (NumberFormatException var5) {
                throw new Exception("The date string is not valid!");
            }
        } else {
            return cal;
        }
    }

    private static Calendar parseHour(Calendar cal, HashMap hm, Locale lo) throws Exception {
        int hour;
        if(hm.get("HH24") != null) {
            try {
                hour = Integer.parseInt((String)hm.get("HH24"));
                cal.set(11, hour);
                return cal;
            } catch (NumberFormatException var5) {
                throw new Exception("The hour string is not valid!");
            }
        } else if(hm.get("HH") != null) {
            try {
                hour = Integer.parseInt((String)hm.get("HH"));
                cal.set(10, hour);
                return cal;
            } catch (NumberFormatException var6) {
                throw new Exception("The hour string is not valid!");
            }
        } else {
            return cal;
        }
    }

    private static Calendar parseMinute(Calendar cal, HashMap hm, Locale lo) throws Exception {
        if(hm.get("MI") != null) {
            try {
                int minute = Integer.parseInt((String)hm.get("MI"));
                cal.set(12, minute);
                return cal;
            } catch (NumberFormatException var5) {
                throw new Exception("The minute string is not valid!");
            }
        } else {
            return cal;
        }
    }

    private static Calendar parseSecond(Calendar cal, HashMap hm, Locale lo) throws Exception {
        if(hm.get("SS") != null) {
            try {
                int second = Integer.parseInt((String)hm.get("SS"));
                cal.set(13, second);
                return cal;
            } catch (NumberFormatException var5) {
                throw new Exception("The second string is not valid!");
            }
        } else {
            return cal;
        }
    }

    private static Calendar parseMeridian(Calendar cal, HashMap hm, Locale lo) throws Exception {
        byte meridian = 0;
        if(hm.get("AM") == null && hm.get("A.M.") == null && hm.get("PM") == null && hm.get("P.M.") == null) {
            return cal;
        } else {
            String meridianStr = (String)hm.get("AM");
            if(meridianStr == null) {
                meridianStr = (String)hm.get("A.M.");
            }

            if(meridianStr == null) {
                meridianStr = (String)hm.get("PM");
            }

            if(meridianStr == null) {
                meridianStr = (String)hm.get("P.M.");
            }

            switch(lo.hashCode()) {
                case 1591:
                    if(!"PM".equals(meridianStr) && !"P.M.".equals(meridianStr)) {
                        if(!"AM".equals(meridianStr) && !"A.M.".equals(meridianStr)) {
                            throw new Exception("The meridian string is wrong!");
                        }
                    } else {
                        meridian = 1;
                    }
                    break;
                case 1861:
                    if("下午".equals(meridianStr)) {
                        meridian = 1;
                    } else if(!"上午".equals(meridianStr)) {
                        throw new Exception("The meridian string is wrong!");
                    }
                    break;
                default:
                    if(!"PM".equals(meridianStr) && !"P.M.".equals(meridianStr)) {
                        if(!"AM".equals(meridianStr) && !"A.M.".equals(meridianStr)) {
                            throw new Exception("The meridian string is wrong!");
                        }
                    } else {
                        meridian = 1;
                    }
            }

            cal.set(9, meridian);
            return cal;
        }
    }

    public static Date stringToDate(String str, String fmt) throws Exception {
        return stringToCalendar(str, fmt).getTime();
    }

    public static Date stringToDate(String str, String fmt, Locale lo) throws Exception {
        return stringToCalendar(str, fmt, lo).getTime();
    }

    public static Calendar stringToCalendar(String str, String fmt) throws Exception {
        return stringToCalendar(str, fmt, Locale.US);
    }

    public static Calendar stringToCalendar(String str, String fmt, Locale lo) throws Exception {
        Calendar cal = Calendar.getInstance();
        HashMap hm = parseInput(filterDivision(str), filterDivision(fmt));
        cal = parseYear(cal, hm, lo);
        cal = parseMonth(cal, hm, lo);
        cal = parseDate(cal, hm, lo);
        cal = parseHour(cal, hm, lo);
        cal = parseMinute(cal, hm, lo);
        cal = parseSecond(cal, hm, lo);
        cal = parseMeridian(cal, hm, lo);
        return cal;
    }

    private static String parseYear(Calendar cal, String dtStr, Locale lo) throws Exception {
        try {
            StringBuffer strBuf;
            while(dtStr.lastIndexOf("YYYY") > -1) {
                strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("YYYY"), dtStr.lastIndexOf("YYYY") + 4, String.valueOf(cal.get(1)));
                dtStr = strBuf.toString();
            }

            while(dtStr.lastIndexOf("YY") > -1) {
                strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("YY"), dtStr.lastIndexOf("YY") + 2, String.valueOf(cal.get(1)).substring(2));
                dtStr = strBuf.toString();
            }

            return dtStr;
        } catch (StringIndexOutOfBoundsException var5) {
            throw new Exception("Parse Year Error!");
        }
    }

    private static String parseMonth(Calendar cal, String dtStr, Locale lo) throws Exception {
        try {
            StringBuffer strBuf;
            while(dtStr.lastIndexOf("MM") > -1) {
                strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("MM"), dtStr.lastIndexOf("MM") + 2, intToString(cal.get(2) + 1, 2));
                dtStr = strBuf.toString();
            }

            for(; dtStr.lastIndexOf("Mon") > -1; dtStr = strBuf.toString()) {
                strBuf = new StringBuffer(dtStr);
                switch(lo.hashCode()) {
                    case 1591:
                        strBuf.replace(dtStr.lastIndexOf("Mon"), dtStr.lastIndexOf("Mon") + 3, MON_LO[cal.get(2)]);
                        break;
                    case 1861:
                        strBuf.replace(dtStr.lastIndexOf("Mon"), dtStr.lastIndexOf("Mon") + 3, MONTH_ZH[cal.get(2)]);
                        break;
                    default:
                        strBuf.replace(dtStr.lastIndexOf("Mon"), dtStr.lastIndexOf("Mon") + 3, MON_LO[cal.get(2)]);
                }
            }

            return dtStr;
        } catch (StringIndexOutOfBoundsException var5) {
            throw new Exception("Parse Month Error!");
        }
    }

    private static String parseDate(Calendar cal, String dtStr, Locale lo) throws Exception {
        try {
            while(dtStr.lastIndexOf("DD") > -1) {
                StringBuffer strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("DD"), dtStr.lastIndexOf("DD") + 2, intToString(cal.get(5), 2));
                dtStr = strBuf.toString();
            }

            return dtStr;
        } catch (StringIndexOutOfBoundsException var5) {
            throw new Exception("Parse Date Error!");
        }
    }

    private static String parseHour(Calendar cal, String dtStr, Locale lo) throws Exception {
        try {
            StringBuffer strBuf;
            while(dtStr.lastIndexOf("HH24") > -1) {
                strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("HH24"), dtStr.lastIndexOf("HH24") + 4, intToString(cal.get(11), 2));
                dtStr = strBuf.toString();
            }

            while(dtStr.lastIndexOf("HH") > -1) {
                strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("HH"), dtStr.lastIndexOf("HH") + 2, intToString(cal.get(10), 2));
                dtStr = strBuf.toString();
            }

            return dtStr;
        } catch (StringIndexOutOfBoundsException var5) {
            throw new Exception("Parse Hour Error!");
        }
    }

    private static String parseMinute(Calendar cal, String dtStr, Locale lo) throws Exception {
        try {
            while(dtStr.lastIndexOf("MI") > -1) {
                StringBuffer strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("MI"), dtStr.lastIndexOf("MI") + 2, intToString(cal.get(12), 2));
                dtStr = strBuf.toString();
            }

            return dtStr;
        } catch (StringIndexOutOfBoundsException var5) {
            throw new Exception("Parse Minute Error!");
        }
    }

    private static String parseSecond(Calendar cal, String dtStr, Locale lo) throws Exception {
        try {
            while(dtStr.lastIndexOf("SS") > -1) {
                StringBuffer strBuf = new StringBuffer(dtStr);
                strBuf.replace(dtStr.lastIndexOf("SS"), dtStr.lastIndexOf("SS") + 2, intToString(cal.get(13), 2));
                dtStr = strBuf.toString();
            }

            return dtStr;
        } catch (StringIndexOutOfBoundsException var5) {
            throw new Exception("Parse Second Error!");
        }
    }

    private static String parseMeridian(Calendar cal, String dtStr, Locale lo) throws Exception {
        try {
            StringBuffer strBuf = new StringBuffer();
            int beginIndex;
            int endIndex;
            for(; dtStr.lastIndexOf("AM") > -1 || dtStr.lastIndexOf("PM") > -1; dtStr = strBuf.toString()) {
                if(dtStr.lastIndexOf("AM") > -1) {
                    beginIndex = dtStr.lastIndexOf("AM");
                    endIndex = beginIndex + 2;
                } else {
                    beginIndex = dtStr.lastIndexOf("PM");
                    endIndex = beginIndex + 2;
                }

                strBuf = new StringBuffer(dtStr);
                switch(lo.hashCode()) {
                    case 1591:
                        strBuf.replace(beginIndex, endIndex, cal.get(9) == 0?"=%A=%M":"=%P=%M");
                        break;
                    case 1861:
                        strBuf.replace(beginIndex, endIndex, cal.get(9) == 0?"=%上=%午":"=%下=%午");
                        break;
                    default:
                        strBuf.replace(beginIndex, endIndex, cal.get(9) == 0?"=%A=%M":"=%P=%M");
                }
            }

            for(; dtStr.lastIndexOf("A.M.") > -1 || dtStr.lastIndexOf("P.M.") > -1; dtStr = strBuf.toString()) {
                strBuf = new StringBuffer(dtStr);
                if(dtStr.lastIndexOf("A.M.") > -1) {
                    beginIndex = dtStr.lastIndexOf("A.M.");
                    endIndex = beginIndex + 4;
                } else {
                    beginIndex = dtStr.lastIndexOf("P.M.");
                    endIndex = beginIndex + 4;
                }

                switch(lo.hashCode()) {
                    case 1591:
                        strBuf.replace(beginIndex, endIndex, cal.get(9) == 0?"=%A=%.M=%.":"=%P=%.=%M=%.");
                        break;
                    case 1861:
                        strBuf.replace(beginIndex, endIndex, cal.get(9) == 0?"=%上=%午":"=%下=%午");
                        break;
                    default:
                        strBuf.replace(beginIndex, endIndex, cal.get(9) == 0?"=%A=%.M=%.":"=%P=%.=%M=%.");
                }
            }

            while(dtStr.lastIndexOf("=%") > -1) {
                strBuf = new StringBuffer(dtStr);
                strBuf.delete(dtStr.lastIndexOf("=%"), dtStr.lastIndexOf("=%") + 2);
                dtStr = strBuf.toString();
            }

            return dtStr;
        } catch (StringIndexOutOfBoundsException var7) {
            throw new Exception("Parse Date Error!");
        }
    }

    public static String dateToString(String fmt) throws Exception {
        return dateToString(fmt, Locale.US);
    }

    public static String dateToString(String fmt, Locale lo) throws Exception {
        Calendar cal = Calendar.getInstance();
        return calendarToString(cal, fmt, lo);
    }

    public static String dateToString(Date dt, String fmt) throws Exception {
        return dateToString(dt, fmt, Locale.US);
    }

    public static String dateToString(Date dt, String fmt, Locale lo) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return calendarToString(cal, fmt, lo);
    }

    public static String calendarToString(Calendar cal, String fmt) throws Exception {
        return calendarToString(cal, fmt, Locale.US);
    }

    public static String calendarToString(Calendar cal, String fmt, Locale lo) throws Exception {
        String dtStr = parseYear(cal, fmt, lo);
        dtStr = parseMonth(cal, dtStr, lo);
        dtStr = parseDate(cal, dtStr, lo);
        dtStr = parseHour(cal, dtStr, lo);
        dtStr = parseMinute(cal, dtStr, lo);
        dtStr = parseSecond(cal, dtStr, lo);
        dtStr = parseMeridian(cal, dtStr, lo);
        return dtStr;
    }

    public static Date getDate(int year, int month, int date) {
        return getDate(year, month, date, 0, 0, 0);
    }

    public static Date getDate(int year, int month, int date, int hour, int minute) {
        return getDate(year, month, date, hour, minute, 0);
    }

    public static Date getDate(int year, int month, int date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, hour, minute, second);
        return cal.getTime();
    }

    public static Date getDate(String year, String month, String date) throws Exception {
        return getDate(year, month, date, "0", "0", "0");
    }

    public static Date getDate(String year, String month, String date, String hour, String minute) throws Exception {
        return getDate(year, month, date, hour, minute, "0");
    }

    public static Date getDate(String year, String month, String date, String hour, String minute, String second) throws Exception {
        Calendar cal = Calendar.getInstance();

        try {
            cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(date), Integer.parseInt(hour), Integer.parseInt(minute), Integer.parseInt(second));
            return cal.getTime();
        } catch (NumberFormatException var8) {
            throw new Exception("Can not convert string to valid int!");
        }
    }

    public static Date beginOfDate(Date dt) {
        byte hour = 0;
        byte minute = 0;
        byte second = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(11, hour);
        cal.set(12, minute);
        cal.set(13, second);
        return cal.getTime();
    }

    public static Date endOfDate(Date dt) {
        byte hour = 23;
        byte minute = 59;
        byte second = 59;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(11, hour);
        cal.set(12, minute);
        cal.set(13, second);
        return cal.getTime();
    }

    public static Date addMillisecond(Date dt, int millisecond) {
        return addSecond(dt, (long)millisecond);
    }

    public static Date addMillisecond(Date dt, long millisecond) {
        dt.setTime(dt.getTime() + millisecond);
        return dt;
    }

    public static Date addSecond(Date dt, int second) {
        return addSecond(dt, (long)second);
    }

    public static Date addSecond(Date dt, float second) {
        return addSecond(dt, (double)second);
    }

    public static Date addSecond(Date dt, long second) {
        return addMillisecond(dt, 1000L * second);
    }

    public static Date addSecond(Date dt, double second) {
        Double millisecond = new Double(1000.0D * second);
        return addMillisecond(dt, millisecond.longValue());
    }

    public static Date addMinute(Date dt, int minute) {
        return addMinute(dt, (long)minute);
    }

    public static Date addMinute(Date dt, float minute) {
        return addMinute(dt, (double)minute);
    }

    public static Date addMinute(Date dt, long minute) {
        return addMillisecond(dt, 60000L * minute);
    }

    public static Date addMinute(Date dt, double minute) {
        Double millisecond = new Double(60000.0D * minute);
        return addMillisecond(dt, millisecond.longValue());
    }

    public static Date addHour(Date dt, int hour) {
        return addHour(dt, (long)hour);
    }

    public static Date addHour(Date dt, float hour) {
        return addHour(dt, (double)hour);
    }

    public static Date addHour(Date dt, long hour) {
        return addMillisecond(dt, 3600000L * hour);
    }

    public static Date addHour(Date dt, double hour) {
        Double millisecond = new Double(3600000.0D * hour);
        return addMillisecond(dt, millisecond.longValue());
    }

    public static Date addDay(Date dt, int day) {
        return addDay(dt, (long)day);
    }

    public static Date addDay(Date dt, float day) {
        return addDay(dt, (double)day);
    }

    public static Date addDay(Date dt, long day) {
        return addMillisecond(dt, 86400000L * day);
    }

    public static Date addDay(Date dt, double day) {
        Double millisecond = new Double(8.64E7D * day);
        return addMillisecond(dt, millisecond.longValue());
    }

    public static Date addMonth(Date dt, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(2, cal.get(2) + month);
        return cal.getTime();
    }

    public static Date addMonth(Date dt, float month) {
        return addMonth(dt, (double)month);
    }

    public static Date addMonth(Date dt, long month) {
        return addMonth(dt, (new Long(month)).intValue());
    }

    public static Date addMonth(Date dt, double month) {
        double floorMonth = Math.floor(month);
        double decimalMonth = month - floorMonth;
        dt = addMonth(dt, (new Double(floorMonth)).intValue());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(2, cal.get(2) + 1);
        Date nextdt = cal.getTime();
        long monthMillisecond = nextdt.getTime() - dt.getTime();
        double millisecond = (double)monthMillisecond * decimalMonth;
        return addMillisecond(dt, (long)millisecond);
    }

    public static Date addYear(Date dt, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(1, cal.get(1) + year);
        return cal.getTime();
    }

    public static Date addYear(Date dt, float year) {
        return addYear(dt, (double)year);
    }

    public static Date addYear(Date dt, long year) {
        return addYear(dt, (new Long(year)).intValue());
    }

    public static Date addYear(Date dt, double year) {
        double floorYear = Math.floor(year);
        double decimalYear = year - floorYear;
        dt = addYear(dt, (new Double(floorYear)).intValue());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(1, cal.get(1) + 1);
        Date nextdt = cal.getTime();
        long yearMillisecond = nextdt.getTime() - dt.getTime();
        double millisecond = (double)yearMillisecond * decimalYear;
        return addSecond(dt, (long)millisecond);
    }

    public static String getStrYear(int curYear) {
        return String.valueOf(curYear) == null?null:(curYear - 2000 >= 0 && curYear - 2000 <= 25?STR_YEAR[curYear - 2000]:null);
    }

    public static String getCurDate(String str) {
        try {
            return dateToString(new Date(), str);
        } catch (Exception var2) {
            return null;
        }
    }

    public static String getCurDate(Calendar calendar, String str) {
        try {
            return calendarToString(calendar, str);
        } catch (Exception var3) {
            return null;
        }
    }

    public static boolean isLeapYear(String str, String datePattern) throws Exception {
        Calendar cal = stringToCalendar(str, datePattern);
        GregorianCalendar gc = new GregorianCalendar();
        return gc.isLeapYear(cal.get(1));
    }

    public static boolean isLeapYear(String str) throws Exception {
        return isLeapYear(str, datePattern);
    }

    public static int getLastDayOfMonth(int year, int month) {
        boolean day = true;
        byte day1;
        switch(month) {
            case 1:
                day1 = 31;
                break;
            case 2:
                GregorianCalendar gc = new GregorianCalendar();
                if(gc.isLeapYear(year)) {
                    day1 = 29;
                } else {
                    day1 = 28;
                }
                break;
            case 3:
                day1 = 31;
                break;
            case 4:
                day1 = 30;
                break;
            case 5:
                day1 = 31;
                break;
            case 6:
                day1 = 30;
                break;
            case 7:
                day1 = 31;
                break;
            case 8:
                day1 = 31;
                break;
            case 9:
                day1 = 30;
                break;
            case 10:
                day1 = 31;
                break;
            case 11:
                day1 = 30;
                break;
            case 12:
                day1 = 31;
                break;
            default:
                day1 = 30;
        }

        return day1;
    }

    public static int getLastDayOfMonth(String str, String datePattern) throws Exception {
        Calendar cal = stringToCalendar(str, datePattern);
        return getLastDayOfMonth(cal.get(1), cal.get(2));
    }

    public static int getLastDayOfMonth(String str) throws Exception {
        Calendar cal = stringToCalendar(str, datePattern);
        return getLastDayOfMonth(cal.get(1), cal.get(2) + 1);
    }

    public static String getLastYearAcctPeriodNo(String acctPeriodNo) {
        String ret = "";
        acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
        if(!acctPeriodNo.equals("") && acctPeriodNo.length() == 6) {
            String strYear = acctPeriodNo.substring(0, 4);
            String strMonth = acctPeriodNo.substring(4, 6);
            if(strMonth.equals("00")) {
                ret = Integer.parseInt(strYear) - 2 + "13";
            } else {
                ret = Integer.parseInt(strYear) - 1 + "13";
            }

            return ret;
        } else {
            return "";
        }
    }

    public static String getLastMonth(String acctPeriodNo) {
        String ret = "";
        acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
        if(!acctPeriodNo.equals("") && acctPeriodNo.length() == 6) {
            String strYear = acctPeriodNo.substring(0, 4);
            String strMonth = acctPeriodNo.substring(4, 6);
            if(strMonth.equals("01")) {
                ret = Integer.parseInt(strYear) - 1 + "12";
            } else {
                int month = Integer.parseInt(strMonth) - 1;
                strMonth = month > 9?String.valueOf(month):"0" + month;
                ret = strYear + strMonth;
            }

            return ret;
        } else {
            return "";
        }
    }

    public static String getLastPeriodOfQuarter(String acctPeriodNo) {
        String ret = "";
        acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
        if(!acctPeriodNo.equals("") && acctPeriodNo.length() == 6) {
            String strYear = acctPeriodNo.substring(0, 4);
            int quarter = getQuarterOfMonth(acctPeriodNo);
            String month = getLastMonthOfQuarter(quarter);
            ret = strYear + month;
            return ret;
        } else {
            return "";
        }
    }

    public static String getLastYearPeriodOfQuarter(String acctPeriodNo) {
        String ret = "";
        acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
        if(!acctPeriodNo.equals("") && acctPeriodNo.length() == 6) {
            int year = Integer.parseInt(acctPeriodNo.substring(0, 4)) - 1;
            int quarter = getQuarterOfMonth(acctPeriodNo);
            String month = getLastMonthOfQuarter(quarter);
            ret = year + month;
            return ret;
        } else {
            return "";
        }
    }

    public static String getLastPeriodOfLastQuarter(String acctPeriodNo) {
        String ret = "";
        acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
        if(!acctPeriodNo.equals("") && acctPeriodNo.length() == 6) {
            int year = Integer.parseInt(acctPeriodNo.substring(0, 4));
            int quarter = getQuarterOfMonth(acctPeriodNo);
            if(quarter == 1) {
                --year;
                quarter = 4;
            } else {
                --quarter;
            }

            String month = getLastMonthOfQuarter(quarter);
            ret = year + month;
            return ret;
        } else {
            return "";
        }
    }

    public static int getQuarterOfMonth(String acctPeriodNo) {
        boolean ret = true;
        acctPeriodNo = StringUtils.defaultIfEmpty(acctPeriodNo, "");
        if(!acctPeriodNo.equals("") && acctPeriodNo.length() == 6) {
            int month = Integer.parseInt(acctPeriodNo.substring(4, 6));
            byte ret1;
            switch(month) {
                case 1:
                case 2:
                case 3:
                    ret1 = 1;
                    break;
                case 4:
                case 5:
                case 6:
                    ret1 = 2;
                    break;
                case 7:
                case 8:
                case 9:
                    ret1 = 3;
                    break;
                case 10:
                case 11:
                case 12:
                    ret1 = 4;
                    break;
                default:
                    ret1 = 1;
            }

            return ret1;
        } else {
            return 1;
        }
    }

    public static String getLastMonthOfQuarter(int quarter) {
        String ret = "03";
        switch(quarter) {
            case 1:
                ret = "03";
                break;
            case 2:
                ret = "06";
                break;
            case 3:
                ret = "09";
                break;
            case 4:
                ret = "12";
                break;
            default:
                ret = "01";
        }

        return ret;
    }

    private static SimpleDateFormat getDateParser(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static String curDateTimeStr14() {
        Date date = new Date();
        return getDateParser("yyyyMMddHHmmss").format(date);
    }

//    public static void main(String[] args) {
//        log.info(convertDateToString(new Date()));
//
//        try {
//            System.out.println(convertStringToDate("ddMMyyyy", "30072004").toString());
//            Date e = stringToDate("20070401", "YYYYMMDD");
//            System.out.println(e.toString());
//            System.out.println("---" + getTimeNow(new Date()));
//            System.out.println(getLastYearAcctPeriodNo("200801"));
//            System.out.println(getLastMonth("200812"));
//            System.out.println(getLastMonth("200801"));
//            System.out.println(getLastPeriodOfLastQuarter("200801"));
//            System.out.println(getLastPeriodOfLastQuarter("200810"));
//            System.out.println(getLastPeriodOfQuarter("200801"));
//            System.out.println(getLastYearPeriodOfQuarter("200801"));
//        } catch (Exception var2) {
//            var2.printStackTrace();
//        }
//
//    }

    static {
        timePattern = datePattern + " HH:MM a";
        STR_YEAR = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        WEEK_ZH = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        MONTH_ZH = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        MONTH_UP = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        MONTH_LO = new String[]{"Janury", "February", "March", "April", "May", "June", "July", "Auguest", "September", "October", "Novenber", "December"};
        MON_UP = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        MON_LO = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        divisionChars = "- / , . ; :";
    }
}
