package web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.model.dto.PageDto;
import web.model.dto.PostDto;
import web.service.MemberService;
import web.service.PostService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PostDto getPost( @RequestParam int pno, HttpSession session ){
        System.out.println("PostController.getPost");
        // HttpSession : 브라우저마다 별도의 저장소 개념
        // 1. 24시간 내 1번만 조회수 증가 요청 가능
        // 1-1. 세션 속성 내 'viewHistory' 값 가져오기
            // viewHistory : 사용자가 조회한 게시물정보
        Object object = session.getAttribute("viewHistory");
        Map< Integer, String > viewHistory;
        // 1-2. 만약에 viewHistory가 존재하지 않으면
        if ( object == null ){
            // 1-3. 새롭게 만들어주고
            viewHistory = new HashMap<>();
        } else {
            // 1-3. 존재하면, 기존 자료를 타입변환한다.
            viewHistory = (Map< Integer, String >) object;
        } // if end
        // 1-4. 현재 날짜를 문자열로 가져오기
            // LocalDate.now() : 현재 시스템 날짜 반환 함수
        String today = LocalDate.now().toString();
        // 1-5. 현재 게시물과 today를 조합하여, 기록을 체크한다.
        String check = viewHistory.get( pno );
        // 1-6. 오늘 기록이 없거나, 기록이 현재 날짜와 다르다면
        if ( check == null || !check.equals( today ) ){
            // 1-7. 조회수 증가
            postService.incrementView( pno );
            // 1-8. 오늘 기록
            viewHistory.put( pno, today );
            // 1-9. 세션에 업데이트
            session.setAttribute("viewHistory", viewHistory);
        } // if end
        // 2. 요청한 사람이 본인이 작성한 글인지 확인
        PostDto postDto = postService.getPost( pno );
        // 2-1. 로그인 세션 확인
        Object loginObject = session.getAttribute("loginMno");
        // 2-2. 로그인정보가 없으면 0, 있으면 타입변환하여 저장
        int loginMno = loginObject == null ? 0 : (int) loginObject;
        System.out.println("loginMno = " + loginMno);
        // 2-3. 작성자와 클라이언트가 같으면, host = true로 변경
        if ( postDto.getMno() == loginMno ) postDto.setHost( true );
        // 3. 최종적으로 반환
        return postDto;
    } // func end

    // [4] 게시물 삭제
    @DeleteMapping("")
    public boolean deletePost( @RequestParam int pno, HttpSession session ){
        System.out.println("PostController.deletePost");

        return postService.deletePost( pno, session );
    } // func end

    // [5] 게시물 수정
    @PutMapping("")
    public int updatePost( @RequestBody PostDto postDto, HttpSession session ){
        System.out.println("PostController.updatePost");

        return postService.updatePost( postDto, session );
    } // func end

    // 댓글 등록
    @PostMapping("/reply")
    public int writeReply( @RequestBody Map<String, String> map, HttpSession session ){
        System.out.println("PostController.writeReply");

        return postService.writeReply( map, session );
    } // func end

    // 댓글 전체조회
    @GetMapping("/reply")
    public List<Map<String, String>> findAllReply( @RequestParam int pno ){
        System.out.println("PostController.findAllReply");

        return postService.findAllReply( pno );
    } // func end
} // class end