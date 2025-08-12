package 종합.과정평가.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MoneyDto {
    private int custno;
    private int salsnol;
    private int pcost;
    private int amount;
    private int price;
    private String pcode;
    private String sdate;
} // func end
