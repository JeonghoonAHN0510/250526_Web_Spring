package example.Day08._2MVC.model.dao;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository // [1] Repository bean 등록
public class MvcDao extends Dao {
    // [2] 다음 레이어가 없어서 @Autowired 사용 X
    // [3] extends Dao를 통해, DB 연동을 상속받아 사용

    // [4] 기능구현 테스트
    public void method(){
        System.out.println("MvcDao.method");
        try {
            String SQL = "select * from mvc";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                System.out.println( rs.getString("var1") );
            }
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
    } // func end

} // class end
