package 종합.예제12.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.예제12.model.dao.BoardDao;
import 종합.예제12.model.dto.BoardDto;

import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    // 1. 게시물 등록 메소드
    public boolean boardWrite( BoardDto boardDto ){
        return boardDao.boardWrite( boardDto );
    } // func end

    // 2. 게시물 전체조회
    public ArrayList<BoardDto> boardPrint(){
        return boardDao.boardPrint();
    } // func end

    // 3. 게시물 단일조회
    public BoardDto boardFing( int bno ){
        return boardDao.boardfint( bno );
    } // func end

    // 4. 게시물 삭제
    public boolean  boardDelete( int bno ){
        return boardDao.boardDelete( bno );
    } // func end

    // 5. 게시물 수정
    public boolean boardUpdate( BoardDto boardDto ){
        return boardDao.boardUpdate( boardDto );
    } // func end
} // class end
