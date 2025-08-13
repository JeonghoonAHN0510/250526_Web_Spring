package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
    private int mno;        // 회원 번호
    private String mid;     // 회원 아이디
    private String mpwd;    // 회원 비밀번호
    private String mname;   // 회원 이름
    private String mphone;  // 회원 연락처
    private String mdate;   // 가입일
} // class end
