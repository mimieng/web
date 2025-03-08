package org.example.webmanagement.service;

import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.EmpQueryParam;
import org.example.webmanagement.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
