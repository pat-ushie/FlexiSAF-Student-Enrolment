/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.eao;

import com.flexisaf.ses.entities.AcademicDepartmentEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ushie
 */
@Stateless
public class AcademicDepartmentBean implements AcademicDepartmentRemote {
    
    private static final String DEFAULT_PU = "StudentEnrollmentPU";
    
    public long createEntity(AcademicDepartmentEntity department) {
        entityManager.persist(department);
        entityManager.flush();
        
        return department.getDepartmentID();
    }
    
    public void updateEntity(AcademicDepartmentEntity department) {
        entityManager.merge(department);
    }
    
    public void deleteEntity(AcademicDepartmentEntity department) {
        entityManager.remove(department);
    }

    @PersistenceContext(unitName = DEFAULT_PU)
    private EntityManager entityManager;
    
    public List<AcademicDepartmentEntity> findAllAcademicDepartments() {
        List<AcademicDepartmentEntity> departmentList = new ArrayList<AcademicDepartmentEntity>();

        String queryString = "SELECT a FROM AcademicDepartmentEntity a";

        Query query = entityManager.createQuery(queryString);
        
        Iterator resultIt = query.getResultList().iterator();

        while (resultIt.hasNext()) {
            AcademicDepartmentEntity department = (AcademicDepartmentEntity) resultIt.next();
            departmentList.add(department);
        }

        return departmentList;
    }
    
    public AcademicDepartmentEntity findAcademicDepartmentByID(long departmentID) {
        AcademicDepartmentEntity student = null;

        String queryString = "SELECT a FROM AcademicDepartmentEntity a "
                + "WHERE a.departmentID = :departmentID";

        Query query = entityManager.createQuery(queryString);
        
        query.setParameter("departmentID", departmentID);
        
        Iterator resultIt = query.getResultList().iterator();

        if (resultIt.hasNext()) {
            student = (AcademicDepartmentEntity) resultIt.next();
        }

        return student;
    }
    
    public AcademicDepartmentEntity findAcademicDepartmentByName(String departmentName) {
        AcademicDepartmentEntity department = null;

        String queryString = "SELECT a FROM AcademicDepartmentEntity a "
                + "WHERE UPPER(a.departmentName) = :departmentName";

        Query query = entityManager.createQuery(queryString);
        
        query.setParameter("departmentName", departmentName.toUpperCase());
        
        Iterator resultIt = query.getResultList().iterator();

        if (resultIt.hasNext()) {
            department = (AcademicDepartmentEntity) resultIt.next();
        }

        return department;
    }

}
