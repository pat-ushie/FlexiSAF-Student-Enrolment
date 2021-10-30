/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.tests;

import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.utils.CommonAdapterUtil;
import com.google.gson.Gson;
import java.io.Serializable;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static junit.framework.TestCase.assertEquals;

/**
 * Test code for user login
 *
 * @author ushie
 */
public class UserAccountLoginTest implements Serializable {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UserAccountLoginTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("++++++++++++++++++++++++++ [UserAccountLoginTest] Successful: " + result.wasSuccessful());
    }

    @Test
    public void testUserAccountLogin() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/UserAccountService/loginUser?userName="
                    + URLEncoder.encode(userAccount.getUserName(), "UTF-8") + "&password=" + URLEncoder.encode(userAccount.getPassword(), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            UserAccountEntity signedInUser = gson.fromJson(jsonFromResponse, UserAccountEntity.class);
            System.out.println("++++++++++++++++++++++++ signedInUser ==>> " + signedInUser);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(signedInUser.getUserName().trim().length() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

}
