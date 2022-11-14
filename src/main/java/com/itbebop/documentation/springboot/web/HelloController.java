package com.itbebop.documentation.springboot.web;

import com.itbebop.documentation.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name , @RequestParam("amount") int amount){
        /*
         1) @RequestParam
          - 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션임
          - 여기서는 외부(API를 호출한 곳)에서 name (@RequestParam("name") 이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장함
         */
        return new HelloResponseDto(name, amount);
    }
}
