/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.eao;

import com.flexisaf.ses.entities.AcademicDepartmentEntity;
import com.flexisaf.ses.entities.GenderType;
import com.flexisaf.ses.entities.StudentEntity;
import com.flexisaf.ses.utils.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ushie
 */
@Stateless
public class StudentEnrollmentBean implements StudentEnrollmentRemote {

    private static final String DEFAULT_PU = "StudentEnrollmentPU";

    @PersistenceContext(unitName = DEFAULT_PU)
    private EntityManager entityManager;
    
    public long createEntity(StudentEntity student) {
        entityManager.persist(student);
        entityManager.flush();
        
        return student.getStudentID();
    }
    
    public void updateEntity(StudentEntity student) {
        entityManager.merge(student);
    }
    
    public void deleteEntity(StudentEntity student) {
        String queryString = "DELETE FROM StudentEntity a WHERE a.studentID = :studentID";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("studentID", student.getStudentID());
        
        query.executeUpdate();
    }

    public StudentEntity findStudentByID(long studentID) {
        StudentEntity student = null;

        String queryString = "SELECT a FROM StudentEntity a WHERE a.studentID = :studentID";

        Query query = entityManager.createQuery(queryString);
        
        query.setParameter("studentID", studentID);
        
        Iterator resultIt = query.getResultList().iterator();

        if (resultIt.hasNext()) {
            student = (StudentEntity) resultIt.next();
        }

        return student;
    }

    public List<StudentEntity> findStudentsByCreateDateInterval(Date startDate, Date endDate) {
        List<StudentEntity> studentList = new ArrayList<StudentEntity>();

        String queryString = "select a from StudentEntity a "
                + "where a.createDate BETWEEN :startDate AND :endDate";

        Query query = entityManager.createQuery(queryString);

        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        Iterator resultIt = query.getResultList().iterator();

        while (resultIt.hasNext()) {
            StudentEntity student = (StudentEntity) resultIt.next();
            studentList.add(student);
        }

        return studentList;
    }
    
    public List<StudentEntity> findStudentsByBirthDate(Date birthDate) {
        List<StudentEntity> studentList = new ArrayList<StudentEntity>();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);

        String queryString = "SELECT a FROM StudentEntity a "
                + "WHERE  FUNC('MONTH', a.dateOfBirth) = :birthMonth"
                + " AND FUNC('DAY', a.dateOfBirth) = :birthDay";

        Query query = entityManager.createQuery(queryString);

        query.setParameter("birthMonth", calendar.get(Calendar.MONTH) + 1);
        query.setParameter("birthDay", calendar.get(Calendar.DAY_OF_MONTH));

        Iterator resultIt = query.getResultList().iterator();

        while (resultIt.hasNext()) {
            StudentEntity student = (StudentEntity) resultIt.next();
            studentList.add(student);
        }

        return studentList;
    }

    public List<StudentEntity> findStudentsByFullName(
            String firstName, String lastName, String otherName) {
        List<StudentEntity> studentList = new ArrayList<StudentEntity>();

        String queryString = "SELECT a FROM StudentEntity a "
                + "WHERE UPPER(a.firstName) = :firstName "
                + "AND UPPER(a.lastName) = :lastName "
                + "AND UPPER(a.otherName) = :otherName";

        Query query = entityManager.createQuery(queryString);
        
        query.setParameter("firstName", firstName.toUpperCase());
        query.setParameter("lastName", lastName.toUpperCase());
        query.setParameter("otherName", otherName.toUpperCase());
        
        Iterator resultIt = query.getResultList().iterator();

        while (resultIt.hasNext()) {
            StudentEntity student = (StudentEntity) resultIt.next();
            studentList.add(student);
        }

        return studentList;
    }

    public List<StudentEntity> findStudentListByFirstName(String firstName) {
        return findStudentListBySingleCriteria("firstName", firstName.toUpperCase());
    }

    public List<StudentEntity> findStudentListByLastName(String lastName) {
        return findStudentListBySingleCriteria("lastName", lastName.toUpperCase());
    }

    public List<StudentEntity> findStudentListByOtherName(String otherName) {
        return findStudentListBySingleCriteria("otherName", otherName.toUpperCase());
    }

    public List<StudentEntity> findStudentListByGenderType(GenderType genderType) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentEntity> query = builder.createQuery(StudentEntity.class);

        Root<StudentEntity> personRoot = query.from(StudentEntity.class);
        query.where(builder.equal(personRoot.get("genderType"), genderType));

        List<StudentEntity> studentList = entityManager.createQuery(query).getResultList();

        return studentList;
    }

    public List<StudentEntity> findStudentListByCreator(long creatorUserID) {
        return findStudentListBySingleCriteria("creatorUserID", String.valueOf(creatorUserID));
    }

    public List<StudentEntity> findStudentListByDeptID(long departmentID) {
        return findStudentListBySingleCriteria("departmentID", String.valueOf(departmentID));
    }

    private List<StudentEntity> findStudentListBySingleCriteria(
            String criteriaName, String criteriaValue) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentEntity> query = builder.createQuery(StudentEntity.class);

        Root<StudentEntity> personRoot = query.from(StudentEntity.class);
        query.where(builder.equal(personRoot.get(criteriaName), criteriaValue.toUpperCase()));

        List<StudentEntity> studentList = entityManager.createQuery(query).getResultList();

        return studentList;
    }
}
