package org.example.webmanagement.service.impl;

import org.example.webmanagement.mapper.EmpMapper;
import org.example.webmanagement.pojo.Emp;
import org.example.webmanagement.pojo.JobOption;
import org.example.webmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String , Object>>maps=empMapper.countEmpJobData();
        List<Object> jobList= maps.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> DataList= maps.stream().map(dataMap->dataMap.get("num")).toList();

        return new JobOption(jobList,DataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
