package com.jun.servletContainerInitializer;

import com.jun.controller.ServletContainerInitializerServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("this is ServletContainerInitializer,there are "+c);
        ServletRegistration.Dynamic servlet = ctx.addServlet("servletContainerInitializerServlet", ServletContainerInitializerServlet.class);
        servlet.addMapping("/servletContainerInitializerServlet");
    }
}
