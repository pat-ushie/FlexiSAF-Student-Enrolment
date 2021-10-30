/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.exceptions;

import java.io.Serializable;

/**
 * 
 * To be thrown when one attempts to create a department whose name matches an already existing department.
 *
 * @author ushie
 */
public class DepartmentAlreadyExistsException extends Exception implements Serializable {

    /** 
     * Creates a new instance of DepartmentAlreadyExistsException 
     */
    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }

}
