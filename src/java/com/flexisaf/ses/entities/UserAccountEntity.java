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
@Table(name = "USER_ACCOUNT")
public class UserAccountEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long userAccountID;
    //
    private String userName;
    private String password;

    public UserAccountEntity() {
        userAccountID = 0;
        
        userName = "";
        password = "";
    }
    
    @Override
    public boolean equals(Object o) {
        boolean isEqls = false;

        if (o instanceof UserAccountEntity) {
            UserAccountEntity entity = (UserAccountEntity) o;

            if ((entity.userAccountID > 0) && (this.userAccountID > 0)) {
                isEqls = entity.userAccountID == this.userAccountID;
            } else {
                isEqls = entity.userName.equalsIgnoreCase(this.userName);
            }
        }

        return isEqls;
    }

    @Override
    public int hashCode() {
        return (int) this.userAccountID;
    }
    
    @Override
    public String toString() {
        return "{userAccountID: " + userAccountID
                + ", userName: " + userName + "}\n";
    }

    /**
     * @return the userAccountID
     */
    public long getUserAccountID() {
        return userAccountID;
    }

    /**
     * @param userAccountID the userAccountID to set
     */
    public void setUserAccountID(long userAccountID) {
        this.userAccountID = userAccountID;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
