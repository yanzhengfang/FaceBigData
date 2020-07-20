package com.koocloud.facerecognition.showdata.service.impl;

import com.koocloud.facerecognition.showdata.dao.main.MainDataRepositoty;
import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.entity.main.MainData;
import com.koocloud.facerecognition.showdata.service.MainDataService;
import com.koocloud.facerecognition.showdata.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MainDataServiceImpl  implements MainDataService {

    @Autowired
    MainDataRepositoty mainDataRepositoty;

    @Override
    public ResponseTemplate mainDataList(PageVo pageVo) throws Exception {
        PageRequest pageRequest=PageRequest.of(pageVo.getPage()-1,pageVo.getLimit());   //获取page(jpa页码从0开始,-1)页的 limit条数据
        //查询分页数据
        Page<MainData> pageInfo = mainDataRepositoty.findAll(pageRequest);

        //获取总数
        Long count = pageInfo.getTotalElements();

        return ResponseTemplate.builder().code(0).count(count).message("请求成功").data(pageInfo.getContent()).build();
    }
}
