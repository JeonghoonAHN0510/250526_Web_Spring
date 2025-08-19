package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.service.PointService;

@RestController
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;
    @Autowired
    public PointController( PointService pointService ){
        this.pointService = pointService;
    } // func end

    // [1] 포인트 지급


    // [2] 포인트 지급 전체 조회


    // [3] 특정 회원 포인트 합계 조회
    @GetMapping("/find")
    public int memberPoint( @RequestParam int mno ){
        System.out.println("PointController.memberPoint");
        System.out.println("mno = " + mno);

        return pointService.memberPoint( mno );
    } // func end
} // class end