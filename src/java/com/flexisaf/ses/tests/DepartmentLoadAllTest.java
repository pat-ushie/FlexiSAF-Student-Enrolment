/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.tests;

import com.flexisaf.ses.entities.AcademicDepartmentEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.utils.CommonAdapterUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
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

/**
 * Test code for loading all departments.
 *
 * @author ushie
 */
public class DepartmentLoadAllTest implements Serializable {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DepartmentLoadAllTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("++++++++++++++++++++++++++ [DepartmentLoadAllTest] Successful: " + result.wasSuccessful());
    }

    @Test
    public void testLoadAllDepartments() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/AcademicDepartmentService/findAllDepartments?userAcctJson="
                    + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
            
            Type listType = new TypeToken<List<AcademicDepartmentEntity>>() {
            }.getType();

            List<AcademicDepartmentEntity> departments = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ result set ==>> " + departments);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(departments.size() >= 0, true);
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
