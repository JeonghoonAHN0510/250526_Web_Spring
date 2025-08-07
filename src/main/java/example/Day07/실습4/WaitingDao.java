package example.Day07.실습4;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaitingDao {

    // 싱글톤 만들기
    private WaitingDao(){ connect(); }
    @Getter
    private static final WaitingDao instance = new WaitingDao();

    // * DB 연동하기
    private String db_url = "jdbc:mysql://localhost:3306/exam04";
    private String db_user = "root";
    private String db_pwd = "1234";
    private Connection conn;
    private void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url, db_user, db_pwd );
        } catch ( Exception e ){
            System.out.println( e );
        } // try-catch end
    } // func end

    // 1. 등록 기능
    public boolean add( WaitingDto waitingDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into waiting( wphone, wcount ) values ( ?, ? )";
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

    // 2. 전체조회 기능
    public List<WaitingDto> print(){
        // 반환할 빈 객체 생성
        List<WaitingDto> list = new ArrayList<>();
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
        // 최종적으로 반환
        return list;
    } // func end
} // class end
