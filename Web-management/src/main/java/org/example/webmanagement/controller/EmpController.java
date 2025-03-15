package org.example.webmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.EmpQueryParam;
import org.example.webmanagement.pojo.PageResult;
import org.example.webmanagement.pojo.Result;
import org.example.webmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @GetMapping
    public Result page(EmpQueryParam empQueryParam)
    {
        log.info("分页查询，参数：{}",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工，员工信息：{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询员工信息，id：{}",id);
        Emp emp=empService.getById(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息，员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(Integer [] ids){
        log.info("删除员工，id：{}",ids);
        empService.delete(ids);
        return Result.success();
    }
}
