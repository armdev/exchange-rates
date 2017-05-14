/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.web.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author armen
 */
public class DateUtils {
    
    // why I am not using Java 8 or Joda time????? Because for production work only this code. Joda and Java 8 still for console applications.

    static public Integer calculateAge(Date birthdate) {
        if (birthdate == null) {
            return null;
        }

        Calendar birthdate_calendar = new GregorianCalendar();
        Calendar thisdate_calendar = new GregorianCalendar();
        Integer age = null;
        try {
            birthdate_calendar.setTime(birthdate);
            thisdate_calendar.setTime(new Date());

            age = thisdate_calendar.get(Calendar.YEAR) - birthdate_calendar.get(Calendar.YEAR);

            if ((birthdate_calendar.get(Calendar.MONTH) > thisdate_calendar.get(Calendar.MONTH)) || (birthdate_calendar.get(Calendar.MONTH) == thisdate_calendar.get(Calendar.MONTH) && birthdate_calendar.get(Calendar.DAY_OF_MONTH) > thisdate_calendar.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

        } catch (ArrayIndexOutOfBoundsException ex) {
        }

        return age;
    }
}
