package com.qingda.tliaswebmanagement.service;

import com.qingda.tliaswebmanagement.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询全部部门数据
    public List<Dept> list();
    public void deleteById(Integer id);
    public void add(Dept dept);
}
