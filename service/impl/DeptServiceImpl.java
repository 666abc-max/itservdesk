package com.qingda.tliaswebmanagement.service.impl;

import com.qingda.tliaswebmanagement.mapper.DeptMapper;
import com.qingda.tliaswebmanagement.mapper.EmpMapper;
import com.qingda.tliaswebmanagement.pojo.Dept;
import com.qingda.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    EmpMapper empMapper;
    @Override
    public List<Dept> list(){
        return deptMapper.list();
    }

    @Transactional
    @Override
    public void deleteById(Integer id){
        deptMapper.deleteById(id);
        empMapper.deleteByDeptId(id);//根据部门的id删除该部门下的id
    }

    @Override
    public void add(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }
}
