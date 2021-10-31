/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.tests;

import com.flexisaf.ses.entities.GenderType;
import com.flexisaf.ses.entities.StudentEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.utils.CommonAdapterUtil;
import com.google.gson.Gson;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
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
 * Test code for student creation.
 *
 * @author ushie
 */
public class StudentCreateTest implements Serializable {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StudentCreateTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("++++++++++++++++++++++++++ [StudentCreateTest] Successful: " + result.wasSuccessful());
    }

    @Test
    public void testStudentCreation() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        StudentEntity student = new StudentEntity();
        student.setLastName("Eyoloze");
        student.setFirstName("Charles");
        student.setOtherName("Adams");
        student.setEmailAddress("charles.eyoloze@gmail.com");
        student.setCreatorUserID(1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.YEAR, 2001);
        
        student.setDateOfBirth(calendar.getTime());
        
        student.setGenderType(GenderType.MALE);
        student.setDepartmentID(51);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpPost(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/saveStudent?recordJson="
                    + URLEncoder.encode(gson.toJson(student), "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

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
    
    @Test
    public void testUnderAgedStudentCreation() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        StudentEntity student = new StudentEntity();
        student.setLastName("Adamu");
        student.setFirstName("Mathias");
        student.setOtherName("Osaze");
        student.setEmailAddress("mathias.adamu@gmail.com");
        student.setCreatorUserID(1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.YEAR, 2015);
        
        student.setDateOfBirth(calendar.getTime());
        
        student.setGenderType(GenderType.MALE);
        student.setDepartmentID(51);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpPost(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/saveStudent?recordJson="
                    + URLEncoder.encode(gson.toJson(student), "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("++++++++++++++++++++++++ jsonFromResponse ==>> " + jsonFromResponse);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(jsonFromResponse.equalsIgnoreCase("This student is too young to enrol."), true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testOverAgedStudentCreation() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        StudentEntity student = new StudentEntity();
        student.setLastName("Oladimeji");
        student.setFirstName("Olusolape");
        student.setOtherName("Olanrewaju");
        student.setEmailAddress("olusola.dimeji@gmail.com");
        student.setCreatorUserID(1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 1985);
        
        student.setDateOfBirth(calendar.getTime());
        
        student.setGenderType(GenderType.MALE);
        student.setDepartmentID(51);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpPost(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/saveStudent?recordJson="
                    + URLEncoder.encode(gson.toJson(student), "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("++++++++++++++++++++++++ jsonFromResponse ==>> " + jsonFromResponse);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(jsonFromResponse.equalsIgnoreCase("This student is too old to enrol."), true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testStudentCreationWithInvalidGender() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        StudentEntity student = new StudentEntity();
        student.setLastName("Oladimeji");
        student.setFirstName("Olusolape");
        student.setOtherName("Olanrewaju");
        student.setEmailAddress("olusola.dimeji@gmail.com");
        student.setCreatorUserID(1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 2001);
        
        student.setDateOfBirth(calendar.getTime());
        
        student.setGenderType(GenderType.NONE);
        student.setDepartmentID(51);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpPost(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/saveStudent?recordJson="
                    + URLEncoder.encode(gson.toJson(student), "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("++++++++++++++++++++++++ jsonFromResponse ==>> " + jsonFromResponse);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(jsonFromResponse.equalsIgnoreCase("The Specified Gender Type Is Invalid"), true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testStudentCreationWithoutDepartment() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        StudentEntity student = new StudentEntity();
        student.setLastName("Oladimeji");
        student.setFirstName("Olusolape");
        student.setOtherName("Olanrewaju");
        student.setEmailAddress("olusola.dimeji@gmail.com");
        student.setCreatorUserID(1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 2001);
        
        student.setDateOfBirth(calendar.getTime());
        
        student.setGenderType(GenderType.MALE);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpPost(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/saveStudent?recordJson="
                    + URLEncoder.encode(gson.toJson(student), "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("++++++++++++++++++++++++ jsonFromResponse ==>> " + jsonFromResponse);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(jsonFromResponse.equalsIgnoreCase("Department Not Specified For This Student."), true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testStudentCreationWithInvalidEmailAddress() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        StudentEntity student = new StudentEntity();
        student.setLastName("Oladimeji");
        student.setFirstName("Olusolape");
        student.setOtherName("Olanrewaju");
        student.setEmailAddress("olusola.dimeji.com");
        student.setCreatorUserID(1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 2001);
        
        student.setDateOfBirth(calendar.getTime());
        
        student.setGenderType(GenderType.MALE);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpPost(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/saveStudent?recordJson="
                    + URLEncoder.encode(gson.toJson(student), "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("++++++++++++++++++++++++ jsonFromResponse ==>> " + jsonFromResponse);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(jsonFromResponse.equalsIgnoreCase("Invalid Email: The Email Format Is Wrong"), true);
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
