/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.eao;

import com.flexisaf.ses.entities.GenderType;
import com.flexisaf.ses.entities.StudentEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ushie
 */
@Remote
public interface StudentEnrollmentRemote {

    /**
     * Persists the student entity to the database.
     * 
     * @param student
     * @return 
     */
    long createEntity(StudentEntity student);
    
    /**
     * Updates the database with this student entity
     * 
     * @param student 
     */
    void updateEntity(StudentEntity student);
    
    /**
     * Deletes the student entity from the database.
     * 
     * @param student 
     */
    void deleteEntity(StudentEntity student);
    
    /**
     * Retrieves the student entity matching the specified ID.
     * 
     * @param studentID
     * @return 
     */
    StudentEntity findStudentByID(long studentID);
    
    /**
     * Retrieves student entities that were created between <code>startDate</code> and <code>endDate</code>.
     * 
     * @param startDate
     * @param endDate
     * @return 
     */
    List<StudentEntity> findStudentsByCreateDateInterval(Date startDate, Date endDate);
    
    /**
     * Retrieves student entities whose dates of birth match <code>birthDate</code>.
     * 
     * @param birthDate
     * @return 
     */
    List<StudentEntity> findStudentsByBirthDate(Date birthDate);
    
    /**
     * Retrieves student entities whose First Name, Last Name, and Other Name fields match 
     * <code>firstName</code>, <code>lastName</code>, and <code>otherName</code> respectively.
     * 
     * @param firstName
     * @param lastName
     * @param otherName
     * @return 
     */
    List<StudentEntity> findStudentsByFullName(
            String firstName, String lastName, String otherName);
    
    /**
     * Retrieves student entities whose First Name field matches <code>firstName</code>.
     * 
     * @param firstName
     * @return 
     */
    List<StudentEntity> findStudentListByFirstName(String firstName);
    
    /**
     * Retrieves student entities whose Last Name field matches <code>lastName</code>.
     * 
     * @param lastName
     * @return 
     */
    List<StudentEntity> findStudentListByLastName(String lastName);
    
    /**
     * Retrieves student entities whose Other Name field matches <code>otherName</code>.
     * 
     * @param otherName
     * @return 
     */
    List<StudentEntity> findStudentListByOtherName(String otherName);
    
    /**
     * Retrieves student entities whose Gender type matches <code>genderType</code>.
     * 
     * @param genderType
     * @return 
     */
    List<StudentEntity> findStudentListByGenderType(GenderType genderType);
    
    /**
     * Retrieves student entities that were created by the user with ID <code>creatorUserID</code>.
     * 
     * @param creatorUserID
     * @return 
     */
    List<StudentEntity> findStudentListByCreator(long creatorUserID);
    
    /**
     * Retrieves student entities belonging to the same department with ID <code>departmentID</code>.
     * 
     * @param departmentID
     * @return 
     */
    List<StudentEntity> findStudentListByDeptID(long departmentID);

}
