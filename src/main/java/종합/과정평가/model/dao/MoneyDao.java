package 종합.과정평가.model.dao;

import org.springframework.stereotype.Repository;
import 종합.과정평가.model.dto.MoneyDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class MoneyDao extends Dao{

    // 1. 회원매출조회
    public ArrayList<MoneyDto> monryPrint(){
        ArrayList<MoneyDto> list = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select A.custno, A.custname, A.grade, sum(B.price) totalprice from member_tbl_02 A join money_tbl_02 B using(custno) group by custno order by totalprice desc";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // 배열에 넣을 객체 생성
                MoneyDto moneyDto = new MoneyDto(
                        rs.getInt("custno"),
                        rs.getString("custname"),
                        rs.getString("grade"),
                        rs.getInt("totalprice")
                );
                // 생성한 객체 배열에 넣기
                list.add(moneyDto);
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end


} // class end
