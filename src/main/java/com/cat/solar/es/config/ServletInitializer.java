package com.cat.solar.es.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ServletInitializer extends AbstractDispatcherServletInitializer {

              @Override
              protected WebApplicationContext createServletApplicationContext() {
                             AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
                             context.scan("com.cat.solar.es*");
                             context.register(springfox.documentation.swagger2.web.Swagger2Controller.class);
                             context.refresh();
                             //context.scan("springfox.documentation.swagger2.web");
                             //springfox.documentation.swagger2.web
                             return context;
              }

              @Override
              protected String[] getServletMappings() {
                             return new String[] { "/" };
              }

              @Override
              protected WebApplicationContext createRootApplicationContext() {
                             return null;
              }
              
              @Override
              public void onStartup(ServletContext servletContext) throws ServletException {
                             super.onStartup(servletContext);
                             DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
                             filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
                             servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
              }

}
