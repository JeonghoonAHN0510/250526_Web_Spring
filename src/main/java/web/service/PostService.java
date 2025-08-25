package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.model.dao.PostDao;
import web.model.dto.PageDto;
import web.model.dto.PostDto;

import java.util.List;

@Service
@RequiredArgsConstructor            // final 변수에 대한 생성자 자동 제공
public class PostService {
    private final PostDao postDao;  // @RequiredArgsConstructor를 사용하여, 생성자를 만들 필요 X + @Autowired를 생략해도 자동으로 의존성 처리

    // [1] 게시물 등록
    public int writePost( PostDto postDto ){
        return postDao.writePost( postDto );
    } // func end

    // [2] 게시물 전체조회(페이징처리)
    // cno : 카테고리 번호, page : 현재 페이지번호, perCount : 페이지당 자료 개수
    public PageDto findAllPost( int cno, int page, int perCount ){
        // 1. 페이지별 조회를 시작할 인덱스 번호 계산
        int startRow = ( page - 1 ) * perCount;
        // 2. 카테고리별 자료 개수 구하기
        int totalCount = postDao.getTotalCount( cno );
        // 3. 전체 페이지 수 구하기 -> 자료개수 / 페이지당 자료 개수 = 나머지가 있으면, 페이지 1개 추가
        int totalPage = totalCount / perCount == 0 ? totalCount / perCount : (totalCount / perCount) + 1;
        // 4. 페이지 시작 번호 구하기
        // 한 페이지에 보여지는 버튼 수
        int btnCount = 5;
        int startBtn = ( ( page - 1 ) / btnCount ) * btnCount + 1;
        // 5. 페이지 끝 번호 구하기
        int endBtn = startBtn + btnCount - 1;
        if ( endBtn > totalPage ) endBtn = totalPage;
        // 6. Dao에게 자료 요청하기 : cno, startRow, count
        List< PostDto > postList = postDao.findAllPrint( cno, startRow, perCount );
        // 7. pageDto 구성하기
        PageDto pageDto = new PageDto();
        pageDto.setCurrentPage( page );         // 현재 페이지 번호
        pageDto.setTotalPage( totalPage );      // 전체 페이지 개수
        pageDto.setPerCount( perCount );        // 페이지 1개당 자료 개수
        pageDto.setTotalCount( totalCount );    // 전체 자료 개수
        pageDto.setStartBtn( startBtn );        // 페이지 시작 번호
        pageDto.setEndBtn( endBtn );            // 페이지 끝 번호
        pageDto.setData( postList );            // 페이징 처리된 자료 리스트
        // 8. 반환하기
        return pageDto;
    } // func end
} // class end