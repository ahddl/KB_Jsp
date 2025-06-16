package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // final field 생성자 자동 생성
public class BoardController {
    //의존성 주입
    final private BoardService boardService;

    //목록 조회
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list 동작" + model);

        //boardDTO 리스트 모델에 담기
        model.addAttribute("list",boardService.getList());
    }

    //상세조회 ( 단건조회
    @GetMapping("/get")
    public void get(Model model, @RequestParam("id") int id) {

    }

    //글 등록
    @PostMapping("/create")
    public String create(BoardDTO board) {
        log.info("create: "+ board);
        boardService.create(board);
        return "redirect:/board/list";
    }

    @PostMapping("/update")
    public String update(BoardDTO board) {
        log.info("update: "+ board);
        boolean result = boardService.update(board);
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") long no) {
        log.info("/delete");
        boardService.delete(no);
        return "redirect:/board/list";
    }
}
