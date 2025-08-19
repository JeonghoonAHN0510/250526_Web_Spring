package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PointDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PointDao extends Dao {

    // [1] 포인트 지급


    // [2] 특정 회원 포인트 지급 전체 조회
    public List<PointDto> getPointlog( int mno ){
        List<PointDto> list = new ArrayList<>();
        try {
            String SQL = "select * from pointlog where mno = ? order by pldate desc";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, mno );
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                PointDto pointDto = new PointDto();
                pointDto.setPlno( rs.getInt( "plno" ) );
                pointDto.setMno( rs.getInt( "mno" ) );
                pointDto.setPlpoint( rs.getInt( "plpoint" ) );
                pointDto.setPlcomment( rs.getString( "plcomment" ) );
                pointDto.setPldata( rs.getString( "pldate" ) );

                list.add( pointDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end

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