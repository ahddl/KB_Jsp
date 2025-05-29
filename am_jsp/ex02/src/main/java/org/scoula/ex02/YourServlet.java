package org.scoula.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//컨테이너에 등록할 떄 방법 2가지 --> 1) 어노테이션, 2) web.xml
//@WebServlet(name = "you", value = "/you")
public class YourServlet extends HttpServlet {

    //서블릿객체는 최초 호출했을 때 생성됨 -> 객체는 하나만 만들어서 계속 사용
    //객체 생성시 초기화는 1번, 소멸시 1번 호출해서 기능을 정의할 수 있음.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("YourServletdoGet");
        //
    }
}
