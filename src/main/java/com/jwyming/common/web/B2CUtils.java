package com.jwyming.common.web;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class B2CUtils {
    public static Logger logger = Logger.getLogger(B2CUtils.class.getName());

    public synchronized static String getPropertyFromFile(String key) {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("system.system", locale);
        return rb.getString(key).trim();
    }

    public synchronized static String getPropertyFromFile(String filename, String key) {
        ResourceBundle rb = ResourceBundle.getBundle(filename);
        return rb.getString(key).trim();
    }
}
