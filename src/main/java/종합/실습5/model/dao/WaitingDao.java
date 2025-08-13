package 종합.실습5.model.dao;

import org.springframework.stereotype.Repository;
import 종합.실습5.model.dto.WaitingDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class WaitingDao extends Dao {

    // 1. 대기 현황 등록 기능
    // 조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.
    public boolean waitingAdd( WaitingDto waitingDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into waiting ( wphone, wcount ) values ( ? , ? )";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, waitingDto.getWphone() );
            ps.setInt( 2, waitingDto.getWcount() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return true;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // 2. 대기 현황 전체 조회 기능 , /waiting/list.jsp
    // 조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public ArrayList<WaitingDto> waitingPrintAll(){
        ArrayList<WaitingDto> list = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select * from waiting";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // 리스트에 넣을 객체 생성
                WaitingDto waitingDto = new WaitingDto(
                        rs.getInt("wno"),
                        rs.getString("wphone"),
                        rs.getInt("wcount"),
                        rs.getString("wdate")
                );
                // 생성한 객체 리스트에 넣기
                list.add( waitingDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end

    // 3. 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
    // 조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public WaitingDto waitingPrint( int wno ){
        try {
            // 1. SQL 작성
            String SQL = "select * from waiting where wno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, wno );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            if ( rs.next() ){
                WaitingDto waitingDto = new WaitingDto(
                        rs.getInt("wno"),
                        rs.getString("wphone"),
                        rs.getInt("wcount"),
                        rs.getString("wdate")
                );
                return waitingDto;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return null;
    } // func end

    // 4. 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
    // 조건 : 선택한 대기번호의 정보를 삭제합니다.
    public boolean waitingDelete( int wno ){
        try {
            // 1. SQL 작성
            String SQL = "delete from waiting where wno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, wno );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return true;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // 5. 특정 대기 현황 수정 기능 , /waiting/update.jsp
    // 조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다.
    // 조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다.
    public boolean waitingUpdate( WaitingDto waitingDto ){
        try {
            // 1. SQL 작성
            String SQL = "update waiting set wphone = ? , wcount = ? where wno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, waitingDto.getWphone() );
            ps.setInt( 2, waitingDto.getWcount() );
            ps.setInt( 3, waitingDto.getWno() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return true;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end
} // class end
