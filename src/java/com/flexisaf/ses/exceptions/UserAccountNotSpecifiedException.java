/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * To be thrown when a client request doesn't have a user account. 
 *
 * @author ushie
 */
public class UserAccountNotSpecifiedException extends Exception implements Serializable {

    /** 
     * Creates a new instance of UserAccountNotSpecifiedException 
     */
    public UserAccountNotSpecifiedException(String message) {
        super(message);
    }

}
