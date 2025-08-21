package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PaymentDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class PaymentDao extends Dao {

    // [1] 포인트 결제 진행
    public int payment( PaymentDto paymentDto ){
        try {
            String SQL = "insert into payment ( mno, pamount ) values ( ?, ? )";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setInt( 1, paymentDto.getMno() );
            ps.setInt( 2, paymentDto.getPamount() );
            if ( ps.executeUpdate() == 1 ){
                ResultSet rs = ps.getGeneratedKeys();
                if ( rs.next() ){
                    // 성공하면 자동생성된 결제번호 반환
                    return rs.getInt( 1 );
                } // if end
            } // if end
        } catch ( SQLException e ) {
            System.out.println( e );
        } // try-catch end
        // 실패하면 0 반환
        return 0;
    } // func end
} // class end