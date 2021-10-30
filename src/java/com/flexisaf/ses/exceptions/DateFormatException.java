/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * 
 * To be thrown when a date string doesn't match the preferred format for parsing.
 *
 * @author ushie
 */
public class DateFormatException extends Exception implements Serializable {

    /** 
     * Creates a new instance of DateFormatException 
     */
    public DateFormatException(String message) {
        super(message);
    }
}
