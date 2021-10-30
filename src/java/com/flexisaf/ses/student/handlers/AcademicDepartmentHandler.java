/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.student.handlers;

import com.flexisaf.ses.eao.AcademicDepartmentRemote;
import com.flexisaf.ses.entities.AcademicDepartmentEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.servicelocator.PersistenceServiceLocator;
import com.flexisaf.ses.user.handlers.UserAccountHandler;
import com.flexisaf.ses.exceptions.DepartmentAlreadyExistsException;
import com.flexisaf.ses.exceptions.UnknownUserException;
import com.flexisaf.ses.exceptions.UserAccountNotSpecifiedException;
import java.io.Serializable;
import java.util.List;

/**
 * This class serves to decouple the department rest service 
 * endpoint from its corresponding data access service.
 *
 * @author ushie
 */
public class AcademicDepartmentHandler implements Serializable {

    public AcademicDepartmentHandler() {
        
    }
    
    /**
     * Creates a department.
     * 
     * @param department
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws DepartmentAlreadyExistsException
     * @throws UserAccountNotSpecifiedException 
     */
    public long createAcademicDepartment(
            AcademicDepartmentEntity department,
            UserAccountEntity userAccount) 
            throws UnknownUserException,
            DepartmentAlreadyExistsException,
            UserAccountNotSpecifiedException{
        UserAccountHandler.validateUserAccount(userAccount);
        
        AcademicDepartmentRemote academicDepartmentBean = PersistenceServiceLocator.locateAcademicDepartmentPersistenceManager();

        AcademicDepartmentEntity existingDept = findDepartmentByName(
            department.getDepartmentName(), userAccount);
        
        if(existingDept != null) {
            throw new DepartmentAlreadyExistsException("The Specified Department Already Exists");
        }
        
        if (department.getDepartmentID() == 0) {
            return academicDepartmentBean.createEntity(department);
        } else {
            academicDepartmentBean.updateEntity(department);
            return department.getDepartmentID();
        }
    }
    
    /**
     * Finds a department by ID
     * 
     * @param departmentID
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public AcademicDepartmentEntity findDepartmentByID(
            long departmentID, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        AcademicDepartmentRemote academicDepartmentBean = PersistenceServiceLocator.locateAcademicDepartmentPersistenceManager();

        return academicDepartmentBean.findAcademicDepartmentByID(departmentID);
    }
    
    /**
     * Loads all departments matching the specified name.
     * 
     * @param departmentName
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public AcademicDepartmentEntity findDepartmentByName(
            String departmentName, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        AcademicDepartmentRemote academicDepartmentBean = PersistenceServiceLocator.locateAcademicDepartmentPersistenceManager();

        return academicDepartmentBean.findAcademicDepartmentByName(departmentName);
    }
    
    /**
     * Loads all departments.
     * 
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<AcademicDepartmentEntity> findAllDepartments(UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        AcademicDepartmentRemote academicDepartmentBean = PersistenceServiceLocator.locateAcademicDepartmentPersistenceManager();

        return academicDepartmentBean.findAllAcademicDepartments();
    }

}
