package org.example.webmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.EmpQueryParam;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmpMapper {
//    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
//    public Long count();
//    @Select("select e.*,d.name deptName from emp e left join dept d on  e.dept_id=d.id order by e.update_time desc  limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);
//    @Select("select e.*,d.name deptName from emp e left join dept d on  e.dept_id=d.id order by e.update_time desc")
    public List<Emp> list(EmpQueryParam empQueryParam);
    @Options(useGeneratedKeys = true,keyProperty = "id")

    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)"+
            "value (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(Integer[] ids);

    Emp getById(Integer id);

    void update(Emp emp);

//    统计员工职位
    @MapKey("pos")
    List<Map<String , Object>> countEmpJobData();
@   MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
    @Select("select id ,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
