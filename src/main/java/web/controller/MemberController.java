package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

} // class end
