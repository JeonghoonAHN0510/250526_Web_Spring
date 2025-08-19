package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.ProductDto;
import web.service.FileService;
import web.service.ProductService;

import java.util.List;

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
        // 1-1. 로그인 상태 확인하기 -> 세션정보가 없거나 특정한 속성값이 없으면
        if ( session == null || session.getAttribute("loginMno") == null ) return 0;
        // 1-2. 로그인 상태라면, 세션에서 로그인 번호를 꺼낸다.
        int loginMno = ( int ) session.getAttribute("loginMno");
        // 1-3. 로그인 번호를 ProductDto에 추가한다.
        productDto.setMno( loginMno );
        // 2-1. 제품정보를 DB 처리하기 -> 제품을 DB에 등록 -> 결과값(result) = 생성된 제품번호
        int result = productService.createProduct( productDto );
        // 2-2. 만약에 결과가 0이면, 아래 코드 실행 안하고 종료
        if ( result == 0 ) return 0;
        // 3. 만약에 업로드 파일이 존재하면, 업로드 서비스를 호출하여 업로드 처리하기
        // 3-1. 첨부파일이 비어있지 않고, 첨부파일 목록에서 첫번째 첨부파일이 비어있지 않으면
        if ( !productDto.getUploads().isEmpty() && !productDto.getUploads().get(0).isEmpty() ){
            List<MultipartFile> multipartFiles = productDto.getUploads();
            // 3-2. 반복문을 통해서 파일 업로드하기
            for ( MultipartFile multipartFile : multipartFiles ){
                String fileName = fileService.fileUpload( multipartFile );
                // 3-3. 만약에 파일명이 null이면, 업로드 실패이기에 0 반환
                if ( fileName == null ) return 0;
                // 3-4. 업로드 성공 시, 업로드된 파일명을 DB에 저장하기
                // result = 생성된 제품번호, fileName = 업로드된 파일명
                boolean result1 = productService.createProductImage( result, fileName );
                // 3-5. 이미지 저장실패 시, 0 반환
                if ( !result1 ) return 0;
            } // for end
        } // if end
        return result;
    } // func end

    // [2] 제품 정보 전체 조회
    @GetMapping("/list")
    public List<ProductDto> getAllProduct(){
        System.out.println("ProductController.getAllProduct");

        return productService.getAllProduct();
    } // func end

    // [3] 제품 상세 조회
    @GetMapping("/find")
    public ProductDto getProduct( @RequestParam int pno ){
        System.out.println("ProductController.getProduct");
        System.out.println("pno = " + pno);

        return productService.getProduct( pno );
    } // func end
} // class end