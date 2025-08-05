package 종합.예제11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 내장 톰캣 실행 + 컴포넌트 스캔
public class AppStart {
    public static void main(String[] args) {

        // View 없이 Spring 실행 : @SpringBootApplication + SpringApplication.run( 현재클래스.class )
        // -> 실행하면 프로젝트 내 resources 폴더의 HTML/CSS/JS 등의 프론트엔드를 등록한다.
        SpringApplication.run( AppStart.class );

    } // main end
} // class end
