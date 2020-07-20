package com.koocloud.facerecognition.showdata.controller;

import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.service.MainDataService;
import com.koocloud.facerecognition.showdata.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainDataRest")
public class MainDataController {

    @Autowired
    MainDataService mainDataService;

    /**
     * 完整账户数据库列表数据
     * @param pageVo
     * @return
     */
    @PostMapping("/mainDataList")
    public ResponseTemplate mainDataList(PageVo pageVo) throws Exception {
        return  mainDataService.mainDataList(pageVo);
    }
}
