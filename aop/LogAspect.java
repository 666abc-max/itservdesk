package com.qingda.tliaswebmanagement.aop;

import com.alibaba.fastjson.JSONObject;
import com.qingda.tliaswebmanagement.mapper.OperateLogMapper;
import com.qingda.tliaswebmanagement.pojo.OperateLog;
import com.qingda.tliaswebmanagement.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.qingda.tliaswebmanagement.anno.Log)")
    public Object recordingLog(ProceedingJoinPoint joinPoint) throws Throwable{

        //操作人id  当前员工的id
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //获取方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object []args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();

       //调用原始目标方法开始运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();


        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime = end- begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("aop记录日志：{}",operateLog);



        return result;

    }
}
