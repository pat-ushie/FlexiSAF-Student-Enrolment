/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.entities;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author ushie
 */
public enum GenderType {

    @SerializedName("NONE")
    NONE("NONE"),
    //
    @SerializedName("MALE")
    MALE("MALE"),
    //
    @SerializedName("FEMALE")
    FEMALE("FEMALE");
    
    private String value;
    
    GenderType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue().toUpperCase();
    }

    public static GenderType getEnum(String value) {
        GenderType enType = GenderType.NONE;

        for (GenderType v : values()) {
            if (v.getValue().equalsIgnoreCase(value)) {
                enType = v;
                break;
            }
        }

        return enType;
    }

    public static GenderType getEnum(int value) {
        GenderType enType = GenderType.NONE;

        for (GenderType v : values()) {
            if (v.ordinal() == value) {
                enType = v;
                break;
            }
        }

        return enType;
    }

}
