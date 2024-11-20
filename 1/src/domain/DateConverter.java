package domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Serializable {
    public Date convertStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}