package Days.Day10;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data   // = @Getter + @Setter + @ToString
public class LoginDto {
    private int mno;    // 회원번호
    private String mid; // 회원아이디
    private String mpw; // 회원비밀번호
} // class end
