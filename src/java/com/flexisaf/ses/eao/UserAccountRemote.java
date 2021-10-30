/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.eao;

import com.flexisaf.ses.entities.UserAccountEntity;
import javax.ejb.Remote;

/**
 *
 * @author ushie
 */
@Remote
public interface UserAccountRemote {

    /**
     * Persists the user entity to the database.
     * 
     * @param userAccount
     * @return 
     */
    long createEntity(UserAccountEntity userAccount);
    
    /**
     * Updates the database with this user entity
     * 
     * @param userAccount 
     */
    void updateEntity(UserAccountEntity userAccount);
    
    /**
     * Deletes the user entity from the database.
     * 
     * @param userAccount 
     */
    void deleteEntity(UserAccountEntity userAccount);
    
    /**
     * Retrieves the user entity matching the specified ID.
     * 
     * @param userAccountID
     * @return 
     */
    UserAccountEntity findUserAccountByID(long userAccountID);
    
    /**
     * Retrieves the user entity matching the specified username and password.
     * 
     * @param userName
     * @param password
     * @return 
     */
    UserAccountEntity findUserByUserNameAndPassword(String userName, String password);
    
    /**
     * Retrieves the user entity matching the specified username.
     * 
     * @param userName
     * @return 
     */
    UserAccountEntity findUserByUserName(String userName);

}
