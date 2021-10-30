/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * To  be thrown when the specified age false outside the required boundaries.
 *
 * @author ushie
 */
public class AgeOutOfBoundsException extends Exception implements Serializable {

    /** 
     * Creates a new instance of AgeOutOfBoundsException 
     */
    public AgeOutOfBoundsException(String message) {
        super(message);
    }

}
