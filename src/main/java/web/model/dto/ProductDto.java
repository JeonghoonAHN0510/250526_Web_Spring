package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data   // @Getter + @Setter + @ToString
public class ProductDto {
    // 기본적으로 테이블과 동일한 멤버변수 선언
    private int pno;
    private String pname;
    private int pprice;
    private String pcomment;
    private String pdate;
    private double plat;
    private double plng;
    private int mno;

    // + 부가적인 정보 추가
    // 1. 업로드에 사용할 multipartFile 객체를 List 타입으로 여러개 첨부파일 받아오기
    private List<MultipartFile> uploads;
    // 2. 조회 시 업로드된 파일명을 문자열로 조회하기
    private List<String> images;
    // 3. 판매자의 아이디 -> join해서 가져와야한다.
    private String mid;
} // class end