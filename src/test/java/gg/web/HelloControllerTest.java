package gg.web;

import gg.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
// 여기서는 SpringRunner라는 스프링 실행자를 사용
// 즉, 스프링부트 테스트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)

// 여러 스프링 테스트 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
// 단, @Service, @Component, @Repository 등은 사용할 수 없음
// 여기서는 컨트롤러만 사용하기 때문에 선언.
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })

public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean) 주입 받음
    private MockMvc mvc; // 웹 API를 테스트할 때 사용, 스프링 MVC테스트의 시작점, 이 클래스를 통해 HTTP GET, POST 등에 대한 API테스트를 할 수 있음

    @WithMockUser(roles="USER")
    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소로 HTTP GET 요청 , 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증, HTTP Header의 Status를 검증. 200/404/500 ... 여기선 OK, 즉 200인지 아닌지 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증. 컨트롤러에서 hello를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }


}
