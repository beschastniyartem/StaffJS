package com.artem.trainee.utils;

import net.sf.oval.context.OValContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created on 07.07.14.
 */
public class ParamUtils {

    //Double
    public static Double getDouble(HttpServletRequest request, String param) {
        return getDouble(request, param, null);
    }
    public static Double getDouble(HttpServletRequest request, String param, Double defaultValue) {
        try {
            return Double.valueOf(request.getParameter(param));
        } catch (Exception e) {
        }

        return defaultValue;
    }
    //Integer
    public static Integer getInteger(HttpServletRequest request, String param) {
        return getInteger(request, param, null);
    }
    public static Integer getInteger(HttpServletRequest request, String param, Integer  defaultValue) {
        try {
            return Integer .valueOf(request.getParameter(param));
        } catch (Exception e) {
        }

        return defaultValue;
    }
    //Date
    public static Date getDate(HttpServletRequest request, String param) {
        return getDate(request, param, null);
    }
    public static Date getDate(HttpServletRequest request, String param, Date  defaultValue) {
        try {
            return Date.valueOf(request.getParameter(param));
        } catch (Exception e) {
        }

        return defaultValue;
    }
    public static boolean IsValid( OValContext context, String param){
        return (String.valueOf(context)).endsWith(param);
    }
    public static boolean IsValid( String context, String param){
        return (String.valueOf(context)).endsWith(param);
    }
}
