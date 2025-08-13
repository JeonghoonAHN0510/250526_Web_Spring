package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository    // 스프링 컨테이너에 객체 등록
public class MemberDao extends Dao {
    // [member01] 회원가입 기능 - signup
    public int signup( MemberDto memberDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into member ( mid, mpwd, mname, mphone ) values ( ?, ?, ?, ? )";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            // Statement.RETURN_GENERATED_KEYS : auto_increment한 key 가져오기
            // 3. SQL 매개변수 대입
            ps.setString( 1, memberDto.getMid() );
            ps.setString( 2, memberDto.getMpwd() );
            ps.setString( 3, memberDto.getMname() );
            ps.setString( 4, memberDto.getMphone() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ){
                // * 자동할당된 pk값 반환하여 rs로 조작
                ResultSet rs = ps.getGeneratedKeys();
                if ( rs.next() ){
                    // * 방금 생성된 값 반환받기 + 성공 시, 회원가입한 회원번호 반환
                    return rs.getInt( 1 );
                } // if end
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
            System.out.println("[member01] SQL 기재 실패");
        } // try-catch end
        // 회원가입 실패 시, 0 반환
        return 0;
    } // func end

    // [member02] 로그인 기능 - login
    public int login( MemberDto memberDto ){
        try {
            // 1. SQL 작성
            String SQL = "select mno from member where mid = ? and mpwd = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, memberDto.getMid() );
            ps.setString( 2, memberDto.getMpwd() );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            if ( rs.next() ){
                // 로그인 성공 시, 조회한 회원번호 반환
                return rs.getInt( "mno" );
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        // 로그인 실패 시, 0 반환
        return 0;
    } // func end

    // [member04] 내정보 확인 기능 - info
    public MemberDto info( int mno ){

        return null;
    } // func end
} // class end
