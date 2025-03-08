package org.example.webmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.webmanagement.mapper.EmpMapper;
import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.EmpQueryParam;
import org.example.webmanagement.pojo.PageResult;
import org.example.webmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//    Long total=empMapper.count();
//    Integer start=(page-1)*pageSize;
//    List<Emp> rows=empMapper.list(start,pageSize);
//    return new PageResult<>(total,rows);
//    }
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> empList=empMapper.list(empQueryParam);
        Page<Emp> pageInfo=(Page<Emp>)empList;
        return new PageResult<Emp> (pageInfo.getTotal(),pageInfo.getResult() );
    }
}
