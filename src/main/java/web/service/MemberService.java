package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

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

} // class end
