package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//싱글톤으로 만들어 주고, 스프링에 이 클래스가 컨트롤러 역할을 하는 클래스라고 등록시켜줌
@Controller
@Log4j2
public class HomeController {
    //요청 하나당 함수 하나
    //요청이 어떻게 들어오는지 설정 어떤 함수를 부를지 정의함

    @GetMapping("/")  // "/"주소로 get요청이 들어오면
    public String home() {
        log.info("================> HomController /");
        return "index"; // View의 이름 프론트 컨트롤러에서 리턴함

        //뷰리졸버가 /WEB-INF/views/index.jsp 를 붙여서
        //프론트 컨트롤러가 불러야할 파일명으로 만들어줌 (렌더링)
    }
}