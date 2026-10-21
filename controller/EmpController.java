package com.qingda.tliaswebmanagement.controller;

import com.qingda.tliaswebmanagement.anno.Log;
import com.qingda.tliaswebmanagement.mapper.EmpMapper;
import com.qingda.tliaswebmanagement.pojo.Emp;
import com.qingda.tliaswebmanagement.pojo.PageBean;
import com.qingda.tliaswebmanagement.pojo.Result;
import com.qingda.tliaswebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
    log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean= empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除员工，id:{}",id);
        empService.deleteById(id);
        return Result.success();

    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工，emp:{}",emp);
        empService.save(emp);
        return Result.success();

    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息，id:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);

    }
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息：{}",emp);
        empService.update(emp);
        return Result.success();

    }

}
