package com.ordi.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러 (과거 @ResponseBody를 각 메소드 마다 선언했던 것을 한꺼번에 해줌)
public class HelloController {

    @GetMapping("/hello") // 과거 @ResquestMapping(method = RequestMethod.GET) 과 같음
    public String hello() {
        return "hello";
    }
}
