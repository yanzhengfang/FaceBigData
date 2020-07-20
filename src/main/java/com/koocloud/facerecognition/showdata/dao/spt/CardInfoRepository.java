package com.koocloud.facerecognition.showdata.dao.spt;

import com.koocloud.facerecognition.showdata.entity.spt.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardInfoRepository extends JpaRepository<CardInfo,Integer> {

    @Query(value = "select cardInfo.* from cardInfo where cardInfo.appId = :appid", nativeQuery = true)
    CardInfo getSingleByAppid(@Param("appid") String appid);
}
