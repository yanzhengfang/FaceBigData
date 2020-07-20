package com.koocloud.facerecognition.showdata.controller;

import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.service.LocalRedisService;
import com.koocloud.facerecognition.showdata.service.RemoteRedisService;
import com.koocloud.facerecognition.showdata.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisDataRest")
public class RedisDataController {
    @Autowired
    LocalRedisService localRedisService;

    @Autowired
    RemoteRedisService remoteRedisService;

    /**
     * 本地redis列表数据
     * @param pageVo
     * @return
     */
    @PostMapping("/localRedisList")
    public ResponseTemplate localRedisList(PageVo pageVo) throws Exception {
        return  localRedisService.localRedisList(pageVo);
    }


    /**
     * 远程redis列表数据
     * @param pageVo
     * @return
     */
    @PostMapping("/remoteRedisList")
    public ResponseTemplate remoteRedisList(PageVo pageVo) throws Exception {
        return  remoteRedisService.remoteRedisList(pageVo);
    }
}
