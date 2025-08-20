package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.MemberDto;
import web.service.FileService;
import web.service.MemberService;

import java.util.Map;

@RestController    // 스프링 컨테이너에 객체 등록
@RequestMapping("/member")
public class MemberController {
    // * MemberService 가져오기
    private final MemberService memberService;
    private final FileService fileService;
    @Autowired  // 스프링 컨테이너에 등록된 빈 주입받기
    public MemberController( MemberService memberService, FileService fileService ){
        this.memberService = memberService;
        this.fileService = fileService;
    } // func end

    // [member01] 회원가입 기능 - signup
    @PostMapping("/signup")
    public int signup( MemberDto memberDto ){
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);

        // 1. 업로드 파일 확인하기
        // 1-1. 첨부파일이 존재한다면
        if ( !memberDto.getUpload().isEmpty() ){
            // 1-2. 첨부파일을 꺼내서 업로드 진행
            MultipartFile multipartFile = memberDto.getUpload();
            String mimgname = fileService.fileUpload( multipartFile );
            // 1-3. 파일명을 memberDto에 넣기
            memberDto.setMimgname( mimgname );
        } // if end
        return memberService.signup( memberDto );
    } // func end

    // [member02] 로그인 기능 - login
    @PostMapping("/login")  // 비밀번호를 보여주지않기 위해서, POST
    public int login( @RequestBody MemberDto memberDto, HttpServletRequest request ){
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
        if ( !memberService.checkSession( session ) ) return false;
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
        if ( !memberService.checkSession( session ) ) return null;
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
        if ( !memberService.checkSession( session ) ) return false;
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
        if ( !memberService.checkSession( session ) ) return false;
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
        if ( !memberService.checkSession( session ) ) return false;
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
        // 2. Service에게 전달 후, 결과 반환하기
        return memberService.findPwd( memberDto );
    } // func end
} // class end