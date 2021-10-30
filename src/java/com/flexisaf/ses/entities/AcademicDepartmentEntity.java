/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ushie
 */
@Entity
@Table(name = "ACADA_DEPTMT")
public class AcademicDepartmentEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long departmentID;
    //
    private String departmentName;

    public AcademicDepartmentEntity() {
        departmentID = 0;
        departmentName = "";
    }
    
    @Override
    public boolean equals(Object o) {
        boolean isEqls = false;

        if (o instanceof AcademicDepartmentEntity) {
            AcademicDepartmentEntity entity = (AcademicDepartmentEntity) o;

            if ((entity.departmentID > 0) && (this.departmentID > 0)) {
                isEqls = entity.departmentID == this.departmentID;
            } else {
                isEqls = entity.departmentName.equalsIgnoreCase(this.departmentName);
            }
        }

        return isEqls;
    }

    @Override
    public int hashCode() {
        return (int) this.departmentID;
    }
    
    @Override
    public String toString() {
        return "{departmentID: " + departmentID
                + ", departmentName: " + departmentName + "}\n";
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
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
