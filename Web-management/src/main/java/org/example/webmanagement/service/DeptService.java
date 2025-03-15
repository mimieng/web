package org.example.webmanagement.service;

import org.example.webmanagement.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();
    void delete(Integer id);
    void save(Dept dept);
    Dept getById(Integer id);
    void update(Dept dept);
}
