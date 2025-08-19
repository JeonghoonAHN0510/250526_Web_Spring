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
    // 쿼리스트링 : fileName = fb419c88-17ff-4ae5-8bfc-65c1872f7473_logo.jpg
    public void fileDownload( @RequestParam String fileName, HttpServletResponse response ){
        System.out.println("FileController.fileDownload");
        System.out.println("fileName = " + fileName + ", response = " + response);

        fileService.fileDownload( fileName, response );
    } // func end


} // class end
