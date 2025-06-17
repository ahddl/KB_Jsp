package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    //   게시글 전체 조회
    public List<BoardDTO> getList();

    //  게시글 단건 조회
    public BoardDTO get(Long no);

    //게시글 생성
    public void create(BoardDTO board);

    //게시글 수정
    public boolean update(BoardDTO board);

    //게시슬 삭제
    public boolean delete(Long no);

    //하나의 첨부파일 정보를 알고자 하는 경우
    public BoardAttachmentVO getAttachment(Long no);

    //첨부파일을 삭제하고자 하는 경우
    public boolean deleteAttachment(Long no);
}