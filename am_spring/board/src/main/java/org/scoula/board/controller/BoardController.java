package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // final field 생성자 자동 생성
public class BoardController {
    //의존성 주입
    private final BoardService service;

    //목록 조회
    @GetMapping("/list")  //board/list
    public void list(Model model) {
        /*
        db에서 가지고 온 것 있어야함.
        Controller --> Service --> dao
        요청한 주소와 views의 호출할 파일명이 같으면 return 안해도 됨.
         */
        log.info("=====list");
        //boardDTO 리스트 모델에 담기
        model.addAttribute("list",service.getList());
    }


    //get -> create
    @GetMapping("/create") //board/create(입력화면 보여줘)
    public void create() {

        log.info("create");

    }

    //post -> create
    @PostMapping("/create")
    public String create(BoardDTO board) {
        log.info("create: " + board);
        service.create(board);
        return "redirect:/board/list";

    }

    //get -> get, update
    @GetMapping({ "/get", "/update" }) //board/update(수정하기 전에 검색 먼저 해서 한번 보여줘)
    public void get(@RequestParam("no") Long no, Model model) {

        log.info("/get or update");
        model.addAttribute("board", service.get(no));

    }

    //post -> update
    @PostMapping("/update")
    public String update(BoardDTO board) {
        log.info("update:" + board);
        service.update(board);
        return "redirect:/board/list";

    }

    //post -> delete
    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {

        log.info("delete..." + no);
        service.delete(no);
        return "redirect:/board/list";

    }

//    //상세조회 ( 단건조회
//    @GetMapping("/get") //board/get
//    public void get(Model model, @RequestParam("id") int id) {
//    }

//    //글 등록
//    @PostMapping("/create")
//    public String create(BoardDTO board) {
//        log.info("create: "+ board);
//        service.create(board);
//        return "redirect:/board/list";
//    }
//
//    @PostMapping("/update")
//    public String update(BoardDTO board) {
//        log.info("update: "+ board);
//        boolean result = service.update(board);
//        return "redirect:/board/list";
//    }
//
//    @PostMapping("/delete")
//    public String delete(@RequestParam("no") long no) {
//        log.info("/delete");
//        service.delete(no);
//        return "redirect:/board/list";
//    }
    /**
     * 파일 다운로드 처리
     * @param no 첨부파일 번호
     * @param response HTTP 응답 객체
     * @throws Exception
     */
    @GetMapping("/download/{no}")
    @ResponseBody  // View를 사용하지 않고 직접 응답 데이터 전송
    public void download(@PathVariable("no") Long no,
                         HttpServletResponse response) throws Exception {

        // 첨부파일 정보 조회
        BoardAttachmentVO attach = service.getAttachment(no);

        // 실제 파일 객체 생성
        // (java.io.File)
        File file = new File(attach.getPath());

        // 파일 다운로드 처리
        UploadFiles.download(response, file, attach.getFilename());
    }
}
