package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductService productService;
    @Autowired
    public ProductService(ProductService productService) {
        this.productService = productService;
    } // func end

    // [1] 제품 등록


    // [2] 제품 전체 조회


    // [3] 제품 상세 조회


} // class end