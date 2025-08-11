package 종합.예제12.model.dao;

import org.springframework.stereotype.Repository;
import 종합.예제12.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class BoardDao extends Dao {
    // 1. 게시물 등록 메소드
    public boolean boardWrite( BoardDto boardDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into board ( bcontent, bwriter ) values ( ?, ? )";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, boardDto.getBcontent() );
            ps.setString( 2, boardDto.getBwriter() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return true;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // 2. 게시물 전체조회
    public ArrayList<BoardDto> boardPrint( ){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            // 1. SQL 작성
            String SQL = "select * from board";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // 배열에 넣을 객체 생성
                BoardDto boardDto = new BoardDto(
                        rs.getInt("bno"),
                        rs.getString("bcontent"),
                        rs.getString("bwriter")
                );
                // 배열에 객체 추가하기
                list.add( boardDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end

    // 3. 게시물 단일조회
    public BoardDto boardFind( int bno ){
        BoardDto boardDto = new BoardDto();
        try {
            // 1. SQL 작성
            String SQL = "select * from board where bno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, bno );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            if ( rs.next() ){
                boardDto = new BoardDto(
                        rs.getInt("bno"),
                        rs.getString("bcontent"),
                        rs.getString("bwriter")
                );
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return boardDto;
    } // func end

    // 4. 게시물 삭제
    public boolean boardDelete( int bno ){
        try {
            // 1. SQL 작성
            String SQL = "delete from board where bno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, bno );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ) return true;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // 5. 게시물 수정
    public boolean boardUpdate( BoardDto boardDto ){
        try {
            // 1. SQL 작성
            String SQL = "update board set bcontent = ? where bno = ?";
            // 2. SQL 기재
            PreparedStatement ps =  conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, boardDto.getBcontent() );
            ps.setInt( 2, boardDto.getBno() );
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
