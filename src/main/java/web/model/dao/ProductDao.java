package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao extends Dao {

    // [1-1] 제품 등록
    public int createProduct( ProductDto productDto ){
        try {
            String SQL = "insert into product( pname, pprice, pcomment, plat, plng, mno ) values ( ?, ?, ?, ?, ?, ? )";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            // 자동 생성된 PK값 반환받기
            ps.setString( 1, productDto.getPname() );
            ps.setDouble( 2, productDto.getPprice() );
            ps.setString( 3, productDto.getPcomment() );
            ps.setDouble( 4, productDto.getPlat() );
            ps.setDouble( 5, productDto.getPlng() );
            ps.setInt( 6, productDto.getMno() );
            int count = ps.executeUpdate();
            if ( count == 1 ){
                // 등록된 레코드의 PK값 조회
                ResultSet rs = ps.getGeneratedKeys();
                if ( rs.next() ) return rs.getInt( 1 ); // 생성된 PK값 반환
            } // if end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return 0;
    } // func end

    // [1-2] 제품 이미지 등록
    public boolean createProductImage( int pno, String fileName ){
        try {
            String SQL = "insert into productimg ( pimgname, pno ) values ( ?, ? )";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, fileName );
            ps.setInt( 2, pno );
            return ps.executeUpdate() == 1;
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return false;
    } // func end

    // [2-1] 제품 정보 전체 조회
    public List<ProductDto> getAllProduct(){
        List<ProductDto> list = new ArrayList<>();
        try {
            String SQL = "select * from product";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                ProductDto productDto = new ProductDto();
                productDto.setPno( rs.getInt( "pno" ) );
                productDto.setPname( rs.getString( "pname" ) );
                productDto.setPprice( rs.getInt( "pprice" ) );
                productDto.setPcomment( rs.getString( "pcomment" ) );
                productDto.setPdate( rs.getString( "pdate" ) );
                productDto.setPlat( rs.getDouble( "plat" ) );
                productDto.setPlng( rs.getDouble( "plng" ) );
                productDto.setMno( rs.getInt( "mno" ) );

                list.add( productDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end

    // [2-2] 특정 제품의 이미지명 전체 조회
    public List<String> getProductImageName( int pno ){
        List<String> list = new ArrayList<>();
        try {
            String SQL = "select * from productimg where pno = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, pno );
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                list.add( rs.getString( "pimgname" ) );
            } // func end
        } catch ( SQLException e ){
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end


    // [3] 제품 상세 조회


} // class end