package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PostDto;
import web.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController( PostService postService ) {
        this.postService = postService;
    } // func end

    // 게시물 등록
    @PostMapping("/write")
    public String writePost( @RequestBody PostDto postDto, HttpSession session ){

    } // func end
} // class end