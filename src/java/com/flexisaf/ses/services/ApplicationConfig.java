/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ushie
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    public ApplicationConfig() {
        
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.flexisaf.ses.services.AcademicDepartmentService.class);
        resources.add(com.flexisaf.ses.services.StudentEnrolmentService.class);
        resources.add(com.flexisaf.ses.services.UserAccountService.class);
    }

}
