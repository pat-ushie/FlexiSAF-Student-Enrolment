/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.services;

import com.flexisaf.ses.entities.GenderType;
import com.flexisaf.ses.entities.StudentEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.student.handlers.StudentBirthdayMessenger;
import com.flexisaf.ses.student.handlers.StudentEnrollmentHandler;
import com.flexisaf.ses.exceptions.AgeOutOfBoundsException;
import com.flexisaf.ses.utils.CommonAdapterUtil;
import com.flexisaf.ses.exceptions.DateFormatException;
import com.flexisaf.ses.utils.DateUtil;
import com.flexisaf.ses.utils.EmailGatewayConfig;
import com.flexisaf.ses.exceptions.UnknownUserException;
import com.flexisaf.ses.exceptions.UnspecifiedDateException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * This class provides an endpoint interface to perform CRUD 
 * operations on the <code>Student</code> entity.
 *
 * @author ushie
 */
@Path("StudentEnrolmentService")
public class StudentEnrolmentService implements Serializable {
    
    @Context
    private UriInfo context;

    /**
     * This service creates a student.
     * 
     * @param recordJson
     * @param userAcctJson
     * @return
     * @throws DateFormatException
     * @throws AgeOutOfBoundsException
     * @throws UnspecifiedDateException
     * @throws UnknownUserException 
     */
    @POST
    @Path("/saveStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveStudent(
            @QueryParam("recordJson") String recordJson, 
            @QueryParam("userAcctJson") String userAcctJson) 
            throws DateFormatException,
            AgeOutOfBoundsException,
            UnspecifiedDateException,
            UnknownUserException {
        String errorMessage = "";
        
        try {
            Gson gson = CommonAdapterUtil
                    .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

            UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

            StudentEntity recordDto = gson.fromJson(recordJson, StudentEntity.class);

            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();

            long recordID = businessHandler.createStudent(recordDto, userAccount);

            return Response.ok().entity(String.valueOf(recordID)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getLocalizedMessage();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }

    /**
     * This service retrieves all students whose first names match the <code>firstName</code> field.
     * 
     * @param firstName
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentListByFirstName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentListByFirstName(
            @QueryParam("firstName") String firstName,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentListByFirstName(firstName, userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service retrieves all students whose last names match the <code>lastName</code> field.
     * 
     * @param lastName
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentListByLastName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentListByLastName(
            @QueryParam("lastName") String lastName,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentListByLastName(lastName, userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service retrieves all students whose other name field match the <code>otherName</code> field.
     * 
     * @param otherName
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentListByOtherName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentListByOtherName(
            @QueryParam("otherName") String otherName,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentListByOtherName(otherName, userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service retrieves all students whose full names match the specified full 
     * (ie <code>firstName</code>, <code>lastName</code>, and <code>otherName</code>).
     * 
     * @param lastName
     * @param firstName
     * @param otherName
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentListByFullName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentListByFullName(
            @QueryParam("lastName") String lastName,
            @QueryParam("firstName") String firstName,
            @QueryParam("otherName") String otherName,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentListByFullName(lastName, firstName, otherName, userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service retrieves all students whose gender matches <code>genderType</code>.
     * 
     * @param genderType
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentListByGenderType")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentListByGenderType(
            @QueryParam("genderType") String genderType,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentListByGenderType(GenderType.getEnum(genderType), userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service retrieves all students created between the date range 
     * between <code>startDate</code> and <code>endDate</code>.
     * 
     * @param startDate
     * @param endDate
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentsByCreateDateInterval")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentsByCreateDateInterval(
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);
        
        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentsByCreateDateInterval(
                            DateUtil.toUtilDate(startDate), 
                            DateUtil.toUtilDate(endDate), userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service retrieves all students created by the same user whose user ID is <code>creatorUserID</code>.
     * 
     * @param creatorUserID
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentListByCreatorID")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentListByCreatorID(
            @QueryParam("creatorUserID") String creatorUserID,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentListByCreator(Long.parseLong(creatorUserID), userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service retrieves all students of the same department specified by ID <code>departmentID</code>.
     * 
     * @param departmentID
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findStudentListByDepartmentID")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findStudentListByDepartmentID(
            @QueryParam("departmentID") String departmentID,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            List<StudentEntity> recordEntityList = businessHandler.
                    findStudentListByDeptID(Long.parseLong(departmentID), userAccount);
            
            if (recordEntityList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntityList)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service deletes the student specified by <code>recordJson</code>..
     * 
     * @param recordJson
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/deleteStudentRecord")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStudentRecord(
            @QueryParam("recordJson") String recordJson,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

        StudentEntity recordDto = gson.fromJson(recordJson, StudentEntity.class);
        
        try {
            StudentEnrollmentHandler businessHandler = new StudentEnrollmentHandler();
            
            long recordID = businessHandler.deleteStudent(recordDto, userAccount);
            
            if (recordID == 0) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(String.valueOf(recordID)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service looks up all students whose birth days fall on the current 
     * date and dispatches a happy birth day message to each of them.
     * 
     * @param emailGatewayConfigJson
     * @param subject
     * @param messageText
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/sendHappyBirthEmailToAllCelebrants")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendHappyBirthEmailToAllCelebrants(
            @QueryParam("emailGatewayConfigJson") String emailGatewayConfigJson,
            @QueryParam("subject") String subject,
            @QueryParam("messageText") String messageText,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);
        
        EmailGatewayConfig emailGatewayConfig = gson.fromJson(emailGatewayConfigJson, EmailGatewayConfig.class);

        try {
            StudentBirthdayMessenger businessHandler = new StudentBirthdayMessenger();
            
            businessHandler.sendBirthdayMessage(
                    subject,   messageText, emailGatewayConfig, userAccount);
            
            return Response.ok(String.valueOf(1)).build();
        } catch (Exception ex) {
            Logger.getLogger(StudentEnrolmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }

}
