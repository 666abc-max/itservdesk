package com.qingda.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingda.tliaswebmanagement.mapper.EmpMapper;
import com.qingda.tliaswebmanagement.pojo.Emp;
import com.qingda.tliaswebmanagement.pojo.PageBean;
import com.qingda.tliaswebmanagement.pojo.Result;
import com.qingda.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageBean page(Integer page,Integer pageSize){
//        //获取总记录数
//        Long count = empMapper.count();
//        //获取分页查询结果列表
//        Integer start = (page - 1)*pageSize;
//        List<Emp> emplist =  empMapper.page(start,pageSize);
//        PageBean pageBean = new PageBean(count,emplist);
//        return pageBean;
//    }
public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,LocalDate end){
    //设置分页参数
    PageHelper.startPage(page,pageSize);
    //执行查询
    List<Emp> emplist = empMapper.list(name,gender,begin,end);
    Page<Emp> p = (Page<Emp>)emplist;

      PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
      return pageBean;
    }
    public void deleteById(Integer id){

    empMapper.delete(id);

    }

    @Override
    public void save(Emp emp){
    emp.setCreateTime(LocalDateTime.now());
    emp.setUpdateTime(LocalDateTime.now());
    empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id){

    return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp){
    emp.setUpdateTime(LocalDateTime.now());
    empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp){
    return empMapper.getByUsernameAndPassword(emp);
    }


}
