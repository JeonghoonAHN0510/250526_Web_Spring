package 종합.과정평가.model.dao;

import org.springframework.stereotype.Repository;
import 종합.과정평가.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    // 3. 회원목록 조회
    public ArrayList<MemberDto> memberPrint(){
        ArrayList<MemberDto> memberDtos = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select * from member_tbl_02";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while ( rs.next() ){
                // 배열에 넣을 객체 생성
                MemberDto memberDto = new MemberDto(
                        rs.getInt("custno"),
                        rs.getString("custname"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("joindate"),
                        rs.getString("grade"),
                        rs.getString("city")
                );
                // 생성한 객체 배열에 넣기
                memberDtos.add( memberDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return memberDtos;
    } // func end

    // 4. 회원정보 수정
    public int updateMember( MemberDto memberDto ){
        try {
            // 1. SQL 작성
            String SQL = "update member_tbl_02 set custname = ?, phone = ?, address = ?, joindate = ?, grade = ?, city = ? where custno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, memberDto.getCustname() );
            ps.setString( 2, memberDto.getPhone() );
            ps.setString( 3, memberDto.getAddress() );
            ps.setString( 4, memberDto.getJoindate() );
            ps.setString( 5, memberDto.getGrade() );
            ps.setString( 6, memberDto.getCity() );
            ps.setInt( 7, memberDto.getCustno() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return 0;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 1;
    } // func end

    // 5. 선택 회원정보 출력
    public MemberDto memberPrintOne( int custno ){
        try {
            // 1. SQL 작성
            String SQL = "select * from member_tbl_02 where custno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, custno );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            if ( rs.next() ){
                // 반환할 객체 생성
                MemberDto memberDto = new MemberDto(
                        rs.getInt("custno"),
                        rs.getString("custname"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("joindate"),
                        rs.getString("grade"),
                        rs.getString("city")
                );
                return memberDto;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return null;
    } // func end


} // class end
