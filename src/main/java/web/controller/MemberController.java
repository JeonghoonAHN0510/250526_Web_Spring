package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

import java.util.Map;
import java.util.Random;

@RestController    // 스프링 컨테이너에 객체 등록
@RequestMapping("/member")
public class MemberController {
    // * MemberService 가져오기
    private final MemberService memberService;
    @Autowired  // 스프링 컨테이너에 등록된 빈 주입받기
    public MemberController( MemberService memberService ){
        this.memberService = memberService;
    } // func end

    // [member01] 회원가입 기능 - signup
    @PostMapping("/signup")
    public int signup( @RequestBody MemberDto memberDto ){
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);

        return memberService.signup( memberDto );
    } // func end

    // [member02] 로그인 기능 - login
    @PostMapping("/login")  // 비밀번호를 보여주지않기 위해서, POST
    public int login(@RequestBody MemberDto memberDto, HttpServletRequest request ){
        System.out.println("MemberController.login");
        System.out.println("memberDto = " + memberDto);
        // 0. 로그인한 회원번호 가져오기
        int mno = memberService.login( memberDto );
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2. 로그인을 성공했다면
        if ( mno > 0 ){
            // 3. 세션 속성 추가하기
            session.setAttribute( "loginMno" , mno );
            System.out.println("[로그인 성공]");
        } // if end
        // 4. 결과 반환하기
        return mno;
    } // func end

    // [member03] 로그아웃 기능 - logout
    @GetMapping("/logout")
    public boolean logout( HttpServletRequest request ){
        System.out.println("MemberController.logout");
        // 1. 세션정보 가져오기
        HttpSession session = request.getSession();
        // 2. 세션정보가 없거나 특정한 속성값이 없으면(=유효성 검사)
        if ( !checkSession( session ) ) return false;
        // 3. 로그인 상태라면, 속성값 제거
        session.removeAttribute( "loginMno" );
        // 4. 로그아웃 성공 반환
        System.out.println("[로그아웃 성공]");
        return true;
    } // func end

    // [member04] 내정보 조회 기능 - info
    @GetMapping("/info")
    public MemberDto info( HttpServletRequest request ){
        System.out.println("MemberController.info");
        // 1. 세션정보 가져오기
        HttpSession session = request.getSession();
        // 2. 세션정보가 없거나 특정한 속성값이 없으면(= 유효성 검사)
        if ( !checkSession( session ) ) return null;
        // 3. 로그인 상태라면, mno 가져오기
        int mno = ( int )session.getAttribute("loginMno");
        System.out.println("[로그인 mno = " + mno + "]");
        // 4. 로그인한 mno를 Service에게 전달하여 결과 반환하기
        return memberService.info( mno );
    } // func end

    // [member05] 중복검사 기능 - check
    // true : 중복 존재, false : 중복 없음
    @GetMapping("/check")
    public boolean check( @RequestParam String type, @RequestParam String data ){
        System.out.println("MemberController.check");

        return memberService.check( type, data );
    } // func end

    // [member06] 회원정보수정 기능 - update
    @PutMapping("/update")
    public boolean update( @RequestBody MemberDto memberDto, HttpServletRequest request ){
        System.out.println("MemberController.update");
        // 1. 세션정보 가져오기
        HttpSession session = request.getSession();
        // 2. 세션정보 유효성 검사
        if ( !checkSession( session ) ) return false;
        // 3. 로그인 상태라면, mno
        int mno = ( int ) session.getAttribute("loginMno");
        // 4. 로그인 중인 mno를 입력받은 객체에 넣기
        memberDto.setMno( mno );
        // 5. 객체를 Service에게 전달 후 결과 반환하기
        System.out.println("[회원정보수정 성공]");
        return memberService.update( memberDto );
    } // func end

    // [member07] 비밀번호수정 기능 - updatePassword
    @PutMapping("/update/password")
    public boolean updatePassword( @RequestBody Map< String, String > map, HttpServletRequest request ){
        System.out.println("MemberController.updatePassword");
        // 1. 세션정보 가져오기
        HttpSession session = request.getSession();
        // 2. 세션정보 유효성 검사
        if ( !checkSession( session ) ) return false;
        // 3. 로그인 상태라면, mno 가져오기
        int mno = ( int )session.getAttribute("loginMno");
        // 4. Service에게 전달 후 결과 반환하기
        boolean result = memberService.updatePassword( mno, map );
        // 5. 세션 초기화
        if ( result ) session.invalidate();
        return result;
    } // func end

    // [member08] 회원탈퇴 기능 - delete
    @DeleteMapping("/delete")
    public boolean delete( @RequestParam String mpwd, HttpSession session ){
        System.out.println("MemberController.delete");
        // 1. 세션정보 가져오기 -> 매개변수에서 바로 가져올 수 있다.
        // 2. 세션정보 유효성 검사
        if ( !checkSession( session ) ) return false;
        // 3. 로그인 상태라면, mno 가져오기
        int mno = ( int ) session.getAttribute("loginMno");
        // 4. Service에게 전달할 객체 생성
        MemberDto memberDto = new MemberDto();
        memberDto.setMno( mno );
        memberDto.setMpwd( mpwd );
        // 5. 세션 초기화
        session.invalidate();
        // 6. Service에게 전달 후, 결과 반환하기
        return memberService.delete( memberDto );
    } // func end

    // [member00] 세션정보 유효성검사 - checkSession
    public boolean checkSession( HttpSession session ){
        System.out.println("MemberController.checkSession");
        // 1. 세션정보 유효성검사 -> 세션정보가 없거나 특정한 속성값이 없으면
        if ( session == null || session.getAttribute( "loginMno" ) == null ){
            System.out.println("[로그인 정보 없음]");
            // 2. false 반환
            return false;
        } // if end
        // 3. 로그인 정보가 있으면 true 반환
        return true;
    } // func end

    // [member09] 아이디 찾기 - findId
    // 이름 + 연락처를 입력받아, 일치 시 아이디를 반환한다.
    @GetMapping("/findId")
    public MemberDto findId( @RequestParam String mname, @RequestParam String mphone ){
        System.out.println("MemberController.findId");
        // 1. Service에게 전달할 객체 생성 후 값 넣기
        MemberDto memberDto = new MemberDto();
        memberDto.setMname( mname );
        memberDto.setMphone( mphone );
        // 2. Service에게 전달 후 결과 받기
        return memberService.findId( memberDto );
    } // func end

    // [member10] 비밀번호 찾기 - findPwd
    // 아이디 + 연락처를 입력받아, 일치하는지 확인 후 일치하면,
    // 새로운 난수 비밀번호 생성 후 반환하고, 생성된 비밀번호를 DB에 업데이트한다.
    @GetMapping("/findPwd")
    public MemberDto findPwd( @RequestParam String mid, @RequestParam String mphone ){
        System.out.println("MemberController.findPwd");
        // 1. Service에게 전달할 객체 생성 후 값 넣기
        MemberDto memberDto = new MemberDto();
        memberDto.setMid( mid );
        memberDto.setMphone( mphone );
        // 2. Service에게 전달하여, 일치하는지 확인하기
        if ( memberService.findPwd( memberDto ) != null ){
            // 3. 일치하면, 난수를 생성하여 반환
            return createPwd( memberDto );
        } else {
            // 4. 일치하지 않으면, null 반환
            return null;
        } // if end
    } // func end

    // [member11] 난수 생성 - createPwd
    public MemberDto createPwd( MemberDto memberDto ){
        // 1. 새로운 비밀번호 선언
        StringBuilder newPwd = new StringBuilder();
        // 2. 10자리 새로운 비밀번호 생성
        for ( int i = 1; i <= 10; i++ ){
            Random random = new Random();
            int type = random.nextInt( 3 );
            if ( type == 0 ){           // 소문자
                int value = random.nextInt( 26 ) + 'a';
                newPwd.append( ( char ) value );
            } else if ( type == 1 ){    // 정수
                int value = random.nextInt( 10 ) + '0';
                newPwd.append( ( char ) value );
            } else {                    // 대문자
                int value = random.nextInt( 26 ) + 'A';
                newPwd.append( ( char ) value );
            } // if end
        } // for end
        System.out.println( newPwd );
        // 3. 생성한 난수 비밀번호 Service에게 전달 후, 결과 반환
        memberDto.setMpwd( newPwd.toString() );
        return memberService.createPwd( memberDto );
    } // func end
} // class end
