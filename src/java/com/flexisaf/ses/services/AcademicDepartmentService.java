/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.services;

import com.flexisaf.ses.entities.AcademicDepartmentEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.student.handlers.AcademicDepartmentHandler;
import com.flexisaf.ses.utils.CommonAdapterUtil;
import com.google.gson.Gson;
import java.io.Serializable;
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
 * This class provides an endpoint interface for performing CRUD 
 * operations on the  <code>AcademicDepartment</code> entity.
 *
 * @author ushie
 */
@Path("AcademicDepartmentService")
public class AcademicDepartmentService implements Serializable {

    @Context
    private UriInfo context;
    
    /**
     * This service creates a department.
     * 
     * @param recordJson
     * @param userAcctJson
     * @return 
     */
    @POST
    @Path("/saveAcademicDepartment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAcademicDepartment(
            @QueryParam("recordJson") String recordJson, 
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";
        
        try {
            Gson gson = CommonAdapterUtil
                    .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

            UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

            AcademicDepartmentEntity recordDto = gson.fromJson(recordJson, AcademicDepartmentEntity.class);

            AcademicDepartmentHandler businessHandler = new AcademicDepartmentHandler();

            long recordID = businessHandler.createAcademicDepartment(recordDto, userAccount);

            return Response.ok().entity(String.valueOf(recordID)).build();
        } catch (Exception ex) {
            Logger.getLogger(AcademicDepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getLocalizedMessage();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service finds a department by its ID.
     * 
     * @param departmentID
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findDepartmentByID")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findDepartmentByID(
            @QueryParam("departmentID") String departmentID,
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        try {
            UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);
            
            AcademicDepartmentHandler businessHandler = new AcademicDepartmentHandler();
            
            AcademicDepartmentEntity recordEntity = businessHandler.
                    findDepartmentByID(Long.parseLong(departmentID), userAccount);
            
            if (recordEntity == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordEntity)).build();
        } catch (Exception ex) {
            Logger.getLogger(UserAccountService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }
    
    /**
     * This service loads all departments.
     * 
     * @param userAcctJson
     * @return 
     */
    @GET
    @Path("/findAllDepartments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findAllDepartments(
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        try {
            UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);
            
            AcademicDepartmentHandler businessHandler = new AcademicDepartmentHandler();
            
            List<AcademicDepartmentEntity> recordList = businessHandler.
                    findAllDepartments(userAccount);
            
            if (recordList == null) {
                status = Response.Status.NOT_FOUND;
            }

            return Response.ok(gson.toJson(recordList)).build();
        } catch (Exception ex) {
            Logger.getLogger(UserAccountService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
        }

        return Response.status(status).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }

}
