/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flexisaf.ses.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class encapsulates the creation of a null safe Gson object.
 *
 * @author ushie
 */
public class CommonAdapterUtil implements Serializable {

    public static final String DATE_FORMAT_COMPLETE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    //
    public static final String DATE_FORMAT_STANDARD = "dd/MM/yyyy HH:mm:ss";
    //
    public static final String DATE_FORMAT_MOBILE_STANDARD = "yyyy-MM-dd HH:mm:ss.SSS";
    //
    public static final String DATE_FORMAT_SIMPLE = "dd/MM/yyyy";

    public CommonAdapterUtil() {

    }

    /**
     * return a null safe Gson object along with a date format for parsing date fields.
     * 
     * @param dateFormat
     * @return 
     */
    public static Gson getNullSafeGson(String dateFormat) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            DateFormat df = new SimpleDateFormat(dateFormat);

            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
                try {
                    return df.parse(json.getAsString());
                } catch (ParseException e) {
                    return null;
                }
            }
        });

        Gson gson = gsonBuilder.setDateFormat(dateFormat)
                .excludeFieldsWithModifiers(Modifier.STATIC).create();

        TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
        TypeAdapter<Date> safeDateTypeAdapter = dateTypeAdapter.nullSafe();

        return new GsonBuilder()
                .registerTypeAdapter(Date.class, safeDateTypeAdapter)
                .create();
    }

}
