package web.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Service    // 해당 클래스를 스프링 컨테이너에 bean 등록
public class FileService {

    // [0] 업로드 경로
    // 방법1) 프로젝트 내 src   : 개발자가 코드를 작성하는 폴더
    // 방법2) 프로젝트 내 build : 서버가 실행될 때, 컴파일된 코드가 저장되는 폴더
    // 1. 현재 프로젝트의 최상위 폴더(디렉터리) 경로 찾기
    private String baseDir = System.getProperty("user.dir");
    // 2. build 폴더로 업로드 경로 지정하기 -> 방법2 사용
    private String uploadPath = baseDir + "/build/resources/main/static/upload/";

    // [1] 파일 업로드 -> 스프링에서 MultipartFile 인터페이스 지원( 대용량 바이트 조작 )
    public String fileUpload( MultipartFile multipartFile ){
        // * 업로드한 파일명이 중복일 경우
        // 방법1) 파일명 앞에 PK를 붙인다.
        // 방법2) 파일명 앞에 업로드된 날짜/시간을 붙인다.
        // 방법3) UUID(네트워크 식별번호)를 사용한다. (난수 문자열 생성 라이브러리 -> 고유성 보장)

        // 1. UUID 생성하기 -> 방법3 사용
        String uuid = UUID.randomUUID().toString();

        // 2. 업로드된 파일명과 UUID 합치기
        // .getOriginalFilename() : 업로드된 파일명
        // .replaceAll("_", "-")  : uuid와 파일명을 구분하기 위해 _를 사용하는데, 파일명에 _가 존재한다면, 모두 -로 변경
        // 예상 : UUID_파일명-1.jpg
        String fileName = uuid + "_" + multipartFile.getOriginalFilename().replaceAll("_", "-");

        // 3. 업로드 경로와 파일명 합치기
        // 예상 : /build/resources/main/static/upload/UUID_파일명-1.jpg
        String uploadFilePath = uploadPath + fileName;

        // 4. 만약에 업로드한 경로에 upload 폴더가 존재하지 않으면, 폴더 생성
        // File 클래스 : 다양한 파일/폴더 함수 제공
        File pathFile = new File( uploadPath );
        if ( !pathFile.exists() ){  // .exists() : 지정한 경로가 존재하면 true, 존재하지 않으면 false 반환
            pathFile.mkdirs();      // .mkdirs() : 지정한 경로 생성 메소드
        } // if end

        // 5. 업로드할 파일의 경로의 File 클래스 생성
        File uploadFile = new File( uploadFilePath );

        // 6. 업로드 하기
        // multipartFile.transferTo( file 객체 ) : 지정한 file 객체의 경로로 업로드 파일을 이동, 일반예외 발생
        try {
            multipartFile.transferTo( uploadFile );
        } catch ( IOException e ) {
            System.out.println( e );
            // 7. 업로드 실패 시, null 반환
            return null;
        } // try-catch end

        // 7. 업로드 성공 시, 파일이름 반환
        return fileName;
    } // func end

    // [2] 파일 다운로드
    public void fileDownload( String fileName, HttpServletResponse response ){
        // String fileName : 다운로드받을 파일명
        // HttpServletResponse response : 다운로드를 요청한 사용자의 응답 객체

        // 1. 다운로드 받을 파일명과 업로드 경로 합치기
        String downloadPath = uploadPath + fileName;

        // 2. 만약에 지정한 경로에 파일이 없으면, 메소드 끝내기
        File file = new File( downloadPath );
        if ( !file.exists() ) return;

        try {
            // 3. 다운로드할 파일을 자바(바이트)로 가져오기(파일 읽기)
            // 3-1. 파일 입력스트림 객체 생성
            FileInputStream fin = new FileInputStream( downloadPath );
            // 3-2. 파일 용량만큼 배열 생성 -> 읽어온 바이트를 저장할 배열 생성
            long fileSize = file.length();              // 파일 용량
            byte[] bytes = new byte[ (int) fileSize ];    // 파일 용량만큼 바이트 배열 생성
            // 3-3. 파일을 읽어와서 배열에 저장
            fin.read( bytes );
            // 3-4. 안전하게 스트림 닫기, 스트림이란? 바이트가 다니는 통신 경로
            fin.close();    // 필수는 아니지만, 대용량에서는 직접 닫는 것을 권장!

            //========================================= 다운로드 형식 지정 =======================================\\
            // 1. 파일의 uuid 제거하기, 필수는 아님
            // .split("문자") : 문자열을 '문자' 기준으로 자르기
            String oldFilename = fileName.split("_")[1];    // 파일명을 '_' 기준으로 잘라서 2번째 문자열을 반환받는다.

            // 2. 응답 형식 지정하기 -> 다운로드 형식과 다운로드 파일 지정
            // URL은 한글을 지원하지않기 때문에, URLEncoder.encode( 파일명, "UTF-8")이 필요
            response.setHeader( "Content-Disposition", "attachment; filename=" + URLEncoder.encode( oldFilename, "UTF-8") );
            //=================================================================================================\\

            // 4. 가져온 파일을 사용자에게 보내기
            // 4-1. 파일 출력스트림 객체 생성, Servlet이란? HTTP 요청/응답 클래스
            ServletOutputStream fout = response.getOutputStream();
            // 4-2. 읽어온 바이트배열을 사용자에게 출력하기
            fout.write( bytes );
            // 4-3. 안전하게 스트림 닫기
            fout.close();
        } catch ( Exception e ) {
            System.out.println( e );
        } // try-catch end
    } // func end

    // [3] 파일 삭제


} // class end
