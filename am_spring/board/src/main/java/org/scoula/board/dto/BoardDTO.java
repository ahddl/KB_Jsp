package org.scoula.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date reg_date;
    private Date update_date;

    //복잡하지도 않고, 순서도 상관없이 값을 넣어서 객체를 만들고자 하는 방법
    //Builder객체 이용

// VODTO 변환
    public static BoardDTO of(BoardVO vo) {
        return vo == null ? null : BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .reg_date(vo.getReg_date())
                .update_date(vo.getUpdate_date())
                .build();

    }

// DTOVO 변환
    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer)
                .reg_date(reg_date)
                .update_date(update_date)
                .build();
    }
}