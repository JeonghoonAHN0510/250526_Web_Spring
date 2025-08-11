package example.Day08._2MVC.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

// 중복 코드를 관리하는 슈퍼 클래스
public class Dao {
    // * DB 연동에 필요한 정보
    private String DB_URL = "jdbc:mysql://localhost:3306/spring08";
    private String DB_USER = "root";
    private String DB_PASSWORD = "1234";
    public Connection conn;         // 하위클래스에서 사용할 수 있게 public으로 선언
    // * DB 연동 생성자
    public Dao(){ DBConnect(); }
    // * DB 연동 메소드
    private void DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( DB_URL, DB_USER, DB_PASSWORD );
            System.out.println("Dao.DBConnect");
        }catch( Exception e ){
            System.out.println( e );
        } // try-catch end
    } // func end


} // class end
