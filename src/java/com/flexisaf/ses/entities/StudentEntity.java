/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ushie
 */
@Entity
@Table(name = "STUDENT")
public class StudentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long studentID;
    //
    private String lastName;
    private String firstName;
    private String otherName;
    //
    @Enumerated(EnumType.ORDINAL)
    private GenderType genderType;
    //
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    //
    private String matricNumber;
    private String emailAddress;
    //
    private long creatorUserID;
    //
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;
    //
    private long departmentID;

    public StudentEntity() {
        studentID = 0;

        lastName = "";
        firstName = "";
        otherName = "";

        genderType = GenderType.NONE;
        dateOfBirth = null;

        matricNumber = "";
        emailAddress = "";

        creatorUserID = 0;
        createDate = null;

        departmentID = 0;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqls = false;

        if (o instanceof StudentEntity) {
            StudentEntity entity = (StudentEntity) o;

            if ((entity.studentID > 0) && (this.studentID > 0)) {
                isEqls = entity.studentID == this.studentID;
            } else {
                isEqls = entity.matricNumber.equalsIgnoreCase(this.matricNumber);
            }
        }

        return isEqls;
    }

    @Override
    public int hashCode() {
        return (int) this.studentID;
    }

    @Override
    public String toString() {
        return "{studentID: " + studentID
                + ", lastName: " + lastName
                + ", firstName: " + firstName
                + ", otherName: " + otherName
                + ", genderType: " + genderType
                + ", dateOfBirth: " + dateOfBirth
                + ", matricNumber: " + matricNumber
                + ", creatorUserUD: " + creatorUserID
                + ", createDate: " + createDate
                + ", departmentID: " + departmentID + "}\n";
    }

    /**
     * @return the studentID
     */
    public long getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the otherName
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * @param otherName the otherName to set
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * @return the genderType
     */
    public GenderType getGenderType() {
        return genderType;
    }

    /**
     * @param genderType the genderType to set
     */
    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }
    
    public void setGenderType(int genderTypeOrd) {
        this.genderType = GenderType.getEnum(genderTypeOrd);
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the departmentID
     */
    public long getDepartmentID() {
        return departmentID;
    }

    /**
     * @param departmentID the departmentID to set
     */
    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * @return the matricNumber
     */
    public String getMatricNumber() {
        return matricNumber;
    }

    /**
     * @param matricNumber the matricNumber to set
     */
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the creatorUserUD
     */
    public long getCreatorUserID() {
        return creatorUserID;
    }

    /**
     * @param creatorUserID the creatorUserUD to set
     */
    public void setCreatorUserID(long creatorUserID) {
        this.creatorUserID = creatorUserID;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
