package com.koocloud.facerecognition.showdata.service;

import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.exception.NoSuitableDataException;
import com.koocloud.facerecognition.showdata.vo.PageVo;


public interface CardInfoService {
    /**
     * spt数据库列表查询
     * @param pageVo
     * @return
     * @throws Exception
     */
    public ResponseTemplate cardList(PageVo pageVo) throws Exception;
}
