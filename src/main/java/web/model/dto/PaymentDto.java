package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {
    private int pno;        // 결제번호
    private int mno;        // 회원번호
    private int pamount;    // 결제금액
    private String pdate;   // 결제일시
} // class end