package gg.web.domain.posts;

import gg.web.posts.Posts;
import gg.web.posts.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 데이터베이스 자동 실행해줌.
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // Jnint에서 단위테스트가 끝날 때 마다 수행되는 메소드를 지정 - 테스트간 데이터 침범을 막기 위해
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 테이블 posts에 insert/update 쿼리 실행. id 값이 있다면 update, 없다면 insert
                .title(title)
                .content(content)
                .author("jining@gmail.com")
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터 조회

        // then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);

    }

}
