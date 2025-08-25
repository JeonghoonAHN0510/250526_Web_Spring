package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PageDto;
import web.model.dto.PostDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDao extends Dao {
    // [1] 게시물 등록
    public int writePost( PostDto postDto ){
        try {
            String SQL = "insert into post ( ptitle, pcontent, mno, cno ) values ( ?, ?, ?, ? )";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1, postDto.getPtitle() );
            ps.setString( 2, postDto.getPcontent() );
            ps.setInt( 3, postDto.getMno() );
            ps.setInt( 4, postDto.getCno() );
            int count = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if ( count == 1 ){
                if ( rs.next() ) return rs.getInt( 1 );
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 0;
    } // func end

    // [2-1] 카테고리별 자료 개수 구하기
    public int getTotalCount( int cno ){
        try {
            String SQL = "select count(*) from post where cno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, cno );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                return rs.getInt( 1 );
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 0;
    } // func end

    // [2-2] 게시물 전체조회( 페이징처리 + 최신순 )
    public List< PostDto > findAllPrint( int cno, int startRow, int perCount ){
        List< PostDto > postList = new ArrayList<>();
        try {
            String SQL = "select * from post p inner join member m using ( mno ) where p.cno = ? order by p.pno desc limit ?, ?";
            // limit startRow, perCount : startRow부터 perCount 추출
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, cno );
            ps.setInt( 2, startRow );
            ps.setInt( 3, perCount );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                PostDto postDto = new PostDto();
                postDto.setPno( rs.getInt( "pno" ) );                   // 게시물 번호
                postDto.setPtitle( rs.getString( "ptitle" ) );          // 게시물 제목
                postDto.setPcontent( rs.getString( "pcontent" ) );      // 게시물 내용
                postDto.setPdate( rs.getString( "pdate" ) );            // 게시물 작성일
                postDto.setPview( rs.getInt( "pview" ) );               // 게시물 조회수
                postDto.setMno( rs.getInt( "mno" ) );                   // 게시물 작성자 번호
                postDto.setCno( rs.getInt( "cno" ) );                   // 게시물 카테고리 번호
                postDto.setMid( rs.getString( "mid" ) );                // 게시물 작성자 아이디
                postList.add( postDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return postList;
    } // func end
} // class end