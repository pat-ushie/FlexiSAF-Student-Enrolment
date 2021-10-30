/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * 
 * To be thrown when the gender type value is either unspecified or doesn't match the defined values.
 *
 * @author ushie
 */
public class InvalidGenderTypeException extends Exception implements Serializable {

    /** 
     * Creates a new instance of DateFormatException 
     */
    public InvalidGenderTypeException(String message) {
        super(message);
    }

}
