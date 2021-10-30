/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.student.handlers;

import com.flexisaf.ses.eao.StudentEnrollmentRemote;
import com.flexisaf.ses.entities.StudentEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.servicelocator.PersistenceServiceLocator;
import com.flexisaf.ses.user.handlers.UserAccountHandler;
import com.flexisaf.ses.utils.Constants;
import com.flexisaf.ses.utils.EmailGatewayConfig;
import com.flexisaf.ses.utils.EmailServiceUtil;
import com.flexisaf.ses.exceptions.UnknownUserException;
import com.flexisaf.ses.exceptions.UserAccountNotSpecifiedException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * This class handles Birthday messaging
 *
 * @author ushie
 */
public class StudentBirthdayMessenger implements Serializable {

    /**
     * Loads all students whose birthday is the same as the current day and sends them a birthday 
     * message as conveyed by <code>subject</code> and <code>messageText</code>
     * 
     * @param subject
     * @param messageText
     * @param emailGatewayConfig
     * @param userAccount
     * @throws UnknownUserException
     * @throws UserAccountNotSpecifiedException 
     */
    public void sendBirthdayMessage(
            String subject,  
            String messageText, 
            EmailGatewayConfig emailGatewayConfig,
            UserAccountEntity userAccount) 
            throws UnknownUserException, UserAccountNotSpecifiedException {
        UserAccountHandler.validateUserAccount(userAccount);

        StudentEnrollmentRemote studentEnrollmentBean = PersistenceServiceLocator.locateStudentPersistenceManager();

        Date currentDate = new Date();
        
        List<StudentEntity> studentList = studentEnrollmentBean.
                findStudentsByBirthDate(currentDate);
        
        EmailServiceUtil emailSenderUtil = new EmailServiceUtil();
        
        for(StudentEntity student : studentList) {
            //Replace any <code><@student></code> parameters with the student's name.
            subject = subject.replaceAll(
                    Constants.EMAIL_STUDENT_PARAM, student.getFirstName());
            
            messageText = messageText.replaceAll(
                    Constants.EMAIL_STUDENT_PARAM, student.getFirstName());
            
            emailSenderUtil.sendEmail(
                    emailGatewayConfig, student.getEmailAddress(), subject, messageText);
        }
    }

}
