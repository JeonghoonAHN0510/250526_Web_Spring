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

    // [2] 게시물 전체조회(페이징처리) + 검색 기능 추가
    // cno : 카테고리 번호, page : 현재 페이지번호, perCount : 페이지당 자료 개수
    public PageDto findAllPost( int cno, int page, int perCount, String key, String keyword ){
        // 1. 페이지별 조회를 시작할 인덱스 번호 계산
        int startRow = ( page - 1 ) * perCount;

        // * 자료를 미리 선언하기
        // 2. 카테고리별 자료 개수 선언하기
        int totalCount;
        // 3. Dao에게 받을 자료 선언하기
        List<PostDto> postList;
        // key-keyword(검색)의 유무에 따른, totalCount와 postList 구분하기
        if ( key != null && !key.isEmpty() && keyword != null && !keyword.isEmpty() ){
            // (1) 검색이 존재할 때
            // (1-1) 검색 조건에 따른 카테고리별 자료 개수 구하기
            totalCount = postDao.getTotalCountSearch( cno, key, keyword );
            // (1-2) 검색 조건에 따른 Dao에게 자료 요청하기
            postList = postDao.findAllSearch( cno, startRow, perCount, key, keyword );
        } else {
            // (2) 검색이 존재하지 않을 때
            // (2-1) 카테고리별 자료 개수 구하기
            totalCount = postDao.getTotalCount( cno );
            // (2-2) Dao에게 자료 요청하기 : cno, startRow, count
            postList = postDao.findAllPrint( cno, startRow, perCount );
        } // if end

        // 4. 전체 페이지 수 구하기 -> 자료개수 % 페이지당 자료 개수 = 나머지가 있으면, 페이지 1개 추가
        int totalPage = totalCount % perCount == 0 ? totalCount / perCount : (totalCount / perCount) + 1;
        if ( totalPage == 0 ) totalPage = 1;
        // 5. 페이지 시작 번호 구하기
        // 한 페이지에 보여지는 버튼 수
        int btnCount = 5;
        int startBtn = ( ( page - 1 ) / btnCount ) * btnCount + 1;
        // 6. 페이지 끝 번호 구하기
        int endBtn = startBtn + btnCount - 1;
        if ( endBtn > totalPage ) endBtn = totalPage;
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