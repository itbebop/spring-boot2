package com.itbebop.documentation.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
/*
    - 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
    - 여기서는 SpringRunner 라는 스프링 실행자를 사용한다.
    - 즉, 스프링부트 테스트와 JUnit 사이에 연결자 역할을 한다.
 */
@WebMvcTest(controllers = HelloController.class)
/*
    - 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
    - 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음.
    - 단, @Service, @Component, @Repository 등은 사용할 수 없음.
    - 여기서는 컨트롤러만 사용하기 때문에 선언
 */
public class HelloControllerTest {
    @Autowired
    /*
    스프링이 관리하는 Bean을 주입받음
     */
    private MockMvc mvc;
    /*
    웹 API를 테스트할 때 사용
    스프링 MVC테스트의 시작점
    이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
     */

    @Test
    public void hello_리턴한다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
        /*
        MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
        체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음
         */
                .andExpect(status().isOk())
                /*
                 mvc.perform의 결과를 검증함
                 HTTP Header의 Status를 검증함
                 200, 404, 500 등의 상태를 검증함
                 여기선 OK 즉, 200인지 아닌지를 검증함
                 */
                .andExpect(content().string(hello));
                /*
                 mvc.perform의 결과를 검증함
                 응답 본문의 내용을 검증함
                 Controller에서 "hello"를 리턴하기 떄문에 이 값이 맞는지 검증함
                 */
    }
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // 1)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                //.andExpect(jsonPath("$.amount").value(amount))
                //.andExpect(jsonPath("$.name", is(name)))
                //.andExpect(jsonPath("$.amount", is(amount)))

                ;
        /*
         1) param
          - API 테스트할 때 사용될 요청 파라미터를 설정함
          - param의 값은 String만 허용됨
          - 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야 가능함 (ex. amount도 스트링으로 변경함)
         2) jsonPath
          - JSON 응답값을 필드별로 검증할 수 있는 메소드임
          - $를 기준으로 필드명을 명시함
          - 여기서는 name과 aount를 검증하므로 $name,$amount로 검증함
         */
    }
}