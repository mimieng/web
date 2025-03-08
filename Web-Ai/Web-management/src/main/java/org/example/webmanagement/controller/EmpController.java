package org.example.webmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.EmpQueryParam;
import org.example.webmanagement.pojo.PageResult;
import org.example.webmanagement.pojo.Result;
import org.example.webmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
//                         LocalDate end){
//        log.info("分页查询，参数：page={},pageSize={},name={},gender={},begin={},end={}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult=empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    public Result page(EmpQueryParam empQueryParam)
    {
        log.info("分页查询，参数：{}",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);
    }
}
