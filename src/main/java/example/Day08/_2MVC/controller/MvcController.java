package example.Day08._2MVC.controller;

import example.Day08._2MVC.service.MvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // [1] Controller bean 등록
public class MvcController {
    @Autowired      // [2] Service bean 등록
    private MvcService mvcService;

    // [3] 기능구현 테스트
    @GetMapping("/day08/mvc")
    public void method(){
        // 통신 확인용 프린트
        System.out.println("MvcController.method");
        // MvcService의 메소드 호출
        mvcService.method();
    } // func end

} // class end
