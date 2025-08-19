package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

@Service
public class ProductService {
    private final ProductDao productDao;
    @Autowired
    public ProductService( ProductDao productDao ) {
        this.productDao = productDao;
    } // func end

    // [1-1] 제품 등록
    public int createProduct( ProductDto productDto ){
        return productDao.createProduct( productDto );
    } // func end

    // [1-2] 제품 이미지 등록
    public boolean createProductImage( int pno, String fileName ){
        return productDao.createProductImage( pno, fileName );
    } // func end

    // [2] 제품 전체 조회


    // [3] 제품 상세 조회


} // class end