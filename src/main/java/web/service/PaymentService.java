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
        // 1. 포인트결제 DB 등록 진행
        int result = paymentDao.payment( paymentDto );
        // 2. 등록에 실패한다면 0 반환
        if ( result <= 0 ) return 0;
        // 3. 포인트로그 DB 등록 진행
        // 3-1. PointService에게 전달할 객체 생성
        PointDto pointDto = new PointDto();
        pointDto.setMno( paymentDto.getMno() );
        pointDto.setPlpoint( paymentDto.getPamount() );
        pointDto.setPlcomment( "포인트충전" );
        // 3-2. 생성한 객체를 PointService에게 전달 후 실패하면 0 반환
        if ( !pointService.addPoint( pointDto ) ) return 0;
        // 4. 성공하면 보너스 포인트 지급
        // 4-1. 보너스 포인트를 지급하기 위한 객체 수정
        pointDto.setPlpoint( (int) (paymentDto.getPamount() * 0.05) );
        pointDto.setPlcomment( "충전보너스" );
        // 4-2. 수정한 객체를 PointService에게 전달 후 실패하면 0 반환
        if ( !pointService.addPoint( pointDto ) ) return 0;
        // 5. 최종적으로 모두 성공했다면, 결제번호 반환
        return result;
    } // func end
} // class end

