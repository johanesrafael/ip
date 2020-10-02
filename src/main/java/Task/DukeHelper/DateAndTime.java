package Task.DukeHelper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateAndTime {
    /**
     * converts date from pattern yyyy-mm-dd to MMM dd yyy
     * @param date Date of input
     * @return
     */
    public static String convertDate(String date){
        LocalDate convertedDate = LocalDate.parse(date);
        return convertedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * converts time from 24 hours pattern to 12 hours am pm pattern
     * @param time Time of input
     * @return
     */
    public static String convertTime(String time){
        LocalTime convertedTime = LocalTime.parse(time);
        return convertedTime.format(DateTimeFormatter.ofPattern("h:mm a"));
    }
}
