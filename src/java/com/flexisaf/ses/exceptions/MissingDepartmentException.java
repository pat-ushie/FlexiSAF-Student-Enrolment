/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * 
 * To be thrown when a student object sent over the wire doesn't include a department ID.
 *
 * @author ushie
 */
public class MissingDepartmentException extends Exception implements Serializable {

    /** 
     * Creates a new instance of MissingDepartmentException 
     */
    public MissingDepartmentException(String message) {
        super(message);
    }

}
