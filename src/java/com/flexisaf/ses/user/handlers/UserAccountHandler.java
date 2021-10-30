/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.user.handlers;

import com.flexisaf.ses.eao.UserAccountRemote;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.servicelocator.PersistenceServiceLocator;
import com.flexisaf.ses.exceptions.UnknownUserException;
import com.flexisaf.ses.exceptions.UserAccountNotSpecifiedException;
import com.flexisaf.ses.exceptions.UserAlreadyExistsException;
import java.io.Serializable;

/**
 * This class serves to decouple the user account rest service 
 * endpoint from its corresponding data access service.
 *
 * @author ushie
 */
public class UserAccountHandler implements Serializable {

    public UserAccountHandler() {

    }

    /**
     * Creates User account.
     * 
     * @param userAccount
     * @return
     * @throws UserAlreadyExistsException 
     */
    public long createUserAccount(
            UserAccountEntity userAccount) throws UserAlreadyExistsException {
        UserAccountRemote userAccountBean = PersistenceServiceLocator.locateUserAccountPersistenceManager();

        UserAccountEntity existingUser = userAccountBean.
                findUserByUserName(userAccount.getUserName());
        if(existingUser != null) {
            throw new UserAlreadyExistsException("The Specified User Already Exists");
        }
        
        if (userAccount.getUserAccountID() == 0) {
            return userAccountBean.createEntity(userAccount);
        } else {
            userAccountBean.updateEntity(userAccount);
            return userAccount.getUserAccountID();
        }
    }

    /**
     * Performs user login.
     * 
     * @param userName
     * @param password
     * @return 
     */
    public UserAccountEntity loginUser(
            String userName, String password) {
        UserAccountRemote userAccountBean = PersistenceServiceLocator.locateUserAccountPersistenceManager();

        return userAccountBean.findUserByUserNameAndPassword(userName, password);
    }

    /**
     * Validates user account for every client request.
     * 
     * @param userAccount
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public static void validateUserAccount(UserAccountEntity userAccount) throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler userAccountHandler = new UserAccountHandler();
        
        if(userAccount == null) {
            throw new UserAccountNotSpecifiedException("User Account Not Specified");
        }

        UserAccountEntity recordEntity = userAccountHandler.
                loginUser(userAccount.getUserName(), userAccount.getPassword());

        if (recordEntity == null) {
            throw new UnknownUserException(
                    "Login failed. The specified username and "
                    + "password don't match any user on the system.");
        }
    }

}
