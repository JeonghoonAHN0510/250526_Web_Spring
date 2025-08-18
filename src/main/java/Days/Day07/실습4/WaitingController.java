package Days.Day07.실습4;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/waiting")
public class WaitingController {

    // * WaitingDao 싱글톤 가져오기
    private WaitingDao waitingDao = WaitingDao.getInstance();

    // 1. 등록 기능
    @PostMapping("/add")
    public boolean add( @RequestBody WaitingDto waitingDto ){
        System.out.println("WaitingController.add");
        System.out.println("waitingDto = " + waitingDto);
        return waitingDao.add( waitingDto );
    } // func end

    // 2. 전체조회 기능
    @GetMapping("/print")
    public List<WaitingDto> print(){
        System.out.println("WaitingController.print");
        return waitingDao.print();
    } // func end
} // class end
