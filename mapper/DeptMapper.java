package com.qingda.tliaswebmanagement.mapper;

import com.qingda.tliaswebmanagement.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("Select * from dept")
    public List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer Id);

    @Insert("INSERT into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    public void insert(Dept dept);
}
