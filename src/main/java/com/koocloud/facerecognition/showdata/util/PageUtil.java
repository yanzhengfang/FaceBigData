package com.koocloud.facerecognition.showdata.util;

import com.koocloud.facerecognition.showdata.vo.PageVo;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author anna
 * @create 2020-07-18 23:01
 */
public class PageUtil {
    /**
     * 计算分页条数
     * @param pageVo
     */
    public static void countStart(PageVo pageVo){
        if(pageVo.getPage() != null && pageVo.getPage()>0 && pageVo.getLimit() != null && pageVo.getLimit()>0){
            pageVo.setStart((pageVo.getPage()-1)*pageVo.getLimit());
        }
    }

    /**
     * redis列表分页统一处理方法
     * @param redisList redis查询到的value 的list集合
     * @param res 返回结果list<Map<String,String>
     * @param keys redis查询到的前缀key的set 集合
     * @param pageVo 封装分页查询条件,主要包含 page:第几页 start:从第几页开始 limit 每页多少条
     * @param redisPrefix 返回数据时 需要去掉的redis key值的前缀
     */
    public static void redisListDataOperation(List<String> redisList, List<Map<String,String>> res, Set<String> keys,  PageVo pageVo, String redisPrefix){
        Integer end = 0;
        Integer start = 0;
        if(redisList != null){
            //计算起始位置
            PageUtil.countStart(pageVo);

            //开始索引
            start  = pageVo.getStart();
            Integer sum = pageVo.getStart()+pageVo.getLimit();
            if(start <= redisList.size() && sum<=redisList.size()){ //start,sum 在list索引范围内
                end = sum;
            }else if(start <= redisList.size()){//sum 超出 list 索引,start在索引之内
                end = redisList.size();
            }//其他情况视为无数据

            //拼接key,value
            List<String> keyList = new ArrayList<>(keys);
            if(end > 0 ){//代表可以进行字符串的截取操作(有数据
                for (int i  = start; i<end ; i++){
                    Map<String,String> map = new HashMap<>();
                    String value = keyList.get(i);
                    if(!StringUtils.isEmpty(redisPrefix)){
                        value = value.substring(redisPrefix.length()-1,value.length());
                    }
                    map.put(value,redisList.get(i));
                    res.add(map);
                }
            }
        }
    }
}
