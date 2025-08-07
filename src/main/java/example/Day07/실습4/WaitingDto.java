package example.Day07.실습4;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WaitingDto {
    // 1. 멤버변수
    private int wno;
    private String wphone;
    private int wcount;
    private String wdate;
    // 2. 생성자 > Lombok으로 대체
    // 3. 메소드 > Lombok으로 대체
} // class end
