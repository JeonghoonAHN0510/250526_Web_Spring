package 종합.과정평가.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.과정평가.model.dao.MoneyDao;

@Service
public class MoneyService {
    // MoneyDao 가져오기
    final MoneyDao moneyDao;
    @Autowired
    public MoneyService(MoneyDao moneyDao) {
        this.moneyDao = moneyDao;
    } // func end





} // class end
