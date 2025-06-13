package org.scoula.ex03.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller //싱글톤 + 클래스안에서 설정한 주소->함수 매핑을 핸들러매퍼에게 등록시켜줌.
//@Component + 컨트롤러 기능도 추가.
@RequestMapping("/sample") //해당 도메인을 요청할 때는 앞에다 sample이라고 붙이자.!
//http://localhost:8080/sample/insert
// /sample/delete, /sample/update
public class SampleController {

    //sample 도메인을 위한 여러 요청에 대한 함수를 정의
    @RequestMapping("") //sample
    public String sample(){
        log.info("SampleController==========================");
        return "sample"; // WEB-INF/views/sample.jsp =>없는 경우404 발생
    }

    @GetMapping("/basicOnlyGet")
    public void basicOnlyGet(){
        //void인 경우 에 호출한 주소를 사용해서 WEB-INF/views/요청주소.jsp
        //요청 주소와 views 밑에 파일이름 같아면 String 으로 return 안해도됨\
        // /sample/basicOnlyGet.jsp 를 찾음
        log.info("/basicOnlyGet==========");
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public void basic(){
        log.info("/basic =================");
        //스프링은 컨트롤러로 오면 그 다음은 무조건 jsp파일을 호출하는 흐름임
        //forward라고함 => 결과를 넣는 jsp 페이지가 필요할 때
        //redirect 해서  view로 안 갈 수도 있음 ==> 다른 페이지로 전환
    }

    @GetMapping("/ex01")
    public String ex01(String name,@RequestParam("age")int age2){

        //@Request

        //원래 http 로 전달받은 데이터는 모두 String
        //int age 라고 하는 경우 이것을 숫자로 변경해주는 작업도 스프링이 해준다
        log.info("ex01 =======================");

        System.out.println("name:" + name);
        System.out.println("age: "+ (age2));
        return "ex01";
    }
}