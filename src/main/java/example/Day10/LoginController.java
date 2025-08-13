package example.Day10;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/day10"})     // 공통 URL
public class LoginController {

    // [1] 로그인 기능
    // 아이디와 비밀번호가 일치하면 *세션*에 로그인 성공한 회원번호 저장
    @PostMapping("/login")  // 비밀번호라는 중요 데이터가 있으므로, 쿼리스트링보단 Body를 사용
    public boolean login( @RequestBody LoginDto loginDto, HttpServletRequest request ) {
        // HttpServletRequest : HTTP 요청 정보를 갖는 객체
        System.out.println( "request.getRemoteAddr() = " + request.getRemoteAddr() );                       // 통신을 요청한 클라이언트(사용자)의 IP 확인
        System.out.println( "request.getRemotePort() = " + request.getRemotePort() );                       // 통신을 요청한 클라이언트(사용자)의 Port 확인
        System.out.println( "request.getHeader = " + request.getHeader( "User-Agent" ) );                   // 통신을 요청한 클라이언트(사용자)의 브라우저 정보 확인

        // ** 세션 정보 가져오기, 세션이란? Tomcat 서버 내 저장소( Map 컬렉션 사용 )
        HttpSession session = request.getSession();
        System.out.println( "session.getId() = " + session.getId() );                                       // 세션 식별번호 : 브라우저마다, PC마다 다르게 할당된다.
        System.out.println( "session.getCreationTime() = " + session.getCreationTime() );                   // 세션 생성시간(ms) : 최초로 요청한 시간
        System.out.println( "session.getLastAccessedTime() = " + session.getLastAccessedTime() );           // 세션 마지막 접근시간(ms) / 1000ms = 1s
        System.out.println( "session.getMaxInactiveInterval() = " + session.getMaxInactiveInterval() );     // 세션의 최대 유효시간(s)

        // ** 세션 속성 추가하기, 속성이란? key-value로 구성된 메모리 공간
        session.setAttribute( "loginMno", 3 );                      // ( "속성명", 속성값 ), "loginMno"라는 속성명으로 3이라는 데이터를 저장
        System.out.println("[로그인 성공]");

        return true;
    } // func end

    // [2] 로그인된 정보 확인 : 현재 세션에 저장된 회원정보 확인
    @GetMapping("/info")
    public boolean info( HttpServletRequest request ){
        // 1. 요청 Servlet에서 세션정보 꺼내기
        HttpSession session = request.getSession();
        // 2. 세션정보 내 원하는 속성명 꺼내기, 값은 무조건 Object 타입
        Object obj = session.getAttribute( "loginMno" );
        // 비로그인 상태 표현하기 -> 세션정보가 null이면, 비로그인 상태
        if( obj == null ){
            System.out.println("[비로그인 상태]");
            return false;
        } // if end
        // 3. 꺼낸 obj 타입변환 필요
        int loginMno = ( int ) obj;
        System.out.println("[로그인 상태]");
        System.out.println("loginMno = " + loginMno);

        return true;
    } // func end

    // [3] 로그아웃 기능 : 세선정보 내 속성 제거하기
    @GetMapping("/logout")
    public boolean logout( HttpServletRequest request ){
        // 1. 세선정보 가져오기
        HttpSession session = request.getSession();
        // * 세션정보에 세션이 존재하는지 확인 후, 존재하면 뒤 진행 / 존재하지않으면 비로그인 상태 알림
        // 2.1. 특정한 속성 제거하기
        session.removeAttribute( "loginMno" );
        // 2.2. 전체 속성 제거하기
        // session.invalidate();
        System.out.println("[로그아웃 완료]");

        return true;
    } // func end
} // class end
