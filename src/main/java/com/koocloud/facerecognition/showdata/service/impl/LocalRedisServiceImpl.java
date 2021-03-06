package com.koocloud.facerecognition.showdata.service.impl;

import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.exception.ParamInvalidException;
import com.koocloud.facerecognition.showdata.service.LocalRedisService;
import com.koocloud.facerecognition.showdata.util.PageUtil;
import com.koocloud.facerecognition.showdata.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalRedisServiceImpl implements LocalRedisService {
    @Autowired
    StringRedisTemplate localStrRedisTemplate;

    @Value("${project.redis.prefix}")
    private String redisPrefix;


    @Override
    public ResponseTemplate localRedisList(PageVo pageVo) throws Exception {
        //参数校验
        if(pageVo.getPage()<=0){
            throw  new ParamInvalidException("page");
        }
        if(pageVo.getLimit()<=0){
            throw  new ParamInvalidException("limit");
        }

        String prefix = redisPrefix + "*";
        //查询到所有的前缀key值
        Set<String> keys = localStrRedisTemplate.keys(prefix);
        // 批量获取数据
        List<String> redisList = localStrRedisTemplate.opsForValue().multiGet(keys);

        //用于存放结果
        List<Map<String,String>> res = new ArrayList<>();
        //用于存放总数
        Long  count = Long.valueOf(0);
        if(redisList !=null){
            //获取总数
            count = Long.valueOf(redisList.size());
        }
        //组装结果
        PageUtil.redisListDataOperation(redisList,res,keys,pageVo,redisPrefix);

        return ResponseTemplate.builder().code(0).count(count).message("请求成功").data(res).build();
    }
}
