package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.ProductDto;
import web.service.FileService;
import web.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    // 업로드할 때 필요한 FileService 가져오기
    private final FileService fileService;
    @Autowired
    public ProductController ( ProductService productService, FileService fileService ) {
        this.productService = productService;
        this.fileService = fileService;
    } // func end

    // [1] 제품 등록
    @PostMapping("/create")
    public int createProduct( ProductDto productDto, HttpSession session ){
        // 1. 로그인 상태 확인하기

        // 2. 제품정보를 DB 처리하기

        // 3. 만약에 업로드 파일이 존재하면, 업로드 서비스를 호출하여 업로드 처리하기

        // 4. 처리된 업로드 파일을 DB 처리하기

    } // func end

    // [2] 제품 전체 조회


    // [3] 제품 상세 조회


} // class end




