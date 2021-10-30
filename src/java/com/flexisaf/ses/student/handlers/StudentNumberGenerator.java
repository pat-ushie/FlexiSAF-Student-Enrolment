/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.student.handlers;

import java.io.Serializable;

/**
 * This class generates the student matric number following the specified rule.
 *
 * @author ushie
 */
public class StudentNumberGenerator implements Serializable {

    public static final String MAT_NUMBER_PREFIX = "FLEXISAF/";
    //
    public static final int MAT_SERIAL_NUMBER_LEN = 3;

    public static String generateMatricNumber(long serialNumber) {
        return MAT_NUMBER_PREFIX + padString(String.valueOf(serialNumber));
    }

    private static String padString(String serialNumberStr) {
        while (serialNumberStr.length() < MAT_SERIAL_NUMBER_LEN) {
            serialNumberStr = "0" + serialNumberStr;
        }

        return serialNumberStr;
    }

}
