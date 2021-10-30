/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.eao;

import com.flexisaf.ses.entities.UserAccountEntity;
import java.io.Serializable;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ushie
 */
@Stateless
public class UserAccountBean implements UserAccountRemote {

    private static final String DEFAULT_PU = "StudentEnrollmentPU";

    @PersistenceContext(unitName = DEFAULT_PU)
    private EntityManager entityManager;
    
    public long createEntity(UserAccountEntity userAccount) {
        entityManager.persist(userAccount);
        entityManager.flush();
        
        return userAccount.getUserAccountID();
    }
    
    public void updateEntity(UserAccountEntity userAccount) {
        entityManager.merge(userAccount);
    }
    
    public void deleteEntity(UserAccountEntity userAccount) {
        entityManager.remove(userAccount);
    }
    
    public UserAccountEntity findUserAccountByID(long userAccountID) {
        UserAccountEntity userAccount = null;

        String queryString = "SELECT a FROM UserAccountEntity a "
                + "WHERE a.userAccountID = :userAccountID";

        Query query = entityManager.createQuery(queryString);
        
        query.setParameter("userAccountID", userAccountID);
        
        Iterator resultIt = query.getResultList().iterator();

        if (resultIt.hasNext()) {
            userAccount = (UserAccountEntity) resultIt.next();
        }

        return userAccount;
    }
    
    public UserAccountEntity findUserByUserNameAndPassword(String userName, String password) {
        UserAccountEntity userAccount = null;

        String queryString = "SELECT a FROM UserAccountEntity a "
                + "WHERE a.userName = :userName AND a.password = :password";

        Query query = entityManager.createQuery(queryString);
        
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        
        Iterator resultIt = query.getResultList().iterator();

        if (resultIt.hasNext()) {
            userAccount = (UserAccountEntity) resultIt.next();
        }

        return userAccount;
    }
    
    public UserAccountEntity findUserByUserName(String userName) {
        UserAccountEntity userAccount = null;

        String queryString = "SELECT a FROM UserAccountEntity a "
                + "WHERE a.userName = :userName";

        Query query = entityManager.createQuery(queryString);
        
        query.setParameter("userName", userName);
        
        Iterator resultIt = query.getResultList().iterator();

        if (resultIt.hasNext()) {
            userAccount = (UserAccountEntity) resultIt.next();
        }

        return userAccount;
    }

}
