package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PostDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
} // class end