package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController    // 스프링 컨테이너에 객체 등록
@RequestMapping("/member")
public class MemberController {
    // * MemberService 가져오기
    private final MemberService memberService;
    @Autowired  // 스프링 컨테이너에 등록된 빈 주입받기
    public MemberController( MemberService memberService ){
        this.memberService = memberService;
    } // func end

    // [member01] 회원가입 기능 - signup
    @PostMapping("/signup")
    public int signup( @RequestBody MemberDto memberDto ){
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);

        return memberService.signup( memberDto );
    } // func end

    // [member02] 로그인 기능 - login
    @PostMapping("/login")  // 비밀번호를 보여주지않기 위해서, POST
    public int login(@RequestBody MemberDto memberDto, HttpServletRequest request ){
        System.out.println("MemberController.login");
        System.out.println("memberDto = " + memberDto);
        // 0. 로그인한 회원번호 가져오기
        int mno = memberService.login( memberDto );
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2. 로그인을 성공했다면
        if ( mno > 0 ){
            // 3. 세션 속성 추가하기
            session.setAttribute( "loginMno" , mno );
        } // if end
        // 4. 결과 반환하기
        return mno;
    } // func end

    // [member03] 로그아웃 기능 - logout
    @GetMapping("/logout")
    public boolean logout( HttpServletRequest request ){
        System.out.println("MemberController.logout");
        // 1. 세션정보 가져오기
        HttpSession session = request.getSession();
        // 2. 세션정보가 없거나 특정한 속성값이 없으면( 유효성 검사 )
        if ( session == null || session.getAttribute( "loginMno" ) == null ){
            // 3. 비로그인 상태여서, 로그아웃 실패
            System.out.println("[로그아웃 실패]");
            return false;
        } // if end
        // 4. 로그인 상태라면, 속성값 제거
        session.removeAttribute( "loginMno" );
        // 5. 로그아웃 성공 반환
        System.out.println("[로그인 성공]");
        return true;
    } // func end

    // [member04] 내정보 확인 기능 - info
} // class end
