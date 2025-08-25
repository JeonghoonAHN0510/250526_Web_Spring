package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
    // 게시물 정보를 담는 클래스
    private int pno;            // 게시물 번호
    private String ptitle;      // 게시물 제목
    private String pcontent;    // 게시물 내용
    private String pdate;       // 게시물 작성일
    private int pview;          // 게시물 조회수
    private int mno;            // 게시물 작성자 번호
    private int cno;            // 게시물 카테고리 번호
    // + 부가적인 정보
    private String cname;       // 카테고리명
    private String mid;         // 게시물 작성자 아이디
    private boolean host;       // 본인이 작성한 글인지 확인
} // class end