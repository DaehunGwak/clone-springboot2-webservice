package com.ordi.book.springboot.web;

import com.ordi.book.springboot.config.auth.LoginUser;
import com.ordi.book.springboot.config.auth.dto.SessionUser;
import com.ordi.book.springboot.service.PostsService;
import com.ordi.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (Objects.nonNull(user)) {
            model.addAttribute("userName", user.getName());
        }

        return "index"; // mustache 스타터가 뒤에 .mustache 확장자는 자동으로 붙여줌
        // View Resolver 가 src/main/resources/template/index.mustache 를 받아 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
