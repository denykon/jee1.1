package by.gsu.epamlab.model.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date for Task
 */
public class DateCreator {

    private static final String TODAY = "today";
    private static final String TOMORROW = "tomorrow";
    private static final String DATE_EXPRESSION = "yyyy-MM-dd";
    private static final int DAY = 1000 * 60 * 60 * 24; //24 hours

    public static String create(String day) {
        String date = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_EXPRESSION);
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + DAY);

        switch (day) {
            case TODAY:
                date = dateFormat.format(today);
                break;
            case TOMORROW:
                date = dateFormat.format(tomorrow);
                break;
        }
        return date;
    }
}
