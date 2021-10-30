/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * To be thrown when a required date field is null or empty.
 *
 * @author ushie
 */
public class UnspecifiedDateException extends Exception implements Serializable {

    /** 
     * Creates a new instance of UnspecifiedDateException 
     */
    public UnspecifiedDateException(String message) {
        super(message);
    }

}
