package com.example.springboot_start.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpResponse;

@RestController
public class ResponseController {
@RequestMapping("/response")
//    public void response(HttpServletResponse httpResponse) throws IOException {
//        //1.设置响应码
//        httpResponse.setStatus(401);
//        //2.设置响应头
//        httpResponse.setHeader("name","zhangsan");
//        //3.设置响应体
//        httpResponse.getWriter().write("<h1>hello world</h1>");
//        }
    public ResponseEntity <String> response(){
       return ResponseEntity.status(401).header("name","zhangsan").body("hello world");
    }
}
