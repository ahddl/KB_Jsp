package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j2
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping
    public String hello(Model model){
        log.info("======> hello");

        /*
        Model
        - 컨트롤러가 View 로 데이터를 전달할 때 사용하는 객체
        - key-value 쌍으로 데이터를 저장
         */
        model.addAttribute("message","신규 핸들러 메소드 호출!");

        return "page/mappingResult";
    }

    // /example/modify로 들어오는 요청 처리 -> 기본으론 GET/ POST 등등 다 함
    //method를 이용해 특정 HTTP메소드의 요청에만 처리하도록 제한
    // /example/modify로 들어오는 Post만 처리하게 함 -> method = RequestMethod.POST)
//    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @PostMapping("/modify")
    public String modify(Model model){
        model.addAttribute("message","수정용 핸들러 메소드 호출~~");

        return "page/mappingResult";
    }

    /*
    @GetMapping
    @PostMapping
    @DeleteMapping
    @PutMapping
     */

    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("message","삭제 핸들러 메소드 호출~~");

        return "page/mappingResult";
    }

    @GetMapping(value = {"/multi/modify","/multi/delete"})
    public String modifyAndDelete(Model model){
        model.addAttribute("message","수정 및 삭제 핸들러 메소드 호출~~");

        return "page/mappingResult";
    }
}
