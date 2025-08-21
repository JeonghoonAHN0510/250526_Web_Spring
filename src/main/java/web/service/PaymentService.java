package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PaymentDao;
import web.model.dto.PaymentDto;
import web.model.dto.PointDto;

@Service
public class PaymentService {
    private final PaymentDao paymentDao;
    private final PointService pointService;
    @Autowired
    public PaymentService( PaymentDao paymentDao, PointService pointService ) {
        this.paymentDao = paymentDao;
        this.pointService = pointService;
    } // func end

    // [1] 포인트 결제 진행
    public int payment( PaymentDto paymentDto ){
        // 1. 만약에 포인트결제 DB 등록이 성공한다면
        int result = paymentDao.payment( paymentDto );
        if ( result > 0 ){
            // 2. 포인트로그 DB 등록 진행
            // 2-1. PointService에게 전달할 객체 생성
            PointDto pointDto = new PointDto();
            pointDto.setMno( paymentDto.getMno() );
            pointDto.setPlpoint( paymentDto.getPamount() );
            pointDto.setPlcomment( "포인트충전" );
            // 2-2. 생성한 객체를 Service에게 전달 후 결과 반환
            if ( pointService.addPoint( pointDto ) ){
                // 3-1. 성공하면 결제번호 반환
                return result;
            } else {
                // 3-2. 실패하면 0 반환
                return 0;
            } // if end
        } else {
            // 2-3. 포인트결제 DB등록 실패하면 0 반환
            return 0;
        } // if end
    } // func end
} // class end