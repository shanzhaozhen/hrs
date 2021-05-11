package com.hbjs.hrscommon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CommonUtils {

    public static String URLFileNameConverter(String fileName) throws UnsupportedEncodingException {
        return URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
    }

}
