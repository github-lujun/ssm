package com.jun.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/annotationServelt",initParams = {@WebInitParam(name = "age",value = "20")})
public class AnnotationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getCharacterEncoding());
        System.out.println(resp.getCharacterEncoding());
        resp.setCharacterEncoding("gb2312");
        resp.getWriter().println("这里是 "+this.getServletName()+","+this.getInitParameter("age"));
    }
}
