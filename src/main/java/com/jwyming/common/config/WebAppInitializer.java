package com.jwyming.common.config;

import javax.faces.webapp.FacesServlet;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.getEnvironment().setActiveProfiles("production");
        rootContext.register(AppConfig.class);

        ContextLoaderListener contex = new ContextLoaderListener(rootContext);

        // Manage the lifecycle of the root application context
        servletContext.addListener(contex);
        servletContext.addListener(new RequestContextListener());
        servletContext.addListener(new com.sun.faces.config.ConfigureListener());

        // not for production
        servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "0");
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");

        // not for production
        //servletContext.setInitParameter("org.richfaces.skin", "deepMarine");

//		FilterRegistration.Dynamic facesExceptionFilter = servletContext
//				.addFilter("facesExceptionFilter",
//						org.omnifaces.filter.FacesExceptionFilter.class);
//		facesExceptionFilter.addMappingForServletNames(null, true,
//				"Faces Servlet");

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic facesServlet = servletContext.addServlet(
                "Faces Servlet", new FacesServlet());
        facesServlet.setLoadOnStartup(1);
        facesServlet.addMapping("*.xhtml");
    }

}
