package com.koocloud.facerecognition.showdata.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String toIndexPage() {
        return "index";
    }


    /**
     * 本地特征页面
     * @return
     */
    @RequestMapping("/localFeaturePage")
    public String toLocalFeaturePage() {
        return "feature/localIndex";
    }


    /**
     * 远程特征页面
     * @return
     */
    @RequestMapping("/remoteFeaturePage")
    public String toRemoteFeaturePage() {
        return "feature/remoteIndex";
    }


    /**
     * spt
     * @return
     */
    @RequestMapping("/sptDataPage")
    public String toSptDataPage() {
        return "sptData/index";
    }


    /**
     * 完整账户
     * @return
     */
    @RequestMapping("/completeAccountPage")
    public String tocCompleteAccountPage() {
        return "completeAccount/index";
    }

    /**
     * 数据分析
     * @return
     */
    @RequestMapping("/dataAnalysisPage")
    public String toDataAnalysisPage() {
        return "dataAnalysis/index";
    }

}
