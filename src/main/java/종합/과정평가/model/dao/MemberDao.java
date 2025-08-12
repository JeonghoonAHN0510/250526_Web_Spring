package 종합.과정평가.model.dao;

import org.springframework.stereotype.Repository;
import 종합.과정평가.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberDao extends Dao{
    // 1. 회원등록기능
    public int addMember( MemberDto memberDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into member_tbl_02 ( custno, custname, phone, address, joindate, grade, city ) values ( ?, ?, ?, ?, ?, ?, ? )";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, memberDto.getCustno() );
            ps.setString( 2, memberDto.getCustname() );
            ps.setString( 3, memberDto.getPhone() );
            ps.setString( 4, memberDto.getAddress() );
            ps.setString( 5, memberDto.getJoindate() );
            ps.setString( 6, memberDto.getGrade() );
            ps.setString( 7, memberDto.getCity() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return 0;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 1;
    } // func end

    // 2. 회원번호 자동생성
    public int custnoPrint(){
        try {
            // 1. SQL 작성
            String SQL = "select Max(custno) MaxNo from member_tbl_02";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            if ( rs.next() ){
                return rs.getInt( 1 );
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 0;
    } // func end


} // class end
