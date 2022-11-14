package com.itbebop.documentation.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter  // 1) 선언된 모든 필드의 get 메소드를 생성해 줌
@RequiredArgsConstructor // 2) 선언된 모둔 final 필드가 포함된 생성자를 생성해 줌
                        // final이 없는 필드는 생성자에 포함되지 않음.
public class HelloResponseDto {
        private final String name;
        private final int amount;
}


