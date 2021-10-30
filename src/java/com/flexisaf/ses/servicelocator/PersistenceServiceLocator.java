/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.servicelocator;

import com.flexisaf.ses.eao.AcademicDepartmentRemote;
import java.io.Serializable;
import javax.naming.InitialContext;
import com.flexisaf.ses.eao.StudentEnrollmentRemote;
import com.flexisaf.ses.eao.UserAccountRemote;

/**
 * This utility provides the old fashion way of accessing an EJB bean. 
 * I had to use this as the @EJB annotation failed to inject my beans.
 * 
 *
 * @author ushie
 */
public class PersistenceServiceLocator implements Serializable {

    /**
     * Retrieves the <code>StudentEnrollmentRemote</code> stateless session bean 
     * @return 
     */
    public static StudentEnrollmentRemote locateStudentPersistenceManager() {
        StudentEnrollmentRemote dataServer = (StudentEnrollmentRemote) getJNDIBinding(
                "com.flexisaf.ses.eao.StudentEnrollmentRemote");
        return dataServer;
    }
    
    /**
     * Retrieves the <code>UserAccountRemote</code> stateless session bean 
     * @return 
     */
    public static UserAccountRemote locateUserAccountPersistenceManager() {
        UserAccountRemote dataServer = (UserAccountRemote) getJNDIBinding(
                "com.flexisaf.ses.eao.UserAccountRemote");
        return dataServer;
    }
    
    /**
     * Retrieves the <code>AcademicDepartmentRemote</code> stateless session bean 
     * @return 
     */
    public static AcademicDepartmentRemote locateAcademicDepartmentPersistenceManager() {
        AcademicDepartmentRemote dataServer = (AcademicDepartmentRemote) getJNDIBinding(
                "com.flexisaf.ses.eao.AcademicDepartmentRemote");
        return dataServer;
    }
    
    /**
     * This is the JNDI lookup code.
     * 
     * @param jndiName
     * @return 
     */
    private static Object getJNDIBinding(String jndiName) {
        Object handle = null;
        try {
            InitialContext context = new InitialContext();
            handle = context.lookup(jndiName);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
        }

        return handle;
    }

}
