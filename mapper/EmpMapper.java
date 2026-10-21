package com.qingda.tliaswebmanagement.mapper;

import com.qingda.tliaswebmanagement.pojo.Emp;
import com.qingda.tliaswebmanagement.pojo.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start,Integer pageSize);
    //使用pagehelper:
    //@Select("select * from emp ")
    public List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);

    @Delete("Delete from emp where id = #{id}")
    public void delete(Integer id);

    @Insert("INSERT INTO emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time)" +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    public Emp getByUsernameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);

}
