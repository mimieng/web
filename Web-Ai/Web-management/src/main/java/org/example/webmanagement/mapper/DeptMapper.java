package org.example.webmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.example.webmanagement.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    List<Dept> findAll();
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept getById(Integer id);
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
