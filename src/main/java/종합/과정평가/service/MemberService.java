package 종합.과정평가.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.과정평가.model.dao.MemberDao;
import 종합.과정평가.model.dto.MemberDto;

import java.util.ArrayList;

@Service
public class MemberService {
    // MemberDao 가져오기
    final MemberDao memberDao;
    @Autowired
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    } // func end

    // 1. 회원등록기능
    public int addMember( MemberDto memberDto ){
        return memberDao.addMember( memberDto );
    } // func end

    // 2. 회원번호 자동생성
    public int custnoPrint(){
        return memberDao.custnoPrint();
    } // func end

    // 3. 회원목록 조회
    public ArrayList<MemberDto> memberPrint(){
        return memberDao.memberPrint();
    } // func end

    // 4. 회원정보 수정
    public int updateMember( MemberDto memberDto ){
        return memberDao.updateMember( memberDto );
    } // func end

    // 5. 선택 회원정보 출력
    public MemberDto memberPrintOne( int custno ){
        return memberDao.memberPrintOne( custno );
    } // func end

} // class end
