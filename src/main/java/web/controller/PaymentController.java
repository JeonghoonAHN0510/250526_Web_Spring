package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PaymentDto;
import web.service.MemberService;
import web.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final MemberService memberService;
    @Autowired
    public PaymentController( PaymentService paymentService, MemberService memberService ) {
        this.paymentService = paymentService;
        this.memberService = memberService;
    } // func end


    // [1] 포인트 결제 진행
    // 성공하면 결제번호, 실패하면 0 반환
    @PostMapping("/add")
    public int payment( @RequestParam int point, HttpSession session ){
        System.out.println("PointController.payment");

        // 1. 로그인 유효성 검사
        if( !memberService.checkSession( session ) ) return 0;
        // 2. 로그인 상태라면, loginMno 꺼내기
        int mno = ( int ) session.getAttribute("loginMno");
        // 3. Service에게 전달할 객체 생성하기
        PaymentDto paymentDto = new PaymentDto();
        // 4. 생성한 객체에 값 넣기
        paymentDto.setMno( mno );
        paymentDto.setPamount( point );
        // 5. Service에게 전달 후 결과 반환
        return paymentService.payment( paymentDto );
    } // func end
} // class end