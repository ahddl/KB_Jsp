package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class} )
@Log4j2
class BoardServiceTest {

    @Autowired
    private BoardService service;

    @Test
    public void getList() {
        for(BoardDTO board: service.getList()) {
            log.info(board);
        }

    }
    @Test
    void get() {
        log.info(service.get(1L));
    }

    @Test
    public void create() {
        BoardDTO board = new BoardDTO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("user1");
        service.create(board);
        log.info("생성된 게시물의 번호: " + board.getNo());
    }
    
//    PM강사님작성
//    @Test
//     void create(){
//        BoardDTO board = BoardDTO.builder()
//                .title("새로운 테스트 제목")
//                .content("새로운테스트제목")
//                .writer("user")
//                .build();
//    }

    @Test
    public void update() {
        BoardDTO board = service.get(1L);
        board.setTitle("제목 수정합니다.");
        board.setContent("제목 수정함");
        boolean result = service.update(board);
        log.info("update RESULT: " + result);
        assertTrue(result);
    }

    @Test
    public void delete() {
        //테스트 대상
        long testNo = 2L;
        //삭제 전 확인
        //삭제할 데이터를 조회했을 떄 데이터가 없으서 발생하는 예외가 발생안하는 걸 확인
        assertDoesNotThrow(()-> service.delete(testNo));

        boolean result = service.delete(testNo);
        assertTrue(result);
        assertThrows(NoSuchElementException.class,()->{
            service.delete(testNo);
        });

        // 게시물 번호의 존재 여부를 확인하고 테스트할 것
        log.info("delete RESULT: " + service.delete(2L));

    }
}