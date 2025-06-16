package org.scoula.board.mapper;

import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

    public List<BoardVO> getList();

    public BoardVO get(Long no);

    public int create(BoardVO boardVO);

    public int update(BoardVO boardVO);

    public int delete(Long no);

}

