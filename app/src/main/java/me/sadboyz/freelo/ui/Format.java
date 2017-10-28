package me.sadboyz.freelo.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hugo on 28/10/17.
 */

public class Format {
    public static String completeDateFormat(String firebaseDate) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try{
        cal.setTime(sdf.parse(firebaseDate));}
        catch (ParseException ex){}

        SimpleDateFormat fecc = new SimpleDateFormat("d 'de' MMMM 'del' yyyy");

        String fechacComplString = fecc.format(cal.getTime());
        return fechacComplString;
    }
}
