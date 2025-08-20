package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    // 부가적인 정보
    private MultipartFile upload;   // 업로드용
    private int mimgno;             // 회원이미지번호
    private String mimgname;        // 파일명
} // class end