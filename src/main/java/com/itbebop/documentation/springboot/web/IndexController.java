package com.itbebop.documentation.springboot.web;

import com.itbebop.documentation.springboot.service.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final PostsService postsService;

    public IndexController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
        /*
         Model
            - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
            - 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달함
         */

    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
