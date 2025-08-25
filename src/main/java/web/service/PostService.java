package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PostDao;
import web.model.dto.PostDto;

@Service
public class PostService {
    private final PostDao postDao;
    @Autowired
    public PostService( PostDao postDao ) {
        this.postDao = postDao;
    } // func end

    // 게시물 등록
    public int writePost( PostDto postDto ){
        return postDao.writePost( postDto );
    } // func end
} // class end