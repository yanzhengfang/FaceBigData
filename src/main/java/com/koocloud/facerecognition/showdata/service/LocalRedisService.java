package com.koocloud.facerecognition.showdata.service;


import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.vo.PageVo;

public interface LocalRedisService {

    /**
     * 本地redis列表查询
     * @param pageVo
     * @return
     * @throws Exception
     */
    public ResponseTemplate localRedisList(PageVo pageVo) throws Exception;
}
