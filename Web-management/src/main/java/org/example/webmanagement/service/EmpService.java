package org.example.webmanagement.service;

import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.EmpQueryParam;
import org.example.webmanagement.pojo.LoginInfo;
import org.example.webmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);
    void save(Emp emp);
    void delete(Integer [] ids);
    Emp getById(Integer id);
    void update(Emp emp);

    LoginInfo login(Emp emp);
}
