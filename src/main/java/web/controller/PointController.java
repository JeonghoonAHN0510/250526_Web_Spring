package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PointDto;
import web.service.MemberService;
import web.service.PointService;

import java.util.List;

@RestController
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;
    private final MemberService memberService;
    @Autowired
    public PointController( PointService pointService, MemberService memberService ){
        this.pointService = pointService;
        this.memberService = memberService;
    } // func end

    // [1] 포인트 지급 : MemberController에서 진행


    // [2] 특정 회원 포인트 지급 전체 조회
    @GetMapping("/getlog")
    public List<PointDto> getPointlog( HttpSession session ){
        System.out.println("PointController.getPointlog");
        // 1. 로그인 유효성 검사
        if( !memberService.checkSession( session ) ) return null;
        // 2. 로그인 상태라면, loginMno 꺼내기
        int mno = ( int ) session.getAttribute("loginMno");
        // 3. Service에게 mno 전달 후 결과 반환하기
        return pointService.getPointlog( mno );
    } // func end

    // [3] 특정 회원 포인트 합계 조회
    @GetMapping("/gettotal")
    public int memberPoint( HttpSession session ){
        System.out.println("PointController.memberPoint");
        // 1. 로그인 유효성 검사
        if( !memberService.checkSession( session ) ) return 0;
        // 2. 로그인 상태라면, loginMno 꺼내기
        int mno = ( int ) session.getAttribute("loginMno");
        // 3. Service에게 mno 전달 후 결과 반환하기

        return pointService.memberPoint( mno );
    } // func end
} // class end