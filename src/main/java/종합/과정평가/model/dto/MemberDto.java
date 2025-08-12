package 종합.과정평가.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
    private int custno;         // 회원코드
    private String custname;    // 회원이름
    private String phone;       // 연락처
    private String address;     // 회원주소
    private String joindate;    // 가입날짜
    private String grade;       // 고객등급
    private String city;        // 거주도시 코드
} // class end
