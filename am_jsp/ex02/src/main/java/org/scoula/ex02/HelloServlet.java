package org.scoula.ex02;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
@WebServlet(name="HelloServlet", urlPatterns={"/xxx", "/yyy" })
public class HelloServlet extends HttpServlet {
    private String message;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init okk");
        message = "Hello World!";
    }

    @Override
    public void destroy() {
        System.out.println("destroy okk");

    }
}