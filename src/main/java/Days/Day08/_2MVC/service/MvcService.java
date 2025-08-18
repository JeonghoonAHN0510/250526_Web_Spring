package Days.Day08._2MVC.service;

import Days.Day08._2MVC.model.dao.MvcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // [1] Service bean 등록
public class MvcService {

    // [2] Repository bean을 받는다.
    @Autowired
    private MvcDao mvcDao;

    // [3] 기능구현 테스트
    public void method(){
        System.out.println("MvcService.method");
        // Repository의 메소드 호출
        mvcDao.method();
    } // func end


} // class end
