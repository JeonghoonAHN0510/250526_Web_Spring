package web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    @Autowired
    public FileController( FileService fileService ){
        this.fileService = fileService;
    } // func end

    // [1] 파일 업로드
    @PostMapping("/upload")
    // header : { "Content-Type" : "multipart/form-data" }
    // body : Form
    public String uploadFile( MultipartFile multipartFile ){
        System.out.println("FileController.uploadFile");
        System.out.println("multipartFile = " + multipartFile);

        return fileService.fileUpload( multipartFile );
    } // func end

    // [2] 파일 다운로드
    @GetMapping("/download")
    // 쿼리스트링 : fileName = 다운로드할 파일
    public void fileDownload( @RequestParam String fileName, HttpServletResponse response ){
        System.out.println("FileController.fileDownload");
        System.out.println("fileName = " + fileName + ", response = " + response);

        fileService.fileDownload( fileName, response );
    } // func end

    // [3] 파일 삭제
    @GetMapping("/delete")
    // 쿼리스트링 : fileName = 삭제할 파일
    public boolean fileDelete( @RequestParam String fileName ){
        System.out.println("FileController.fileDelete");
        System.out.println("fileName = " + fileName);

        return fileService.fileDelete( fileName );
    } // func end
} // class end
