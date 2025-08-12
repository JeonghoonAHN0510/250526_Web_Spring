package 종합.과정평가.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import 종합.과정평가.service.MoneyService;

@RestController
public class MoneyController {
    // MoneyService 가져오기
    final MoneyService moneyService;
    @Autowired
    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    } // func end


} // class end
