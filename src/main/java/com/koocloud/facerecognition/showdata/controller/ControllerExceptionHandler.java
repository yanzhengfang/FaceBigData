package com.koocloud.facerecognition.showdata.controller;

import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.exception.NoParamException;
import com.koocloud.facerecognition.showdata.exception.NoSuitableDataException;
import com.koocloud.facerecognition.showdata.exception.ParamInvalidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 没有其他参数
     * @param e
     * @return
     */
    @ExceptionHandler(NoParamException.class)
    @ResponseBody
    public ResponseTemplate noParamHandler(NoParamException e){
        return ResponseTemplate.builder().code(9001).message("未接收到参数:"+e.getMessage()).build();
    }


    /**
     * 数据库找不到对应的数据
     * @param e
     * @return
     */
    @ExceptionHandler(NoSuitableDataException.class)
    @ResponseBody
    public ResponseTemplate noSuitableDataHandler(NoSuitableDataException e){
        return ResponseTemplate.builder().code(9002).message("系统未找到对应的数据").build();
    }

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(ParamInvalidException.class)
    @ResponseBody
    public ResponseTemplate paramInvalidHandler(ParamInvalidException e){
        return ResponseTemplate.builder().code(9003).message("您发送的参数:"+e.getMessage()+",取值有误").build();
    }


    /**
     * 未捕获异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseTemplate unkownExceptionHandler(Exception e){
        e.printStackTrace();
        return ResponseTemplate.builder().code(9999).message("系统出错啦").build();
    }

}
