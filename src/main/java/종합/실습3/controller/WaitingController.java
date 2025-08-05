package 종합.실습3.controller;

import org.springframework.web.bind.annotation.*;
import 종합.실습3.model.dao.WaitingDao;
import 종합.실습3.model.dto.WaitingDto;

import java.util.ArrayList;

@RestController
public class WaitingController {
    // WaitingDao 싱글톤 가져오기
    private WaitingDao waitingDao = WaitingDao.getInstance();

    // 1. 대기 등록 기능
    // 메소드명 : waitingRegis
    // 매개변수 : WaitingDto
    // 반환값 : true(성공)/false(실패) -> boolean
    @PostMapping("/waiting")
    public boolean waitingRegis( @RequestBody WaitingDto waitingDto ){
        // dao에게 객체 전달 후 결과 받기
        boolean result = waitingDao.waitingRegis( waitingDto );
        // view에게 결과 전달하기
        return result;
    } // func end

    // 2. 대기현황 조회 기능
    // 메소드명 : waitingList
    // 매개변수 : X
    // 반환값 : ArrayList<WaitingDto>
    @GetMapping("/waiting")
    public ArrayList<WaitingDto> waitingList(){
        // dao로부터 결과 받기
        ArrayList<WaitingDto> waitingDtos = waitingDao.waitingList();
        // view에게 결과 전달하기
        return waitingDtos;
    } // func end

    // 3. 대기 삭제 기능
    // 메소드명 : waitingDelete
    // 매개변수 : int wno
    // 반환값 : true(성공)/false(실패) -> boolean
    @DeleteMapping("/waiting")
    public boolean waitingDelete( @RequestParam int wno ){
        // dao에게 전달 후 결과 받기
        boolean result = waitingDao.waitingDelete( wno );
        // view 에게 결과 전달하기
        return result;
    } // func end

    // 4. 대기 수정 기능
    // 메소드명 : waitingUpdate
    // 매개변수 : WaitingDto
    // 반환값 : true(성공)/false(실패) -> boolean
    @PutMapping("/waiting")
    public boolean waitingUpdate( @RequestBody WaitingDto waitingDto ){
        // dao에게 객체 전달 후 결과 받기
        boolean result = waitingDao.waitingUpdate( waitingDto );
        // view에게 결과 전달하기
        return result;
    } // func end
} // class end
