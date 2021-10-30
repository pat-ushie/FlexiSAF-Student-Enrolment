/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.services;

import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.entities.UserAccountEntity;
import com.flexisaf.ses.user.handlers.UserAccountHandler;
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
 * operations on the  <code>UserAccount</code> entity.
 *
 * @author ushie
 */
@Path("UserAccountService")
public class UserAccountService implements Serializable {

    @Context
    private UriInfo context;
    
    /**
     * This service creates a user account.
     * 
     * @param recordJson
     * @param userAcctJson
     * @return 
     */
    @POST
    @Path("/saveUserAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUserAccount(
            @QueryParam("recordJson") String recordJson, 
            @QueryParam("userAcctJson") String userAcctJson) {
        String errorMessage = "";
        
        try {
            Gson gson = CommonAdapterUtil
                    .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

            UserAccountEntity userAccount = gson.fromJson(userAcctJson, UserAccountEntity.class);

            UserAccountEntity recordDto = gson.fromJson(recordJson, UserAccountEntity.class);

            UserAccountHandler businessHandler = new UserAccountHandler();

            long recordID = businessHandler.createUserAccount(recordDto);

            return Response.ok().entity(String.valueOf(recordID)).build();
        } catch (Exception ex) {
            Logger.getLogger(UserAccountService.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getLocalizedMessage();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(errorMessage).build();
    }

    /**
     * This service performs user login using <code>username</code> and <code>password</code>.
     * 
     * @param userName
     * @param password
     * @return 
     */
    @GET
    @Path("/loginUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(
            @QueryParam("userName") String userName,
            @QueryParam("password") String password) {
        String errorMessage = "";

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        Gson gson = CommonAdapterUtil
                .getNullSafeGson(CommonAdapterUtil.DATE_FORMAT_MOBILE_STANDARD);

        try {
            UserAccountHandler businessHandler = new UserAccountHandler();
            
            UserAccountEntity recordEntity = businessHandler.
                    loginUser(userName, password);
            
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

}
