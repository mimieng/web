package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
//封裝用戶信息
@NoArgsConstructor
@AllArgsConstructor
@Data
public class user {
    private Integer id;
    private String username;
    private String password;
    private String name ;
    private Integer age;
    private LocalDateTime updateTime;
}
