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
import java.io.Serializable;
import java.net.URLEncoder;
import static junit.framework.TestCase.assertEquals;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test code for department creation.
 *
 * @author ushie
 */
public class DepartmentCreateTest implements Serializable {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DepartmentCreateTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("++++++++++++++++++++++++++ [DepartmentCreateTest] Successful: " + result.wasSuccessful());
    }

    @Test
    public void testAcademicDepartmentCreation() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        AcademicDepartmentEntity department = new AcademicDepartmentEntity();
        department.setDepartmentName("Mechanical Engineering");

        try {
            HttpUriRequest request = new HttpPost(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/AcademicDepartmentService/saveAcademicDepartment?recordJson="
                    + URLEncoder.encode(gson.toJson(department), "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

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
