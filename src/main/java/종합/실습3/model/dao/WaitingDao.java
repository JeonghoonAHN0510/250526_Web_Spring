package 종합.실습3.model.dao;

import 종합.실습3.model.dto.WaitingDto;

import java.sql.*;
import java.util.ArrayList;

public class WaitingDao {
    // * 싱글톤 생성
    private WaitingDao(){
        connectDB();
    } // func end
    private static final WaitingDao instance = new WaitingDao();
    public static WaitingDao getInstance() {
        return instance;
    } // func end

    // 0. DB 연동 메소드 : Dao가 생성될 때, 실행
    private String DB_URL = "jdbc:mysql://localhost:3306/assignment3";  // 연동할 DB 서버의 주소 = jdbc:mysql://IP주소:PORT번호/DB명
    private String DB_ID = "root";                                      // DB 서버의 계정명
    private String DB_PWD = "1234";                                     // DB 서버의 비밀번호
    private Connection conn;
    public void connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( DB_URL, DB_ID, DB_PWD );
        } catch ( ClassNotFoundException e ) {
            System.out.println( e );
        } catch (SQLException e ){
            System.out.println( e );
        } // try-catch end
    } // func end

    // 1. 대기 등록 기능
    // 메소드명 : waitingRegis
    // 매개변수 : WaitingDto
    // 반환값 : true(성공)/false(실패) -> boolean
    public boolean waitingRegis( WaitingDto waitingDto ){
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
            if ( count == 1 ){
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // 2. 대기현황 조회 기능
    // 메소드명 : waitingList
    // 매개변수 : X
    // 반환값 : ArrayList<WaitingDto>
    public ArrayList<WaitingDto> waitingList(){
        ArrayList<WaitingDto> waitingDtos = new ArrayList<>();  // 반환할 배열 선언
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
                int wno = rs.getInt("wno");
                String wphone = rs.getString("wphone");
                int wcount = rs.getInt("wcount");
                // 객체 생성 및 값 넣기
                WaitingDto waitingDto = new WaitingDto( wno, wphone, wcount );
                // 생성한 객체 배열에 넣기
                waitingDtos.add( waitingDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return waitingDtos;
    } // func end

    // 3. 대기 삭제 기능
    // 메소드명 : waitingDelete
    // 매개변수 : int wno
    // 반환값 : true(성공)/false(실패) -> boolean
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
            if ( count == 1 ){
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // 4. 대기 수정 기능
    // 메소드명 : waitingUpdate
    // 매개변수 : WaitingDto
    // 반환값 : true(성공)/false(실패) -> boolean
    public boolean waitingUpdate( WaitingDto waitingDto ){
        try {
            // 1. SQL 작성
            String SQL = "update waiting set wcount = ? where wno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, waitingDto.getWcount() );
            ps.setInt( 2, waitingDto.getWno() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ){
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

} // class end
