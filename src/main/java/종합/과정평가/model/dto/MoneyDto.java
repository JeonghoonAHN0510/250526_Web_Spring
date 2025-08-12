package 종합.과정평가.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MoneyDto {
    private int custno;     // 회원코드
    private int salenol;    // 판매코드
    private int pcost;      // 단가
    private int amount;     // 수량
    private int price;      // 가격
    private String pcode;   // 상품코드
    private String sdate;   // 판매날짜
} // class end
