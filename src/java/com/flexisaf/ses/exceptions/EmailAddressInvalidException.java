/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * 
 * To be thrown when an email address doesn't match the acceptable format.
 *
 * @author ushie
 */
public class EmailAddressInvalidException extends Exception implements Serializable {

    /** 
     * Creates a new instance of EmailAddressInvalidException 
     */
    public EmailAddressInvalidException(String message) {
        super(message);
    }

}
