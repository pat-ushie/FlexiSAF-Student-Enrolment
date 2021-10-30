/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.student.handlers;

import com.flexisaf.ses.entities.StudentEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.servicelocator.PersistenceServiceLocator;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import com.flexisaf.ses.eao.StudentEnrollmentRemote;
import com.flexisaf.ses.entities.GenderType;
import com.flexisaf.ses.user.handlers.UserAccountHandler;
import com.flexisaf.ses.exceptions.AgeOutOfBoundsException;
import com.flexisaf.ses.utils.Constants;
import com.flexisaf.ses.exceptions.DateFormatException;
import com.flexisaf.ses.exceptions.EmailAddressInvalidException;
import com.flexisaf.ses.utils.DateUtil;
import com.flexisaf.ses.exceptions.InvalidGenderTypeException;
import com.flexisaf.ses.exceptions.MissingDepartmentException;
import com.flexisaf.ses.exceptions.UnknownUserException;
import com.flexisaf.ses.exceptions.UnspecifiedDateException;
import com.flexisaf.ses.exceptions.UserAccountNotSpecifiedException;
import com.flexisaf.ses.utils.EmailValidator;
import java.util.Date;

/**
 * This class serves to decouple the student rest service 
 * endpoint from its corresponding data access service.
 *
 * @author ushie
 */
public class StudentEnrollmentHandler implements Serializable {

    public StudentEnrollmentHandler() {

    }

    /**
     * Creates a student.
     * 
     * @param student
     * @param userAccount
     * @return
     * @throws DateFormatException
     * @throws AgeOutOfBoundsException
     * @throws UnspecifiedDateException
     * @throws UnknownUserException
     * @throws InvalidGenderTypeException
     * @throws MissingDepartmentException
     * @throws UserAccountNotSpecifiedException 
     */
    public long createStudent(
            StudentEntity student,
            UserAccountEntity userAccount)
            throws
            DateFormatException,
            AgeOutOfBoundsException,
            UnspecifiedDateException,
            UnknownUserException,
            InvalidGenderTypeException,
            MissingDepartmentException,
            UserAccountNotSpecifiedException,
            EmailAddressInvalidException {
        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        EmailValidator emailValidator = new EmailValidator();
        
        UserAccountHandler.validateUserAccount(userAccount);

        if (student.getGenderType() == GenderType.NONE) {
            throw new InvalidGenderTypeException("The Specified Gender Type Is Invalid");
        }

        if (student.getDepartmentID() == 0) {
            throw new MissingDepartmentException("Department Not Specified For This Student.");
        }
        
        if(!emailValidator.validate(student.getEmailAddress())) {
            throw new EmailAddressInvalidException("Invalid Email: The Email Format Is Wrong");
        }

        if (student.getStudentID() == 0) {
            int studentAge = DateUtil.calculateAge(student.getDateOfBirth());

            if (studentAge < Constants.MINIMUM_STUDENT_AGE) {
                throw new AgeOutOfBoundsException("This student is too young to enrol.");
            }

            if (studentAge > Constants.MAXIMUM_STUDENT_AGE) {
                throw new AgeOutOfBoundsException("This student is too old to enrol.");
            }

            student.setCreateDate(new Date());
            student.setCreatorUserID(userAccount.getUserAccountID());

            long studentID = studentEnrollmentBean.createEntity(student);
            
            student.setStudentID(studentID);

            student.setMatricNumber(
                    StudentNumberGenerator.generateMatricNumber(studentID));

            studentEnrollmentBean.updateEntity(student);

            return studentID;
        } else {
            studentEnrollmentBean.updateEntity(student);
            return student.getStudentID();
        }
    }

    /**
     * Loads all students matching the specified <code>firstName</code>.
     * 
     * @param firstName
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentListByFirstName(
            String firstName, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentListByFirstName(firstName);
    }

    /**
     * Loads all students matching the specified <code>lastName</code>.
     * 
     * @param lastName
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentListByLastName(
            String lastName, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentListByLastName(lastName);
    }

    /**
     * Loads all students matching the specified <code>otherName</code>.
     * 
     * @param otherName
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentListByOtherName(
            String otherName, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentListByOtherName(otherName);
    }

    /**
     * Loads all students matching the specified full name (last, first, and other name combination).
     * 
     * @param lastName
     * @param firstName
     * @param otherName
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentListByFullName(
            String lastName, String firstName, String otherName, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentsByFullName(firstName, lastName, otherName);
    }

    /**
     * Loads all students matching the specified <code>genderType</code>.
     * 
     * @param genderType
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws InvalidGenderTypeException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentListByGenderType(
            GenderType genderType, UserAccountEntity userAccount)
            throws UnknownUserException, InvalidGenderTypeException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        if (genderType == GenderType.NONE) {
            throw new InvalidGenderTypeException("The Specified Gender Type Is Invalid");
        }

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentListByGenderType(genderType);
    }

    /**
     * Loads all students matching the specified <code>departmentID</code>.
     * 
     * @param departmentID
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentListByDeptID(
            long departmentID, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentListByDeptID(departmentID);
    }

    /**
     * Loads all students created by the user with ID <code>creatorUserID</code>.
     * 
     * @param creatorUserID
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentListByCreator(
            long creatorUserID, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentListByCreator(creatorUserID);
    }

    /**
     * Loads all students created in the date range <code>startDate</code> and <code>endDate</code>.
     * 
     * @param startDate
     * @param endDate
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public List<StudentEntity> findStudentsByCreateDateInterval(
            Date startDate, Date endDate, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        return studentEnrollmentBean.findStudentsByCreateDateInterval(startDate, endDate);
    }

    /**
     * Delete the specified student.
     * 
     * @param student
     * @param userAccount
     * @return
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public long deleteStudent(
            StudentEntity student, UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        studentEnrollmentBean.deleteEntity(student);

        return student.getStudentID();
    }

}
