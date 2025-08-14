package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

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

    // [member04] 내정보 조회 기능 - info
    public MemberDto info( int mno ){
        try {
            String SQL = "select * from member where mno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, mno );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                // *비밀번호를 제외한* 모두를 조회하여 객체 생성
                MemberDto memberDto = new MemberDto(
                        mno,
                        rs.getString("mid"),
                        "",
                        rs.getString("mname"),
                        rs.getString("mphone"),
                        rs.getString("mdate")
                );
                // 생성한 객체 반환
                return memberDto;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        // 조회 실패 시, null 반환
        return null;
    } // func end

    // [member05] 중복검사 기능 - check
    // 특정한 속성의 값 중복 확인
    // mid나 mname의 중복을 확인하고 싶을 때, 사용 -> 속성명은 ? 지원 X
    public boolean check( String type, String data ){
        try {
            String SQL = "select * from member where " + type + " = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, data );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                // 중복이면 true
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        // 중복이 아니면 false
        return false;
    } // func end

    // [member06] 회원정보수정 기능 - update
    public boolean update( MemberDto memberDto ){
        try {
            String SQL = "update member set mname = ?, mphone = ? where mno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, memberDto.getMname() );
            ps.setString( 2, memberDto.getMphone() );
            ps.setInt( 3, memberDto.getMno() );
            int count = ps.executeUpdate();
            if ( count == 1 ){
                // 수정에 성공하면
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        // 수정에 실패하면
        return false;
    } // func end

    // [member07] 비밀번호수정 기능 - updatePassword
    public boolean updatePassword( int mno, Map< String, String > map ){
        try {
            String SQL = "update member set mpwd = ? where mno = ? and mpwd = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, map.get( "newPwd" ) );     // 신규 비밀번호
            ps.setInt( 2, mno );
            ps.setString( 3, map.get( "oldPwd" ) );     // 기존 비밀번호
            int count = ps.executeUpdate();
            if ( count == 1 ){
                // 수정에 성공하면
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        // 수정에 실패하면
        return false;
    } // func end

    // [member08] 회원탈퇴 기능 - delete
    public boolean delete( MemberDto memberDto ){
        try {
            String SQL = "delete from member where mno = ? and mpwd = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, memberDto.getMno() );
            ps.setString( 2, memberDto.getMpwd() );
            // return ps.executeUpdate() == 1;
            int count = ps.executeUpdate();
            if ( count == 1 ){
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // [member09] 아이디 찾기 - findId
    // 이름 + 연락처를 입력받아, 일치 시 아이디를 반환한다.
    public MemberDto findId( MemberDto memberDto ){
        try {
            String SQL = "select mid from member where mname = ? and mphone = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, memberDto.getMname() );
            ps.setString( 2, memberDto.getMphone() );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                memberDto.setMid( rs.getString( "mid" ) );
                return memberDto;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return null;
    } // func end

    // [member10] 비밀번호 찾기 - findPwd
    // 아이디 + 연락처를 입력받아, 일치확인. -> 0이면 불일치, 1이면 일치
    public MemberDto findPwd( MemberDto memberDto ){
        try {
            String SQL = "select count(*) from member where mid = ? and mphone = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, memberDto.getMid() );
            ps.setString( 2, memberDto.getMphone() );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                // 일치한다면 객체 그대로 반환
                if ( rs.getInt( 1 ) == 1 ){
                    return memberDto;
                } // if end
            } // func end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return null;
    } // func end

    // [member11] 난수 생성 - createPwd
    // 새로운 난수 비밀번호 생성 후 반환하고, 생성된 비밀번호를 DB에 업데이트한다.
    public MemberDto createPwd( MemberDto memberDto ){
        try {
            String SQL = "update member set mpwd = ? where mid = ? and mphone = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, memberDto.getMpwd() );
            ps.setString( 2, memberDto.getMid() );
            ps.setString( 3, memberDto.getMphone() );
            int count = ps.executeUpdate();
            if ( count == 1 ){
                return memberDto;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return null;
    } // func end
} // class end
