package example.Day07;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    // 싱글톤 만들기
    @Getter     // 해당하는 멤버변수에만 getter를 제공받을 수 있다.
    private static final StudentDao instance = new StudentDao();
    private StudentDao(){ connect(); }

    // * DB 연동하기
    private String db_url = "jdbc:mysql://localhost:3306/spring07";
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

    // 1. 저장 기능 > Controller로부터 StudentDto를 받아서 boolean으로 반환한다.
    public boolean save( StudentDto studentDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into student( sname, skor, smath ) values ( ?, ?, ? )";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, studentDto.getSname() );
            ps.setInt( 2, studentDto.getSkor() );
            ps.setInt( 3, studentDto.getSmath() );
            // 4. SQL 실행 : select > executeQuery(), insert/update/delete > executeUpdate()
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return true;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // 2. 전체조회 기능
    public List<StudentDto> find(){
        // 반환할 List 생성
        List<StudentDto> list = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select * from student";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환 : rs.next() > SQL 조회 결과 중 다음 레코드로 이동하는 메소드
            // 하나 조회 : if( rs.next() )
            // 다수 조회 : while( rs.next() )
            while( rs.next() ){
                // List에 넣을 객체 하나 생성하기
                StudentDto studentDto = new StudentDto(
                        rs.getInt( 1 ),         // 현재 조회중인 레코드의 첫번째 속성
                        rs.getString("sname"),  // 현재 조회중인 레코드의 "sname" 속성
                        rs.getInt( 3 ),
                        rs.getInt( 4 ),
                        rs.getString( 5 )
                ); // 생성자 end
                // 생성한 객체를 List에 추가하기ㅣ
                list.add( studentDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        // 최종적으로 반환
        return list;
    } // func end
} // class end
