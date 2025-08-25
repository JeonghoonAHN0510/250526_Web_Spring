package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageDto {
    // 출력할 정보(게시물/회원/제품 등)들을 갖는 클래스
    private int totalCount;         // 전체 자료 개수
    private int currentPage;        // 현재 페이지 번호
    private int totalPage;          // 전체 페이지 개수
    private int startBtn;           // 페이지버튼 시작 번호
    private int endBtn;             // 페이지버튼 끝 번호
    private int perCount;           // 페이지 1개당 보이는 자료 개수
    private Object data;            // 페이징 처리된 자료 -> 여러 정보를 담기 위해서 Object 사용
} // class end