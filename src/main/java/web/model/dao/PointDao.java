package web.model.dao;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PointDao extends Dao {

    // [1] 포인트 지급


    // [2] 포인트 지급 전체 조회


    // [3] 특정 회원 포인트 합계 조회
    public int memberPoint( int mno ){
        try {
            String SQL = "select sum(plpoint) AS totalpoint from pointlog where mno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, mno );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                return rs.getInt( "totalpoint" );
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 0;
    } // func end
} // class end