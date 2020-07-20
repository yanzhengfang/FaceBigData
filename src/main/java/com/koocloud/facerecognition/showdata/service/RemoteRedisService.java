package com.koocloud.facerecognition.showdata.service;


import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.vo.PageVo;

public interface RemoteRedisService {

    /**
     * 远程redis列表查询
     * @param pageVo
     * @return
     * @throws Exception
     */
    public ResponseTemplate remoteRedisList(PageVo pageVo) throws Exception;
}
