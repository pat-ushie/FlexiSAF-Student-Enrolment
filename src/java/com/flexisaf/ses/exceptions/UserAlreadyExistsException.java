/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * 
 * This is thrown when a client request attempts to 
 * create a user whose user name matches that of an existing user.
 *
 * @author ushie
 */
public class UserAlreadyExistsException extends Exception implements Serializable {

    /** 
     * Creates a new instance of UserAlreadyExistsException 
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
