package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

import java.util.Map;

@Service    // 스프링 컨테이너에 객체 등록
public class MemberService {
    // * MemberDao 가져오기
    private final MemberDao memberDao;
    @Autowired  // 스프링 컨테이너에 등록된 빈 주입받기
    public MemberService( MemberDao memberDao ){
        this.memberDao = memberDao;
    } // func end

    // [member01] 회원가입 기능 - signup
    public int signup( MemberDto memberDto ){
        return memberDao.signup( memberDto );
    } // func end

    // [member02] 로그인 기능 - login
    public int login( MemberDto memberDto ){
        return memberDao.login( memberDto );
    } // func end

    // [member04] 내정보 조회 기능 - info
    public MemberDto info( int mno ){
        return memberDao.info( mno );
    } // func end

    // [member05] 중복검사 기능 - check
    public boolean check( String type, String data ){
        return memberDao.check( type, data );
    } // func end

    // [member06] 회원정보수정 기능 - update
    public boolean update( MemberDto memberDto ){
        return memberDao.update( memberDto );
    } // func end

    // [member07] 비밀번호수정 기능 - updatePassword
    public boolean updatePassword( int mno, Map< String, String > map ){
        return memberDao.updatePassword( mno, map );
    } // func end

    // [member08] 회원탈퇴 기능 - delete

} // class end
