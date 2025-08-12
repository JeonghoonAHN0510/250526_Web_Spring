package 종합.실습5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.실습5.model.dto.WaitingDto;
import 종합.실습5.service.WaitingService;

import java.util.ArrayList;

@RestController
@RequestMapping("/waiting")
public class WaitingController {
    // WaitingService 가져오기
    final WaitingService waitingService;
    @Autowired
    public WaitingController(WaitingService waitingService) {
        this.waitingService = waitingService;
    } // func end


    // 1. 대기 현황 등록 기능
    // 조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.
    @PostMapping("")
    public boolean waitingAdd( @RequestBody WaitingDto waitingDto ){
        System.out.println("WaitingController.waitingAdd");
        System.out.println("waitingDto = " + waitingDto);
        return waitingService.waitingAdd( waitingDto );
    } // func end

    // 2. 대기 현황 전체 조회 기능 , /waiting/list.jsp
    // 조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    @GetMapping("")
    public ArrayList<WaitingDto> waitingPrintAll(){
        System.out.println("WaitingController.waitingPrintAll");
        return waitingService.waitingPrintAll();
    } // func end

    // 3. 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
    // 조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    @GetMapping("/find")
    public WaitingDto waitingPrint( @RequestParam int wno ){
        System.out.println("WaitingController.waitingPrint");
        System.out.println("wno = " + wno);
        return waitingService.waitingPrint( wno );
    } // func end

    // 4. 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
    // 조건 : 선택한 대기번호의 정보를 삭제합니다.
    @DeleteMapping("")
    public boolean waitingDelete( @RequestParam int wno ){
        System.out.println("WaitingController.waitingDelete");
        System.out.println("wno = " + wno);
        return waitingService.waitingDelete( wno );
    } // func end

    // 5. 특정 대기 현황 수정 기능 , /waiting/update.jsp
    // 조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다.
    // 조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다.
    @PutMapping("")
    public boolean waitingUpdate( @RequestBody WaitingDto waitingDto ){
        System.out.println("WaitingController.waitingUpdate");
        System.out.println("waitingDto = " + waitingDto);
        return waitingService.waitingUpdate( waitingDto );
    } // func end
} // class end
