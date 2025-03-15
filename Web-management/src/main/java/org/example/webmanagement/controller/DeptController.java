package org.example.webmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.pojo.Dept;
import org.example.webmanagement.pojo.Result;
import org.example.webmanagement.service.DeptService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
//    private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result list()
    {
//        System.out.println("查询全部数据");
        log.info("查询全部数据");
        List<Dept> depts = deptService.findAll();
        return Result.success(depts);
    }
    @DeleteMapping
    public Result delete(Integer id)
    {
//        System.out.println("删除数据"+id);
        log.info("删除数据"+id);
        deptService.delete(id);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Dept dept)
    {
//        System.out.println("保存数据"+dept);
        log.info("保存数据"+dept);
        deptService.save(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id)
    {
//        System.out.println("查询单个数据"+id);
        log.info("查询单个数据"+id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    @PutMapping
    public Result update(@RequestBody Dept dept)
    {
//        System.out.println("修改数据"+dept);
        deptService.update(dept);
        return Result.success();
    }
}
