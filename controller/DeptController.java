package com.qingda.tliaswebmanagement.controller;

import com.qingda.tliaswebmanagement.anno.Log;
import com.qingda.tliaswebmanagement.pojo.Dept;
import com.qingda.tliaswebmanagement.pojo.Result;
import com.qingda.tliaswebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
//公共路径
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping//限定请求方式为get
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptlist = deptService.list();
        return Result.success(deptlist);
    }

    //删除部门
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        deptService.deleteById(id);
        return Result.success();

    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }
}
