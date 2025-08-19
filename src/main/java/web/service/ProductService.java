package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.List;

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

    // [2] 제품 정보 전체 조회( 정보 + 이미지명 )
    public List<ProductDto> getAllProduct(){
        // 1. 모든 제품 정보 조회
        List<ProductDto> productDtos = productDao.getAllProduct();
        // 2. 제품 이미지명 조회
        // 2-1. 반복문을 통해 모든 제품 Dto 순회
        for( ProductDto productDto : productDtos ){
            // 2-2. 특정한 제품의 pno를 이용하여, 제품 이미지명 조회
            List<String> images = productDao.getProductImageName( productDto.getPno() );
            // 2-3. 조회된 이미지명을 특정 제품의 Dto에 포함시키기
            productDto.setImages( images );
        } // for end
        // 3. 이미지명이 포함된 결과 반환
        return productDtos;
    } // func end

    // [3] 제품 상세 조회
    public ProductDto getProduct( int pno ){
        // 1-1. 특정 제품 정보 조회
        ProductDto productDto = productDao.getProduct( pno );
        // 1-2. 만약에 조회됐으면
        if ( productDto != null ){
            // 2. 특정 제품 이미지명 조회
            List<String> images = productDao.getProductImageName( pno );
            // 3. 조회된 이미지명을 특정 제품의 Dto에 포함시키기
            productDto.setImages( images );
        } // if end
        // 4. 이미지명이 포함된 결과 반환
        return productDto;
    } // func end
} // class end