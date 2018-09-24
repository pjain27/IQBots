package api.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.text.ParseException;

/**
 * Created by hbojja on 6/16/16.
 */
public class MyStringRandomGen extends Utility {
    static Calendar now = Calendar.getInstance();

    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    /**
     * @author: Quoc Le
     * @ActionName: generateTimeStampString
     * @CreatedDate: 12/23/2016
     * This method generates timestamp
     * @return randomStr
     *  - random string like: 2016-12-23-01-46-16
     */
    public String generateTimeStampString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String timestampStr = dtf.format(now);
        return timestampStr;
    }

    public static String generateTimeStampString(int length){
        String timestampStr = null;
        if(length<=14 && length >0) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            timestampStr = dtf.format(now);
        }
        return right(timestampStr, length);
    }

    public static int getDayOfMonth() {
        return now.get(Calendar.DATE);
    }
    public static int getMonth() {
        return now.get(Calendar.MONTH);
    }
    public static int getyear() {
        return now.get(Calendar.YEAR);
    }

    public static String right(String value, int length) {
        // To get right characters from a string, change the begin index.
        return value.substring(value.length() - length);
    }

    public boolean isThisDateValid(String dateToValidate, String dateFormat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            sdf.parse(dateToValidate);

        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}