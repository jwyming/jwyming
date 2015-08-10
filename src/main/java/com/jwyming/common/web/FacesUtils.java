package com.jwyming.common.web;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.util.MessageUtil;

/**
 * Created by yiming on 2014/10/11.
 */
public class FacesUtils {
    private static final Log log = LogFactory.getLog(MessageUtil.class);
    private static final String DEFAULT_BUNDLE = "javax.faces.Messages";

    /**
     * Get servlet context.
     *
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
        return getServletContext(FacesContext.getCurrentInstance());
    }

    /**
     * Get servlet context.
     *
     * @param facesContext FacesContext
     * @return the servlet context
     */
    public static ServletContext getServletContext(FacesContext facesContext) {
        return (ServletContext) facesContext.getExternalContext().getContext();
    }

    /**
     * Get managed bean based on the bean name.
     *
     * @param beanName the bean name
     * @return the managed bean associated with the bean name
     */
    public static Object getManagedBean(String beanName) {
        return getManagedBean(FacesContext.getCurrentInstance(), beanName);
    }

    public static <T> T getObject(String beanName, Class<T> entityClass) {
//        String[] names = beanName.split(".");
//        if(names.length > 1) {
//            Object o = entityClass.cast(getManagedBean(beanName));
//        }
        return entityClass.cast(getManagedBean(beanName));
//        return (T) getManagedBean(beanName);
    }

    /**
     * Get managed bean based on the bean name.
     *
     * @param facesContext FacesContext
     * @param beanName     the bean name
     * @return the managed bean associated with the bean name
     */
    public static Object getManagedBean(FacesContext facesContext, String beanName) {
        Object o = getValueBinding(getJsfEl(beanName)).getValue(facesContext);

        return o;
    }

    /**
     * Remove the managed bean based on the bean name.
     *
     * @param beanName the bean name of the managed bean to be removed
     */
    public static void resetManagedBean(String beanName) {
        resetManagedBean(FacesContext.getCurrentInstance(), beanName);
    }

    /**
     * Remove the managed bean based on the bean name.
     *
     * @param facesContext FacesContext
     * @param beanName     the bean name of the managed bean to be removed
     */
    public static void resetManagedBean(FacesContext facesContext, String beanName) {
        getValueBinding(getJsfEl(beanName)).setValue(facesContext, null);
    }

    /**
     * Store the managed bean inside the session scope.
     *
     * @param beanName    the name of the managed bean to be stored
     * @param managedBean the managed bean to be stored
     */
    public static void setManagedBeanInSession(String beanName, Object managedBean) {
        setManagedBeanInSession(FacesContext.getCurrentInstance(), beanName, managedBean);
    }

    /**
     * Store the managed bean inside the session scope.
     *
     * @param beanName    the name of the managed bean to be stored
     * @param managedBean the managed bean to be stored
     */
    public static void setManagedBeanInSession(FacesContext facesContext,
                                               String beanName, Object managedBean) {
        facesContext.getExternalContext().getSessionMap().put(beanName, managedBean);
    }

    public static Object getManagedBeanInSession(FacesContext facesContext, String beanName) {
        return facesContext.getExternalContext().getSessionMap().get(beanName);
    }

    public static Object getManagedBeanInSession(String beanName) {
        return getManagedBeanInSession(FacesContext.getCurrentInstance(), beanName);
    }

    /**
     * Get parameter value from request scope.
     *
     * @param name the name of the parameter
     * @return the parameter value
     */
    public static String getRequestParameter(String name) {
        return getRequestParameter(FacesContext.getCurrentInstance(), name);
    }

    /**
     * Get parameter value from request scope.
     *
     * @param name the name of the parameter
     * @return the parameter value
     */
    public static String getRequestParameter(FacesContext facesContext, String name) {
        return (String) facesContext.getExternalContext().getRequestParameterMap().get(name);
    }

    public static Object getSessionAttribute(FacesContext facesContext, String name) {
        return facesContext.getExternalContext().getSessionMap().get(name);
    }

    public static Object getSessionAttribute(String name) {
        return getSessionAttribute(FacesContext.getCurrentInstance(), name);
    }

    /**
     * @param locale
     * @return
     */
    public static ResourceBundle getApplicationBundle(Locale locale) {
        return getApplicationBundle(FacesContext.getCurrentInstance(), locale);
    }

    /**
     * get application bundle
     *
     * @param facesContext
     * @param locale
     * @return
     */
    public static ResourceBundle getApplicationBundle(FacesContext facesContext, Locale locale) {
        String bundleName = facesContext.getApplication().getMessageBundle();
        if (bundleName != null) {
            try {
                return ResourceBundle.getBundle(bundleName, locale,
                        Thread.currentThread().getContextClassLoader());

            } catch (MissingResourceException e) {
                log.error("Resource bundle " + bundleName + " could not be found.");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * get default bundle
     *
     * @param locale
     * @return
     */
    public static ResourceBundle getDefaultBundle(Locale locale) {
        try {
            return ResourceBundle.getBundle(DEFAULT_BUNDLE, locale,
                    FacesContext.class.getClassLoader());

        } catch (MissingResourceException e) {
            log.error("Resource bundle " + DEFAULT_BUNDLE + " could not be found.");
            return null;
        }
    }

    /**
     * get current instance of Application
     *
     * @return current instance of Application
     */
    public static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }

    /**
     * get ValueBinding by an EL expression which like "#{expression}"
     *
     * @param el
     * @return
     */
    public static ValueBinding getValueBinding(String el) {
        return getApplication().createValueBinding(el);
    }

    /**
     * get the instanceof of HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getServletResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    private static Object getElValue(String el) {
        return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }
}
