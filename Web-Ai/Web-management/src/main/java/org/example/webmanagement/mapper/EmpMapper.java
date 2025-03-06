package org.example.webmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpMapper {
    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    int getTotal();
}
