package 종합.과정평가.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.과정평가.model.dto.MemberDto;
import 종합.과정평가.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    // MemberService 가져오기
    final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } // func end

    // 1. 회원등록기능
    // 0 : 성공, 1 : 등록실패, 2 : 성명 입력 X, 3: 전화 입력 X, 4 : 주소 입력 X, 5 : 가입일자 입력 X, 6 : 등급 입력 X, 7 : 도시코드 입력 X, 8 : 회원번호 입력 X
    @PostMapping("")
    public int addMember( @RequestBody MemberDto memberDto ){
        System.out.println("MemberController.addMember");
        System.out.println("memberDto = " + memberDto);
        // 유효성 검사
        if ( memberDto.getCustname().isEmpty() ){
            return 2;
        } else if ( memberDto.getPhone().isEmpty() ){
            return 3;
        } else if ( memberDto.getAddress().isEmpty() ){
            return 4;
        } else if ( memberDto.getJoindate().isEmpty() ){
            return 5;
        } else if ( memberDto.getGrade().isEmpty() ){
            return 6;
        } else if ( memberDto.getCity().isEmpty() ){
            return 7;
        } else if ( memberDto.getCustno() == 0 ){
            return 8;
        } // if end
        return memberService.addMember(memberDto);
    } // func end

    // 2. 회원번호 자동생성
    @GetMapping("/custno")
    public int custnoPrint(){
        return memberService.custnoPrint();
    } // func end




} // class end
