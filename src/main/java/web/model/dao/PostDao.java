package web.model.dao;

import org.springframework.stereotype.Repository;
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

    // [2-2] 검색에 따른 카테고리별 자료 개수 구하기
    public int getTotalCountSearch( int cno, String key, String keyword ){
        try {
            String SQL = "select count(*) from post where cno = ? and ";
         // String SQL = "select count(*) from post where cno = ? and " + key + " like ?";
            // key에 따른 동적 SQL 작성 -> 그 외의 속성이 있다면, 추가하기
            if ( key.equals("ptitle") ){
                SQL += " ptitle like ?";
            } else if ( key.equals("pcontent") ){
                SQL += " pcotent like ?";
            } // if end
            System.out.println("SQL = " + SQL);     // 동적 SQL 확인
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, cno );
            ps.setString( 2, "%" + keyword + "%" );     // %는 .setXXX에서 추가하기
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                return rs.getInt( 1 );
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 0;
    } // func end

    // [2-3] 게시물 전체조회( 페이징처리 + 최신순 )
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

    // [2-4] 검색에 따른 게시물 조회( 페이징처리 + 최신순 )
    public List< PostDto > findAllSearch( int cno, int startRow, int perCount, String key, String keyword ){
        List< PostDto > postList = new ArrayList<>();
        try {
            String SQL = "select * from post p inner join member m using ( mno ) where p.cno = ? ";
            // key에 따른 동적 SQL 작성
            if ( key.equals("ptitle") ){
                SQL += " and ptitle like ? ";
            } else if ( key.equals("pcontent") ){
                SQL += " and pcontent like ? ";
            } // if end
            SQL += " order by p.pno desc limit ?, ?";
            System.out.println("SQL = " + SQL);     // 동적 SQL 확인
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, cno );
            ps.setString( 2, "%" + keyword + "%" );
            ps.setInt( 3, startRow );
            ps.setInt( 4, perCount );
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

    // [3-1] 게시물 개별조회
    public PostDto getPost( int pno ){
        try {
            String SQL = "select * from post p inner join member m using ( mno ) where p.pno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, pno );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ){
                PostDto postDto = new PostDto();
                postDto.setPno( rs.getInt( "pno" ) );                   // 게시물 번호
                postDto.setPtitle( rs.getString( "ptitle" ) );          // 게시물 제목
                postDto.setPcontent( rs.getString( "pcontent" ) );      // 게시물 내용
                postDto.setPdate( rs.getString( "pdate" ) );            // 게시물 작성일
                postDto.setPview( rs.getInt( "pview" ) );               // 게시물 조회수
                postDto.setMno( rs.getInt( "mno" ) );                   // 게시물 작성자 번호
                postDto.setCno( rs.getInt( "cno" ) );                   // 게시물 카테고리 번호
                postDto.setMid( rs.getString( "mid" ) );                // 게시물 작성자 아이디
                return postDto;
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return null;
    } // func end

    // [3-2] 게시물 조회수 증가
    public void incrementView( int pno ){
        try {
            String SQL = "update post set pview = pview + 1 where pno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, pno );
            ps.executeUpdate();
            // void라서 반환값 X
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
    } // func end

    // [4] 게시물 삭제
    public boolean deletePost( int pno ){
        try {
            String SQL = "delete from post where pno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, pno );
            return ps.executeUpdate() == 1;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // [5] 게시물 수정
    public int updatePost( PostDto postDto ){
        try {
            String SQL = "update post set ptitle = ?, pcontent = ? where pno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, postDto.getPtitle() );
            ps.setString( 2, postDto.getPcontent() );
            ps.setInt( 3, postDto.getPno() );
            int count = ps.executeUpdate();
            if ( count == 1 ) return postDto.getPno();
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 0;
    } // func end
} // class end