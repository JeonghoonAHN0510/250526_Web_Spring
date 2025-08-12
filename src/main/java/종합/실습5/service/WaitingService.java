package 종합.실습5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.실습5.model.dao.WaitingDao;
import 종합.실습5.model.dto.WaitingDto;

import java.util.ArrayList;

@Service
public class WaitingService {
    // WaitingDao 가져오기
    final WaitingDao waitingDao;
    @Autowired
    public WaitingService(WaitingDao waitingDao) {
        this.waitingDao = waitingDao;
    } // func end


    // 1. 대기 현황 등록 기능
    // 조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.
    public boolean waitingAdd( WaitingDto waitingDto ){
        return waitingDao.waitingAdd( waitingDto );
    } // func end

    // 2. 대기 현황 전체 조회 기능 , /waiting/list.jsp
    // 조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public ArrayList<WaitingDto> waitingPrintAll(){
        return waitingDao.waitingPrintAll();
    } // func end

    // 3. 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
    // 조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public WaitingDto waitingPrint( int wno ){
        return waitingDao.waitingPrint( wno );
    } // func end

    // 4. 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
    // 조건 : 선택한 대기번호의 정보를 삭제합니다.
    public boolean waitingDelete( int wno ){
        return waitingDao.waitingDelete( wno );
    } // func end

    // 5. 특정 대기 현황 수정 기능 , /waiting/update.jsp
    // 조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다.
    // 조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다.
    public boolean waitingUpdate( WaitingDto waitingDto ){
        return waitingDao.waitingUpdate( waitingDto );
    } // func end

} // class end
