package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PointDao;
import web.model.dto.PointDto;

import java.util.List;

@Service
public class PointService {
    private final PointDao pointDao;
    @Autowired
    public PointService( PointDao pointDao ){
        this.pointDao = pointDao;
    } // func end

    // [1] 포인트 지급
    // true : 지급성공, false : 지급실패
    public boolean addPoint( PointDto pointDto ){
        return pointDao.addPoint( pointDto );
    } // func end

    // [2] 특정 회원 포인트 지급 전체 조회
    public List<PointDto> getPointlog( int mno ){
        return pointDao.getPointlog( mno );
    } // func end

    // [3] 특정 회원 포인트 합계 조회
    public int memberPoint( int mno ){
        return pointDao.memberPoint( mno );
    } // func end
} // class end