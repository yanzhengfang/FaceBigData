package com.koocloud.facerecognition.showdata.service;


import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.vo.PageVo;

public interface MainDataService {

    /**
     * 完整账户数据库列表查询
     * @param pageVo
     * @return
     * @throws Exception
     */
    public ResponseTemplate mainDataList(PageVo pageVo) throws Exception;
}
