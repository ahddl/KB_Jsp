package org.scoula.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;

//@Repository //저장(db)
//@Component + db예외처리
public interface BoardMapper {
    //    @Select("select * from tbl_board order by no desc")
    public List<BoardVO> getList();

    //상세검색
    public BoardVO get(Long no);

    //게시판 글쓰기 => vo가  거의 대부분임
    public void create(BoardVO board);

    //게시판 글 업그레이드
    public int update(BoardVO board);

    //게시판 글 삭제
    public int delete(Long no);
}