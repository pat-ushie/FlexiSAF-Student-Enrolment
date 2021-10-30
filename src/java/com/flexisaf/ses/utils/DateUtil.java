/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.utils;

import com.flexisaf.ses.exceptions.DateFormatException;
import com.flexisaf.ses.exceptions.UnspecifiedDateException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class for date operations.
 *
 * @author ushie
 */
public class DateUtil implements Serializable {

    /**
     * Create a date object from a date string with the format: YYYY-MM-dd hh:mm:ss
     * 
     * @param dateAsString
     * @return 
     */
    public static Date toUtilDate(String dateAsString) {
        Calendar cal = Calendar.getInstance();

        int y1 = Integer.parseInt(parseDatePart(dateAsString, 0, '-'));
        int m1 = Integer.parseInt(parseDatePart(dateAsString, 1, '-')) - 1;
        int d1 = Integer.parseInt(parseDatePart(dateAsString, 2, '-'));

        if (dateAsString.indexOf(":") > -1) {
            String[] timeParts = dateAsString.substring(dateAsString.indexOf(" ")).split(":");
            cal.set(y1, m1, d1, Integer.parseInt(timeParts[0].trim()), Integer.parseInt(timeParts[1].trim()));
        } else {
            cal.set(y1, m1, d1);
        }

        return cal.getTime();
    }

    /**
     * Gets a date element by position <code>index</code> and <code>delimiter</code>
     * 
     * @param dateStrg
     * @param index
     * @param delimiter
     * @return 
     */
    public static String parseDatePart(String dateStrg, int index, char delimiter) {
        String strToken = "";
        int count = 0;
        if (index == 0) {
            while (dateStrg.charAt(count) != delimiter) {
                strToken += String.valueOf(dateStrg.charAt(count++));
            }
        }

        if (index == 1) {
            while (dateStrg.charAt(count) != delimiter) {
                count++;
            }
            count++;
            while (dateStrg.charAt(count) != delimiter) {
                strToken += String.valueOf(dateStrg.charAt(count++));
            }

        }

        if (index == 2) {
            while (dateStrg.charAt(count) != delimiter) {
                count++;
            }

            count++;
            while (dateStrg.charAt(count) != delimiter) {
                count++;
            }

            count++;
            while ((count <= dateStrg.length() - 1) && (dateStrg.charAt(count) != ' ')) {
                strToken += String.valueOf(dateStrg.charAt(count++));
            }

        }

        return strToken;
    }

    /**
     * Calculate age given a date value <code>birthDate</code>.
     * 
     * @param birthDate
     * @return
     * @throws DateFormatException
     * @throws UnspecifiedDateException 
     */
    public static int calculateAge(Date birthDate)
            throws DateFormatException, UnspecifiedDateException {
        int age = 0;

        if (birthDate == null) {
            throw new UnspecifiedDateException(
                    "The Specified Date Cannot Be Null");
        }

        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(birthDate);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());

        int y1 = dobCal.get(Calendar.YEAR);
        int m1 = dobCal.get(Calendar.MONTH) + 1;
        int d1 = dobCal.get(Calendar.DAY_OF_MONTH);

        int y2 = todayCal.get(Calendar.YEAR);
        int m2 = todayCal.get(Calendar.MONTH) + 1;
        int d2 = todayCal.get(Calendar.DAY_OF_MONTH);

        if ((isValidDate(d1, m1, y1)) && (isValidDate(d2, m2, y2))) {
            age = y2 - y1;
            if (m2 < m1) {
                age--;
            } else if ((m2 == m1) && (d2 < d1)) {
                age--;
            }
        } else {
            throw new DateFormatException(
                    "One or more input date values is/are invalid");
        }

        return age;
    }

    /**
     * checks if a combination of date elements (day, month, and year) is a valid date.
     * 
     * @param day
     * @param month
     * @param year
     * @return 
     */
    static boolean isValidDate(int day, int month, int year) {
        boolean ok = false;

        if ((month == 1)
                || (month == 3)
                || (month == 5)
                || (month == 7)
                || (month == 8)
                || (month == 10)
                || (month == 12)) {
            if ((day >= 1) && (day <= 31)) {
                ok = true;
            }
        } else if (month == 2) {
            if (isLeapYear(year)) {
                if ((day >= 1) && (day <= 29)) {
                    ok = true;
                }
            } else if ((day >= 1) && (day <= 28)) {
                ok = true;
            }
        } else if ((month == 9) || (month == 4) || (month == 6) || (month == 11)) {
            if ((day >= 1) && (day <= 30)) {
                ok = true;
            }
        }

        return ok;
    }

    /**
     * Checks if a year is a leap year.
     * 
     * @param year
     * @return 
     */
    static boolean isLeapYear(int year) {
        boolean leapYear = false;

        if (year < 100) {
            if (year > 40) {
                year += 1900;
            } else {
                year += 2000;
            }
        }
        if (year % 4 == 0) {
            if (year % 100 != 0) {
                leapYear = true;
            } else if (year % 400 == 0) {
                leapYear = true;
            }
        }

        return leapYear;
    }

}
