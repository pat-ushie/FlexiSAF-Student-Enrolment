/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.utils;

import java.io.Serializable;

/**
 *
 * @author ushie
 */
public class EmailGatewayConfig implements Serializable {
    
    private String senderEmailAddress;
    private String senderPassword;
    //
    private String emailSMTPserver;
    private String smtpServerPort;

    public EmailGatewayConfig() {
        senderEmailAddress = "";
        senderPassword = "";
        emailSMTPserver = "";
        smtpServerPort = "";
    }

    /**
     * @return the senderEmailAddress
     */
    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    /**
     * @param senderEmailAddress the senderEmailAddress to set
     */
    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }

    /**
     * @return the senderPassword
     */
    public String getSenderPassword() {
        return senderPassword;
    }

    /**
     * @param senderPassword the senderPassword to set
     */
    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    /**
     * @return the emailSMTPserver
     */
    public String getEmailSMTPserver() {
        return emailSMTPserver;
    }

    /**
     * @param emailSMTPserver the emailSMTPserver to set
     */
    public void setEmailSMTPserver(String emailSMTPserver) {
        this.emailSMTPserver = emailSMTPserver;
    }

    /**
     * @return the smtpServerPort
     */
    public String getSmtpServerPort() {
        return smtpServerPort;
    }

    /**
     * @param smtpServerPort the smtpServerPort to set
     */
    public void setSmtpServerPort(String smtpServerPort) {
        this.smtpServerPort = smtpServerPort;
    }

}
