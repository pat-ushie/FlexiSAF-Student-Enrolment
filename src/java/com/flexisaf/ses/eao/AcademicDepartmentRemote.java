/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.eao;

import com.flexisaf.ses.entities.AcademicDepartmentEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ushie
 */
@Remote
public interface AcademicDepartmentRemote {
    
    /**
     * Creates a department entity in the database.
     * 
     * @param department
     * @return 
     */
    long createEntity(AcademicDepartmentEntity department);
    
    /**
     * Updates the database with this department entity,
     * 
     * @param department 
     */
    void updateEntity(AcademicDepartmentEntity department);
    
    /**
     * Deletes the department entity from the database.
     * 
     * @param department 
     */
    void deleteEntity(AcademicDepartmentEntity department);
    
    /**
     * Load all department entities.
     * 
     * @return 
     */
    public List<AcademicDepartmentEntity> findAllAcademicDepartments();

    /**
     * Find department by ID.
     * 
     * @param departmentID
     * @return 
     */
    AcademicDepartmentEntity findAcademicDepartmentByID(long departmentID);
    
    /**
     * Find department by name. The search is not case sensitive.
     * 
     * @param departmentName
     * @return 
     */
    AcademicDepartmentEntity findAcademicDepartmentByName(String departmentName);

}
