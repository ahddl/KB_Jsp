package org.scoula.ex03.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

    //요청 하나당 함수 하나임
    // 요청 들어왔을 때 (첫 페이지) 호출할 함수를 정의함
    // 도메인 별 컨트롤러의 메서드의 리턴은 결과를 담을 view 파일 이름이 되어야함
    public String getIndex(HttpServletRequest request, HttpServletResponse response){
        //함수 별 처리 내용을 여기에 쓰기!!
        //첫 페이지라 아무것도 안써도 됨
        return "index";//view 파일 이름만 리턴
    }
}
