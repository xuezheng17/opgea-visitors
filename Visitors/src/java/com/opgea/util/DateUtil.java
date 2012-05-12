/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ramesh
 */
public class DateUtil {
    
    public static String getHHmmss(Long timeMilliSeconds){
        Date date = new Date(timeMilliSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return sdf.format(date);
    }
}
