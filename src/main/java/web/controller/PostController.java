package web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    // method : POST, URL : localhost:8080/post/write, body : { "ptitle" : "", "pcontent" : "", cno : }
    @PostMapping("/write")
    public int writePost( @RequestBody PostDto postDto, HttpSession session ){
        // 1. 현재 로그인 상태 확인
        // 2. 비로그인 상태면, 등록 실패
        if ( !memberService.checkSession( session ) ) return 0;
        // 3. 로그인 상태면, 현재 로그인 회원번호를 postDto에 대입하기
        int mno = (int) session.getAttribute("loginMno");
        postDto.setMno( mno );
        // 4. Service를 호출하고, 응답 반환
        return postService.writePost( postDto );
    } // func end
} // class end