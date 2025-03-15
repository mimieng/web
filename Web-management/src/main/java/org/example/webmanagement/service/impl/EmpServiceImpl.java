package org.example.webmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.mapper.EmpExprMapper;
import org.example.webmanagement.mapper.EmpMapper;
import org.example.webmanagement.pojo.*;
import org.example.webmanagement.service.EmpService;
import org.example.webmanagement.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class EmpServiceImpl implements EmpService {
    private static final Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);
    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//    Long total=empMapper.count();
//    Integer start=(page-1)*pageSize;
//    List<Emp> rows=empMapper.list(start,pageSize);
//    return new PageResult<>(total,rows);
//    }

    @Autowired
    private EmpExprMapper empExprMapper;
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> empList=empMapper.list(empQueryParam);
        Page<Emp> pageInfo=(Page<Emp>)empList;
        return new PageResult<Emp> (pageInfo.getTotal(),pageInfo.getResult() );
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer [] ids) {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteEmpIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        empExprMapper.deleteEmpIds(new Integer[]{emp.getId()});
        List<EmpExpr> empList=emp.getExprList();
        if (!CollectionUtils.isEmpty(empList)){
            empList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insert(empList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e=empMapper.selectByUsernameAndPassword(emp);
        if (e!=null){
            log.info("登录成功，员工信息：{}",e);
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String token = JwtUtils.createToken(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),token);
        }
        return null;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        List<EmpExpr> empList=emp.getExprList();
        if (!CollectionUtils.isEmpty(empList)){
            empList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insert(empList);
        }
    }
}
