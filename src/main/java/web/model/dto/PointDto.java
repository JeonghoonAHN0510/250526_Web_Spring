package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PointDto {
    private int plno;
    private int mno;
    private int plpoint;
    private String plcomment;
    private String pldata;

    // + 부가적인 정보 추가
    // 1. 특정 회원의 합계 포인트
    private int totalPoint;
} // class end