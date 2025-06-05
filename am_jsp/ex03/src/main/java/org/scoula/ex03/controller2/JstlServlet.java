package org.scoula.ex03.controller2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@WebServlet("/jstl_ex")
public class JstlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
            IOException {
        // java.lang.String 클래스의 메서드들을 가져옴
        Method[] methods = String.class.getDeclaredMethods();
        List<Method> methodList = Arrays.asList(methods);

        // java.lang.reflect.Member 타입의 리스트로 처리 가능
        req.setAttribute("members", methodList);  // Method는 Member 인터페이스를 구현함
        req.setAttribute("className", "java.lang.String");

        req.getRequestDispatcher("jstl_ex.jsp").forward(req, res);
    }
}
