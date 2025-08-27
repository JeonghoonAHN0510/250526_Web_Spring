package web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.model.dto.PageDto;
import web.model.dto.PostDto;
import web.service.MemberService;
import web.service.PostService;

@RestController
@RequiredArgsConstructor                    // final 변수에 대한 생성자 자동 제공
@RequestMapping("/post")
public class PostController {
    private final PostService postService;  // @RequiredArgsConstructor를 사용하여, 생성자를 만들 필요 X + @Autowired를 생략해도 자동으로 의존성 처리
    private final MemberService memberService;

    // [1] 게시물 등록
    // method : POST, URL : localhost:8080/post, body : { "ptitle" : "", "pcontent" : "", cno : }
    @PostMapping("")
    public int writePost( @RequestBody PostDto postDto, HttpSession session ){
        System.out.println("PostController.writePost");
        // 1. 현재 로그인 상태 확인
        // 2. 비로그인 상태면, 등록 실패
        if ( !memberService.checkSession( session ) ) return 0;
        // 3. 로그인 상태면, 현재 로그인 회원번호를 postDto에 대입하기
        int mno = (int) session.getAttribute("loginMno");
        postDto.setMno( mno );
        // 4. Service를 호출하고, 응답 반환
        return postService.writePost( postDto );
    } // func end

    // [2] 게시물 전체조회(페이징처리) + 검색 기능 추가
    // 검색이 없을 때 - method : GET, URL : localhost:8080/post?cno=1&page=1&perCount=5
    // 검색이 있을 때 - method : GET, URL : localhost:8080/post?cno=1&page=1&perCount=5&key=ptitle&keyword=ai
    @GetMapping("")
    // cno : 카테고리 번호, page : 현재 페이지번호, perCount : 페이지당 자료 개수
    public PageDto findAllPost( @RequestParam( defaultValue = "1" ) int cno,        // defaultValue : 만약 쿼리스트링 매개변수가 없으면, 기본값을 대입
                                @RequestParam( defaultValue = "1" ) int page,
                                @RequestParam( defaultValue = "5" ) int perCount,
                                @RequestParam( required = false ) String key,       // required = false : 쿼리스트링 매개변수가 필수가 아님을 의미
                                @RequestParam( required = false ) String keyword ){
        System.out.println("PostController.findAllPost");

        return postService.findAllPost( cno, page, perCount, key, keyword );
    } // func end

    // [3] 게시물 개별조회
    @GetMapping("/view")
    public PostDto getPost( @RequestParam int pno ){
        System.out.println("PostController.getPost");

        return postService.getPost( pno );
    } // func end
} // class end