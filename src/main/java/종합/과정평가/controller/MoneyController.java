package 종합.과정평가.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import 종합.과정평가.model.dto.MoneyDto;
import 종합.과정평가.service.MoneyService;

import java.util.ArrayList;

@RestController
@RequestMapping("/money")
public class MoneyController {
    // MoneyService 가져오기
    final MoneyService moneyService;
    @Autowired
    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    } // func end

    // 1. 회원매출조회
    @GetMapping("")
    public ArrayList<MoneyDto> moneyPrint(){
        System.out.println("MoneyController.moneyPrint");
        return moneyService.moneyPrint();
    } // func end


} // class end
