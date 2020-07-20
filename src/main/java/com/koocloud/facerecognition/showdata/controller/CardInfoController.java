package com.koocloud.facerecognition.showdata.controller;

import com.koocloud.facerecognition.showdata.entity.ResponseTemplate;
import com.koocloud.facerecognition.showdata.service.CardInfoService;
import com.koocloud.facerecognition.showdata.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.Card;

/**
 * spt
 */
@RestController
@RequestMapping("/sptInfoRest")
public class CardInfoController {
    @Autowired
    CardInfoService cardInfoService;

    /**
     * spt数据库列表数据
     * @param pageVo
     * @return
     */
    @PostMapping("/sptList")
    public ResponseTemplate sptList(PageVo pageVo) throws Exception {
        return  cardInfoService.cardList(pageVo);
    }
}
