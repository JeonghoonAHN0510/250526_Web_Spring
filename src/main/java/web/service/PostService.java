package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.model.dao.PostDao;
import web.model.dto.PostDto;

@Service
@RequiredArgsConstructor            // final 변수에 대한 생성자 자동 제공
public class PostService {
    private final PostDao postDao;  // @RequiredArgsConstructor를 사용하여, 생성자를 만들 필요 X + @Autowired를 생략해도 자동으로 의존성 처리

    // [1] 게시물 등록
    public int writePost( PostDto postDto ){
        return postDao.writePost( postDto );
    } // func end
} // class end