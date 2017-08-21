package com.xhuihui.app.carTcp;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/29.
 */
public class Tools {


    public static long sendCount = 0L;
    // TODO  婵″倹鐏夐弰顖氬鐎靛棛娈戦弫鐗堝祦,鐎靛棝鎸滈弳鍌欑瑬鐎规矮缍�234,閸氬海鐢婚張澶婄窡婢跺嫮鎮�
    private static int ENCREPT_KEY = 1234;
    // TODO  濞戝牊浼呴悧鍫熸拱閺嗗倷绗栫�姘秴1.0.0,閸氬海鐢婚張澶婄窡婢跺嫮鎮�
    private static String PROTOCOL_VERSION = "10000";

    /**
     * 瀵版鍤瑿RC鐠侊紕鐣荤紒鎾寸亯
     *
     * @param buff
     *            鐟曚浇顓哥粻妗烺C閻ㄥ嫬鐡х粭锔胯
     * @return 鐎涙顑佹稉锟�娑擃亜鐡ч懞锟�     */
    public static String getCRCString(String buff) {
        int crc = 0xFFFF; // initial value
        int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

        byte[] data = HexString2Bytes(buff);

        for (int j = 0; j < data.length; j++) {
            // char b = buf.charAt(j);
            byte b = data[j];
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit)
                    crc ^= polynomial;
            }
        }
        crc &= 0xffff;
        String str = ToHexString(crc, 2);

        return str;
    }

    public static String CRC(String msg) {
        byte[] data = HexString2Bytes(msg);
        int crc = CRC(data);
        String str = ToHexString(crc, 2);
        return str;

    }

    public static int CRC(byte[] buffer) {
        int wTemp = 0;
        int crc = 0xffff;

        for (int i = 0; i < buffer.length; i++) {
            for (int j = 0; j < 8; j++) {
                wTemp = (short) (((buffer[i] << j) & 0x80) ^ ((crc & 0x8000) >> 8));

                crc <<= 1;

                if (wTemp != 0) {
                    crc ^= 0x1021;
                }
            }
        }

        return (crc);
    }

    public static long getSendCount() {
        if (sendCount > 1000000000L)
            sendCount = 1L;
        else {
            sendCount += 1L;
        }
        return sendCount;
    }

    public static String turnDataLength(String data, int length) {
        int data_length = data.length();
        for (int i = data_length; i < length; i++) {
            data = "0" + data;
        }
        return data;
    }

    public static String turnStrLength(String data, int length) {
        int data_length = data.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = data_length; i < length; i++) {
            buffer.append("0");
        }
        return buffer.append(data).toString();
    }

    public static String ToHexString(byte[] bts) {
        StringBuilder strBuild = new StringBuilder();

        for (int i = 0; i < bts.length; i++) {
            strBuild.append(ToHexString(bts[i]));
        }
        return strBuild.toString();
    }

    public static String hex2Ascii(String hex) {
        String result = "";
        int len = hex.length() / 2;
        for (int i = 0; i < len; i++) {
            int tmp = Integer.valueOf(hex.substring(2 * i, 2 * i + 2), 16).intValue();
            result = result + (char) tmp;
        }
        return result;
    }

    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;

        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            // 閹存牞绻嶇粻锟介崗鏈电娑擄拷閸掓瑤璐�
            b[i] = ((byte) (parse(c0) << 4 | parse(c1)));
            // int start = i * 2;
            // int c = Integer.parseInt(hexstr.substring(start, start + 2));
            // b[i] = (byte)c;
        }

        return b;
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return c - 'a' + 10 & 0xF;
        }

        if (c >= 'A') {
            return c - 'A' + 10 & 0xF;
        }

        return c - '0' & 0xF;
    }

    public static String TurnISN(String str) {
        String str1 = "";
        byte[] b = (byte[]) null;
        try {
            b = str.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {

        }

        int i = 0;
        for (int max = b.length; i < max; i++) {
            str1 = str1 + Integer.toHexString(b[i] & 0xFF);
        }
        return str1.toUpperCase();
    }

    // 鐏忓棙鏆熺�妤勬祮閹广垺鍨�6鏉╂稑鍩楃�妤冾儊娑撹绱濇稉宥堝喕鐞涖儵娴�
    public static String ToHexString(byte data) {
        return Integer.toHexString(data & 0xff | 0x100).substring(1);
    }

    // 鐏忓棙鏆熺�妤勬祮閹广垺鍨�6鏉╂稑鍩楃�妤冾儊娑撹绱濇稉宥堝喕鐞涖儵娴�
    public static String ToHexString(Short data) {
        return Integer.toHexString(data & 0xffff | 0x10000).substring(1);
    }

    public static String ToHexString(long val) {
        String tmp = Long.toHexString(val);
        StringBuilder sb = new StringBuilder("0000000000000000");
        sb.replace(16 - tmp.length(), 16, tmp);
        return sb.toString();
    }

    // 鐏忓棙鏆熺�妤勬祮閹广垺鍨�6鏉╂稑鍩楃�妤冾儊娑撹绱濇稉宥堝喕鐞涖儵娴�
    public static String ToHexString(int data) {
        String tmp = Integer.toHexString(data);
        StringBuilder sb = new StringBuilder("00000000");
        sb.replace(8 - tmp.length(), 8, tmp);
        return sb.toString();
    }

    public static String ToHexString(long data, int byteNum) {

        StringBuilder sb = new StringBuilder("");
        for (int m = 0; m < byteNum; m++) {
            sb.append("00");
        }
        int totalLen = byteNum * 2;
        String tmp = Long.toHexString(data);
        if (tmp.length() <= totalLen) {
            sb.replace(totalLen - tmp.length(), totalLen, tmp);
        } else {
            return tmp.substring(tmp.length() - totalLen, tmp.length());
        }
        return sb.toString();
    }

    /**
     * 鐏忓棗鐡х粭锔胯鏉烆剚宕查幋锟�鏉╂稑鍩楁稉锟�     */
    public static String ToHexString(String str) {
        String str1 = "";
        try {
            byte[] b = str.getBytes("gbk");
            int i = 0;
            int max = b.length;
            for (; i < max; i++) {
                str1 = str1 + Integer.toHexString(b[i] & 0xFF);
            }
        } catch (UnsupportedEncodingException e) {

        }
        return str1;
    }

    /**
     * 鐏忓棗鐡х粭锔胯鏉烆剚宕查幋锟�鏉╂稑鍩楁稉璇х礉闂�灝瀹虫稉宥堝喕鐞涖儵娴�
     */
    public static String ToHexString(String str, int length) {
        if (str == null)
            str = "";
        String str1 = "";
        byte[] b = (byte[]) null;
        try {
            b = str.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
        }

        int i = 0;
        int max = b.length;
        for (; i < max; i++) {
            str1 = str1 + Integer.toHexString(b[i] & 0xFF);
        }
        str1 = str1.toUpperCase();

        return turnStrLength(str1, length * 2);
    }

    public static String getStringFromHex(String str1) {
        try {
            str1 = new String(HexString2Bytes(str1), "gbk");
        } catch (UnsupportedEncodingException e) {
        }
        return str1;
    }

    public static int getYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String year = format.format(date);
        return Integer.valueOf(year.substring(0, 4)).intValue();
    }

    public static int getMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String year = format.format(date);
        return Integer.valueOf(year.substring(4, 6)).intValue();
    }

    public static int getDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String year = format.format(date);
        return Integer.valueOf(year.substring(6, 8)).intValue();
    }

    public static int getHour(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String year = format.format(date);
        return Integer.valueOf(year.substring(8, 10)).intValue();
    }

    public static int getMinute(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String year = format.format(date);
        return Integer.valueOf(year.substring(10, 12)).intValue();
    }

    public static int getSeconds(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String year = format.format(date);
        return Integer.valueOf(year.substring(12, 14)).intValue();
    }

    public static String getDateBCDStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        return format.format(date);
    }

    public static String getHexDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String hexdate = format.format(date);

        String year = turnDataLength(Integer.toString(Integer.valueOf(hexdate.substring(2, 4)).intValue(), 16), 2);
        String month = turnDataLength(Integer.toString(Integer.valueOf(hexdate.substring(4, 6)).intValue(), 16), 2);
        String day = turnDataLength(Integer.toString(Integer.valueOf(hexdate.substring(6, 8)).intValue(), 16), 2);
        String hour = turnDataLength(Integer.toString(Integer.valueOf(hexdate.substring(8, 10)).intValue(), 16), 2);
        String minute = turnDataLength(Integer.toString(Integer.valueOf(hexdate.substring(10, 12)).intValue(), 16), 2);
        String seconds = turnDataLength(Integer.toString(Integer.valueOf(hexdate.substring(12, 14)).intValue(), 16), 2);

        return year+ month + day + hour + minute + seconds;
    }

    // 鐎甸�绨崣鍌涙殶閹稿洤鐣鹃惃鍕）閺堢喎鎷伴弮鍫曟？閿涘矁绻戦崶鐐跺殰 1970 楠烇拷1 閺堬拷1 閺冿拷00:00:00 GMT 娴犮儲娼甸惃鍕嚑缁夋帗鏆�
    @SuppressWarnings({ "static-access", "deprecation" })
    public static String getUTC(Date date) {

        // long dt = date.getTime()/1000;//

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long dt = date.UTC(date.getYear(), date.getMonth(), date.getDay(), calendar.get(Calendar.HOUR_OF_DAY) - 8,
                date.getMinutes(), date.getSeconds());
        return ToHexString(dt / 1000, 8);
    }

    public static long myround(double num) {
        BigDecimal b = new BigDecimal(num);
        num = b.setScale(2, 4).longValue();
        return (long) num;
    }

    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(str);
        } catch (ParseException e) {
        }
        return null;
    }

    public static String encoderStringEscape(String strEscape) {
        strEscape = strEscape.toUpperCase();
        int byteNum = strEscape.length() / 2;
        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < byteNum; m++) {
            int start = m * 2;

            String temp = strEscape.substring(start, start + 2);
            if (temp.equals("5A"))
                sb.append("5A02");
            else if (temp.equals("5B"))
                sb.append("5A01");
            else if (temp.equals("5E"))
                sb.append("5E02");
            else if (temp.equals("5D"))
                sb.append("5E01");
            else
                sb.append(temp);
        }
        // strEscape = strEscape.replaceAll("5A", "5A02");
        // strEscape = strEscape.replaceAll("5B", "5A01");
        // strEscape = strEscape.replaceAll("5E", "5E02");
        // strEscape = strEscape.replaceAll("5D", "5E01");
        return sb.toString();
    }

    public static String decoderStringEscape(String strEscape) {
        strEscape = strEscape.toUpperCase();
        strEscape = strEscape.replaceAll("5A01", "5B");
        strEscape = strEscape.replaceAll("5a01", "5B");
        strEscape = strEscape.replaceAll("5A02", "5A");
        strEscape = strEscape.replaceAll("5a02", "5A");
        strEscape = strEscape.replaceAll("5E01", "5D");
        strEscape = strEscape.replaceAll("5e01", "5D");
        strEscape = strEscape.replaceAll("5E02", "5E");
        strEscape = strEscape.replaceAll("5e02", "5E");
        return strEscape;
    }

    public static String getHeaderAndFlag(long counter, String body, int dataType, long centerId, boolean isEncrypt) {
        if (body == null)
            body = "";
        String header = "5B";

        String ver = turnDataLength(PROTOCOL_VERSION, 6);

        String encryptFlag = "00";

        String encryptKey = "00000000";
        String endFlag = "5D";
        int length = body.length() / 2; // 鐎涙濡梹鍨

        long DateLength = length + 26;

        if (isEncrypt) {
            encryptFlag = "01"; // 閸旂姴鐦戦弽鍥х箶娴ｏ拷
            body = Tools.encrypt(ENCREPT_KEY, body);
            encryptKey = ToHexString(ENCREPT_KEY, 4);
        }
        // 濞戝牊浼呮担锟�      
        String message = ToHexString(DateLength, 4) + ToHexString(counter, 4) + ToHexString(dataType, 2)
                + ToHexString(centerId, 4) + ver + encryptFlag + encryptKey + body;

        message = message + getCRCString(message);

        // int mesLength = message.length();

        message = encoderStringEscape(message);

        message = header + message + endFlag;
        return message;
    }

    public static Timestamp convertToTimestamp(String message) {
        long time = Long.valueOf(message, 16).longValue();

        int year = (int) (time >> 26 & 0x3F) + 2000;
        int month = (int) (time >> 22 & 0xF);
        int day = (int) (time >> 17 & 0x1F);
        int hour = (int) (time >> 12 & 0x1F);
        int minute = (int) (time >> 6 & 0x3F);
        int second = (int) (time & 0x3F);
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, day, hour, minute, second);
        return new Timestamp(cd.getTimeInMillis());
    }

    public static String encrypt(int _key, String str) {
        long key = _key;
        int m1 = 10000000;
        int a1 = 20000000;
        int c1 = 30000000;

        byte[] b = HexString2Bytes(str);
        int size = b.length;
        if (key == 0)
            key = 1;
        int i = 0;
        while (i < size) {
            int x = (int) (a1 * (key % m1));
            String tx = ToHexString(x);
            long t = Long.valueOf(tx, 16);

            key = (t + c1);
            // short te = (short)(b[i] ^ (short) (key >> 20) & 0xFF);
            b[i++] ^= ((short) (key >> 20) & 0xFF);
        }
        String result = ToHexString(b);

        return result;
    }

    public static byte bcc(String data) {
        byte[] bytes = HexString2Bytes(data);
        byte bcc=0;
        for (int i = 0; i < bytes.length; i++) {
            bcc^=bytes[i];
        }
        return bcc;
    }

    public static void main(String[] args) {
        Byte[] bytes = dateToByte("161229140330");
        for (int i=0;i<bytes.length;i++){
            System.out.println(bytes[i]);
        }
    }

    public static Byte[] dateToByte(String str){
        if(str!=null){
            Byte[] bytes = new Byte[str.length()/2];
            for (int i=0;i<str.length();i+=2){
                bytes[i/2]=Byte.decode(str.substring(i,i+2));
            }
            return bytes;
        }else{
            return new Byte[0];
        }
    }
}
