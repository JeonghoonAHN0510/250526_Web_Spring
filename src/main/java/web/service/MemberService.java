package web.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;
import web.model.dto.PointDto;

import java.util.Map;
import java.util.Random;

@Service    // 스프링 컨테이너에 객체 등록
public class MemberService {
    // * MemberDao 가져오기
    private final MemberDao memberDao;
    private final PointService pointService;
    @Autowired  // 스프링 컨테이너에 등록된 빈 주입받기
    public MemberService( MemberDao memberDao, PointService pointService ){
        this.memberDao = memberDao;
        this.pointService = pointService;
    } // func end

    // [member01] 회원가입 기능 - signup
    public int signup( MemberDto memberDto ){
        // 1. 회원가입 성공여부 받아오기
        int mno = memberDao.signup( memberDto );
        memberDto.setMno( mno );
        // 2. 포인트 지급할 포인트 객체 생성
        PointDto pointDto = new PointDto();
        pointDto.setMno( mno );
        pointDto.setPlpoint( 1000 );
        pointDto.setPlcomment( "회원가입" );
        // 3. 회원가입에 성공했다면, 포인트 지급
        if ( mno > 0 ){
            pointService.addPoint( pointDto );
        } else {
            return 0;
        } // if end
        // 4. 회원가입 프로필 등록
        if ( !memberDao.signupProfile( memberDto ) ) return 0;
        // 5. 최종적으로 반환
        return mno;
    } // func end

    // [member02] 로그인 기능 - login
    public int login( MemberDto memberDto ){
        // 1. 로그인 성공여부 받아오기
        int result = memberDao.login( memberDto );
        // 2. 포인트 지급할 포인트 객체 생성
        PointDto pointDto = new PointDto();
        pointDto.setMno( result );
        pointDto.setPlpoint( 100 );
        pointDto.setPlcomment( "로그인" );
        // 3. 로그인에 성공했다면, 포인트 지급
        if ( result > 0 ) pointService.addPoint( pointDto );
        return result;
    } // func end

    // [member04] 내정보 조회 기능 - info
    public MemberDto info( int mno ){
        // 1. 내 정보를 조회하기
        MemberDto memberDto = memberDao.info( mno );
        // 2. 조회에 성공했다면
        if ( memberDto != null ){
            // 3. 프로필 사진 조회
            String mimgname = memberDao.getProfile( memberDto );
            // 4. 조회한 파일명을 memberDto에 저장
            memberDto.setMimgname( mimgname );
        } // if end
        // 5. 최종적으로 반환
        return memberDto;
    } // func end

    // [member05] 중복검사 기능 - check
    public boolean check( String type, String data ){
        return memberDao.check( type, data );
    } // func end

    // [member06] 회원정보수정 기능 - update
    public boolean update( MemberDto memberDto ){
        // 1. 기본회원정보 수정을 실패하면, false 반환
        if ( !memberDao.update( memberDto ) ) return false;
        // 2. 성공했다면, 프로필사진 수정하고 반환
        return memberDao.signupProfile( memberDto );
    } // func end

    // [member07] 비밀번호수정 기능 - updatePassword
    public boolean updatePassword( int mno, Map< String, String > map ){
        return memberDao.updatePassword( mno, map );
    } // func end

    // [member08] 회원탈퇴 기능 - delete
    public boolean delete( MemberDto memberDto ){
        return memberDao.delete( memberDto );
    } // func end

    // [member09] 아이디 찾기 - findId
    // 이름 + 연락처를 입력받아, 일치 시 아이디를 반환한다.
    public MemberDto findId( MemberDto memberDto ){
        return memberDao.findId( memberDto );
    } // func end

    // [member10] 비밀번호 찾기 - findPwd
    // 아이디 + 연락처를 입력받아, 일치확인.
    public MemberDto findPwd( MemberDto memberDto ){
        // 1. Dao에게 전달 후 일치 확인 -> false이면 불일치, true면 일치
        if ( memberDao.findPwd( memberDto ) ){
            // 2-1. 일치하면 난수 생성
            String newPwd = createPwd();
            // 3. 생성한 난수 객체에 넣기
            memberDto.setMpwd( newPwd );
        } else {
            // 2-2. 불일치하면 null 반환
            return null;
        } // if end
        // 4. 난수를 넣은 객체 DB에 업데이트하기
        if ( memberDao.createPwd( memberDto ) ){
            // 5-1. 수정에 성공했으면
            return memberDto;
        } else {
            // 5-2. 수정에 실패했다면 null 반환
            return null;
        } // if end
    } // func end

    // [member11] 난수 생성 - createPwd
    // 새로운 난수 비밀번호 생성 후 반환하고, 생성된 비밀번호를 DB에 업데이트한다.
    // 난수 생성은 Service에서 진행
    public String createPwd(){
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
        return newPwd.toString();
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
} // class end
