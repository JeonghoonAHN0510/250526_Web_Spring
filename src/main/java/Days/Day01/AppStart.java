package Days.Day01;

import Days.Day01.controller.BoardController;
import Days.Day01.model.dto.BoardDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // System.out.println("자바실행");
        // view를 제외한 BoardService 10 만들기
        // [view없이 기능 테스트]
        // * 등록 기능 테스트
        boolean test = BoardController.getInstance().boardWrite("테스트내용", "테스트작성자");
        System.out.println( test );     // true(성공) / false(실패)

        // * 전체조회 기능 테스트
        ArrayList<BoardDto> result = BoardController.getInstance().boardPrint();
        System.out.println( result );

        // 0. 메인화면 호출
        // BoardView.getInstance().mainPrint();

        // * Spring 환경 실행
        // SpringApplication.run( 현재클래스명.class );
        SpringApplication.run( AppStart.class );


    } // main end
} // class end
