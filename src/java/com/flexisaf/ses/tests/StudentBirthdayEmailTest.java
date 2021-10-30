/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.tests;

import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.utils.CommonAdapterUtil;
import com.flexisaf.ses.utils.Constants;
import com.flexisaf.ses.utils.EmailGatewayConfig;
import com.google.gson.Gson;
import java.io.Serializable;
import java.net.URLEncoder;
import static junit.framework.TestCase.assertEquals;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static junit.framework.TestCase.assertEquals;
import org.apache.http.client.methods.HttpGet;

/**
 * Test code for sending birthday email.
 *
 * @author ushie
 */
public class StudentBirthdayEmailTest implements Serializable {

    static final String senderEmailId = "whotocontact@gmail.com";
    //
    static final String senderPassword = "xx01x01x01xxx";
    //
    static final String emailSMTPserver = "smtp.gmail.com";
    //
    static final String smtpServerPort = "465";

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StudentBirthdayEmailTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("++++++++++++++++++++++++++ [StudentBirthdayEmailTest] Successful: " + result.wasSuccessful());
    }

    @Test
    public void testAcademicDepartmentCreation() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        EmailGatewayConfig emailGatewayConfig = new EmailGatewayConfig();
        emailGatewayConfig.setEmailSMTPserver(emailSMTPserver);
        emailGatewayConfig.setSenderEmailAddress(senderEmailId);
        emailGatewayConfig.setSenderPassword(senderPassword);
        emailGatewayConfig.setSmtpServerPort(smtpServerPort);

        String subject = "Happy Birthday " + Constants.EMAIL_STUDENT_PARAM + "!";
        String messageText = "Hello " + Constants.EMAIL_STUDENT_PARAM 
                + ", We at FlexiSAF want to say Happy Birthday to you. "
                + "Do have a blast!\n\nThanks.";

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/sendHappyBirthEmailToAllCelebrants?emailGatewayConfigJson="
                    + URLEncoder.encode(gson.toJson(emailGatewayConfig), "UTF-8")
                    + "&subject=" + URLEncoder.encode(subject, "UTF-8")
                    + "&messageText=" + URLEncoder.encode(messageText, "UTF-8")
                    + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("++++++++++++++++++++++++ jsonFromResponse ==>> " + jsonFromResponse);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(Long.parseLong(jsonFromResponse) > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
