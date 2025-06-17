package org.scoula.board.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.common.util.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.scoula.common.util.UploadFiles.upload;

@Log4j2
@Service  //@Component + 서비스 역할의 클래스라는 것이 스프링에 등록
//final 멤버를 인자로 가지는 생성자 추가
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {
    //생성자가 1개인 경우 생성자 주입으로 초기화
    //객체 주입 방법 1. 필드 위 @Autowired 2. 객체 만들때 생성자 3. set 메서드에 오토와이어드
    // 가장 선호하는 거는 객체 만들때 생성자에 넣는게 가장 좋은 방법

    //전처리 해서 dao의 메서드를 불러서 db처리 해달라고 해야함
    final private BoardMapper mapper;
    private final BoardMapper boardMapper;
    private final static String BASE_DIR = "c:/upload/board";

    @Override
    public List<BoardDTO> getList() {
        //List<BoardVO> list =  boardMapper.getList();
        //vo, vo, vo, vo, vo, vo
        //dto, dto, dto, dto, dto  => 1대1로 매치 시켜줌
        log.info("getList..........");
        return mapper.getList().stream() // BoardVO의 스트림
                .map(BoardDTO::of) // BoardDTO의 스트림
                .toList(); // List<BoardDTO> 변환
    }

    @Override
    public BoardDTO get(Long no) {
        //get 할 떄 id값 no 넘겨주기
        BoardVO vo = boardMapper.get(no);
        //다른 곳으로 넘길 때는 DTO로 만들어서 넘기자
        BoardDTO dto = BoardDTO.of(vo);
        return dto;
    }

//    @Override
//    public void create(BoardDTO board) {
//        log.info("create......" + board);
//        //전달받은 BoardDTO를 vo로 변환
//        BoardVO vo = board.toVo();
//        //DB
//        mapper.create(vo);
//        //VO에 생성된 pk 값을
//        //DTO에 전달 -> 후속 작업이 필요할때만
//        board.setNo(vo.getNo());
//    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);
        return mapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);
        return mapper.delete(no) == 1;
    }

    // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요
// RuntimeException인 경우만 자동 rollback.

    /* create() 메서드 수정 */
    // 게시글 등록 서비스
    @Transactional  // 여러 DB 작업을 하나의 트랜잭션으로 처리
    @Override
    public void create(BoardDTO board) {
        log.info("create......" + board);

        // 1. 게시글 등록
        BoardVO vo = board.toVo();         // DTO → VO 변환
        boardMapper.create(vo);            // DB에 저장
        board.setNo(vo.getNo());           // 생성된 PK를 DTO에 설정

        // 2. 첨부파일 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(vo.getNo(), files);  // 게시글 번호가 필요하므로 게시글 등록 후 처리
        }
    }

    /**
     * 파일 업로드 처리 (private 메서드)
     * @param bno 게시글 번호
     * @param files 업로드할 파일 목록
     */
    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part : files) {
            // 빈 파일은 건너뛰기
            if (part.isEmpty()) continue;

            try {
                // 파일을 서버에 저장
                String uploadPath = UploadFiles.upload(BASE_DIR, part);

                // 첨부파일 정보를 DB에 저장
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                boardMapper.createAttachment(attach);

            } catch (IOException e) {
                // @Transactional이 감지할 수 있도록 RuntimeException으로 변환
                throw new RuntimeException(e);
            }
        }
    }

    // 첨부파일 한 개 얻기
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    // 첨부파일 삭제
    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }

}