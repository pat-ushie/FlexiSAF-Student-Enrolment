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
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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
 * Test code for all find-by criteria.
 *
 * @author ushie
 */
public class StudentFindByCriteriaTest implements Serializable {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StudentFindByCriteriaTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("++++++++++++++++++++++++++ [UserAccountCreateTest] Successful: " + result.wasSuccessful());
    }

    @Test
    public void testStudentRetrievalByFirstName() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentListByFirstName?firstName="
                    + URLEncoder.encode("Eclott", "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-first ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testStudentRetrievalByLastName() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentListByLastName?lastName="
                    + URLEncoder.encode("Aketume", "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-last ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testStudentRetrievalByOtherName() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentListByOtherName?otherName="
                    + URLEncoder.encode("Tito", "UTF-8") + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-other ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testStudentRetrievalByFullName() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentListByFullName?"
                    + "lastName=" + URLEncoder.encode("Kobiat", "UTF-8")
                    + "&firstName=" + URLEncoder.encode("Eclott", "UTF-8")
                    + "&otherName=" + URLEncoder.encode("Amana", "UTF-8")
                    + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-all ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testStudentRetrievalByGenderType() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentListByGenderType?"
                    + "genderType=" + URLEncoder.encode(GenderType.FEMALE.getValue(), "UTF-8")
                    + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-gender ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testStudentRetrievalByDateInterval() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 2021);

        Date startDate = calendar.getTime();

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 2021);

        Date endDate = calendar.getTime();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");

        try {
            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentsByCreateDateInterval?"
                    + "startDate=" + URLEncoder.encode(dateFormatter.format(startDate), "UTF-8")
                    + "&endDate=" + URLEncoder.encode(dateFormatter.format(endDate), "UTF-8")
                    + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-date-interval ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testStudentRetrievalByCreatorUserID() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            long creatorUserID = 1;

            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentListByCreatorID?creatorUserID="
                    + URLEncoder.encode(String.valueOf(creatorUserID), "UTF-8")
                    + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-creator-id ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testStudentRetrievalByDepartmentID() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            long departmentID = 51;

            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/findStudentListByDepartmentID?departmentID="
                    + URLEncoder.encode(String.valueOf(departmentID), "UTF-8")
                    + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            Type listType = new TypeToken<List<StudentEntity>>() {
            }.getType();

            List<StudentEntity> studentList = gson.fromJson(jsonFromResponse, listType);
            System.out.println("++++++++++++++++++++++++ find-department-id ==>> " + studentList);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(studentList.size() > 0, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testStudentDeleteByID() {
        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUserName("john.doe");
        userAccount.setPassword("Password1234");
        userAccount.setUserAccountID(1);

        try {
            StudentEntity student = new StudentEntity();
            student.setStudentID(14);

            HttpUriRequest request = new HttpGet(
                    "http://localhost:8080/StudentEnrolmentSystem/webresources/StudentEnrolmentService/deleteStudentRecord?recordJson="
                    + URLEncoder.encode(gson.toJson(student), "UTF-8")
                    + "&userAcctJson=" + URLEncoder.encode(gson.toJson(userAccount), "UTF-8"));

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

            String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());

            System.out.println("++++++++++++++++++++++++ delete-student ==>> " + jsonFromResponse);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                assertEquals(Long.parseLong(jsonFromResponse) > 0, true);
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
