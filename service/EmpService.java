package com.qingda.tliaswebmanagement.service;

import com.qingda.tliaswebmanagement.pojo.Emp;
import com.qingda.tliaswebmanagement.pojo.PageBean;
import com.qingda.tliaswebmanagement.pojo.Result;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface EmpService {
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,LocalDate end);
    public void deleteById(Integer id);
    public void save(Emp emp);
    public Emp getById(Integer id);
    public void update(Emp emp);
    public Emp login(Emp emp);
}
