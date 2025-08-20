package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberimgDto {
    private int mimgno;         // 회원이미지번호
    private String mimgname;    // 파일명
    private int mno;            // 회원번호
} // class end