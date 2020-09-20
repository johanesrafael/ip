package Task.DukeHelper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateAndTime {
    public static String convertDate(String date){
        LocalDate convertedDate = LocalDate.parse(date);
        return convertedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    public static String convertTime(String time){
        LocalTime convertedTime = LocalTime.parse(time);
        return convertedTime.format(DateTimeFormatter.ofPattern("h:mm a"));
    }
}
