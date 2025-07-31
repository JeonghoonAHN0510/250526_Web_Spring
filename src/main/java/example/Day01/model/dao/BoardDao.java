package example.Day01.model.dao;

import example.Day01.model.dto.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    // 싱글톤 만들기
    private BoardDao(){
        connectDB();
    } // func end
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance() {
        return instance;
    } // func end

    // 0. DB 연동 메소드 : Dao가 생성될 때, 실행
    private String DB_URL = "jdbc:mysql://localhost:3306/exam10";   // 연동할 DB 서버의 주소 = jdbc:mysql://IP주소:PORT번호/DB명
    private String DB_ID = "root";                                  // DB 서버의 계정명
    private String DB_PWD = "1234";                                 // DB 서버의 비밀번호
    private Connection conn;
    private void connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( DB_URL, DB_ID, DB_PWD );
        } catch (Exception e) {
            System.out.println( e );
        } // try-catch end
    } // func end

    // 1. 등록 기능 구현
    // 메소드명 : boardWrite
    // 매개변수 : BoardDto boardDto -> controller 로부터 저장할 값들을 boardDto로 받는다.
    // 반환값 : true(성공)/false(실패) -> boolean
    public boolean boardWrite(BoardDto boardDto){
        try {
            // 1. SQL 작성
            String sql = "insert into board( bcontent, bwriter ) values ( ? , ? );";
            // 2. SQL 기재 : PreparedStatement
            PreparedStatement ps = conn.prepareStatement( sql );
            // 3. SQL 매개변수 대입 : ps.setXXX()
            ps.setString( 1, boardDto.getBcontent() );      // 첫번째 ?에 매개변수로 받은 boardDto의 bcontent값 대입
            ps.setString( 2, boardDto.getBwriter() );       // 두번째 ?에 매개변수로 받은 boardDto의 bwriter값 대입
            // 4. SQL 실행 : ps.executeUpdate() / ps.executeQuery()
            int count = ps.executeUpdate();
            // 5. SQL 결과 확인 및 리턴
            if ( count == 1 ){
                return true;            // count가 1개면, 저장성공
            }else {
                return false;           // count가 1 미만이면, 저장실패
            } // if end
        } catch ( Exception e ){
            System.out.println( e );
            return false;               // 예외 발생 시, 저장실패
        } // try-catch end
    } // func end

    // 2. 전체조회 기능 구현
    // 메소드명 : boardPrint
    // 매개변수 : X
    // 반환값 : ArrayList<BoardDto>
    // 다른 패키지의 controller가 접근할 수 있게 public로 선언 / private는 현재클래스에서만 접근 가능
    public ArrayList<BoardDto> boardPrint(){
        // 조회된 레코드들을 저장할 리스트 생성(선언) -> 자바 내에 저장할 필요가 없어서, 멤버변수로 선언하지않고 지역변수로 선언한다.
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            // 1. SQL 작성
            String sql = "select * from board;";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( sql );
            // 3. SQL 매개변수 대입 -> ?가 없어서 생략
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 확인 및 리턴 -> select는 결과가 표로 나온다.
            // 5-1) 결과의 레코드/행 하나씩 조회 -> rs.next() : 결과 테이블에서 다음 레코드로 하나씩 이동
            while ( rs.next() ){
                // 5-2) 조회중인 레코드의 속성값을 호출하여 BoardDto로 만들기 -> rs.getXXX("속성명" or 속성번호)
                // -> 레코드 1개당 BoardDto 1개 생성
                BoardDto boardDto = new BoardDto( rs.getInt("bno"), rs.getString("bcontent"), rs.getString("bwriter") );
                // 5-3) 생성된 dto를 리스트에 담기
                list.add( boardDto );
            } // while end
        } catch ( Exception e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end
} // class end
