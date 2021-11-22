package gg.web;

import gg.config.auth.LoginUser;
import gg.config.auth.dto.SessionUser;
import gg.service.posts.PostsService;
import gg.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    @GetMapping("/") // 어느 컨트롤러든지 @LoginUser 를 사용해 세션 정보를 가져 올 수 있음
    public String index(Model model, @LoginUser SessionUser user) { // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음.
        model.addAttribute("posts", postsService.findAllDesc());
        // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장 -> @LoginUser 어노테이션으로 개선
        //SessionUser user = (SessionUser) httpSession.getAttribute(("user"));
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
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
