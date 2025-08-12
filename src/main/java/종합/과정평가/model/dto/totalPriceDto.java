package 종합.과정평가.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class totalPriceDto {
    private int custno;         // 회원코드
    private String custname;    // 회원성명
    private String grade;       // 회원등급
    private int totalPrice;     // 회원별 총 매출
} // class end
