package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PointDao;

@Service
public class PointService {
    private final PointDao pointDao;
    @Autowired
    public PointService( PointDao pointDao ){
        this.pointDao = pointDao;
    } // func end

    // [1] 포인트 지급


    // [2] 포인트 지급 전체 조회


    // [3] 특정 회원 포인트 합계 조회
    public int memberPoint( int mno ){
        return pointDao.memberPoint(mno);
    } // func end
} // class end