package com.qingcheng.controller;

import com.qingcheng.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//异常处理类  所有异常集中处理
//表示此类为一个控制器的通知类
@ControllerAdvice
public class BaseExceptionHandler {

    //错误统一处理方法
    //此注解用来去指定你的所检测的异常类型 Exception.class 传入此参数表示检测所有异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回格式转换为Json
    public Result error(Exception e) {
        //打印异常
        e.printStackTrace();
        System.out.println("调用了异常处理");
        //返回异常信息
        return new Result(1, e.getMessage());
    }
}
