package org.example.webmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.webmanagement.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    void insert(List<EmpExpr> empList);

    void deleteEmpIds(Integer[] empIds);
}
