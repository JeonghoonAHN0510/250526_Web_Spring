package 종합.과정평가.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import 종합.과정평가.service.MemberService;

@RestController
public class MemberController {
    // MemberService 가져오기
    final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } // func end




} // class end
