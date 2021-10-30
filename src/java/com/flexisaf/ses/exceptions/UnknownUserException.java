/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * 
 * To be thrown when the specified user account passed along 
 * with a client request doesn't exist in the system.
 *
 * @author ushie
 */
public class UnknownUserException extends Exception implements Serializable {

    /** 
     * Creates a new instance of UnknownUserException 
     */
    public UnknownUserException(String message) {
        super(message);
    }

}
