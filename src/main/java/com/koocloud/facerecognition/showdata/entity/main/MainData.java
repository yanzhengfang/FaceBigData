package com.koocloud.facerecognition.showdata.entity.main;

import com.koocloud.facerecognition.showdata.entity.MainAttribute;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@Entity
public class MainData   extends MainAttribute  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键

    private String faceFeature;//人脸特征

    private String faceImg;//人脸图片数据

    private Date createTime;//创建时间

    /**
     * 设置基本信息
     * @param faceFeature 人脸特征值
     * @param faceImg 人脸图片
     * @param createTime 创建时间
     * @return
     */
    public void setInfo(MainAttribute mainAttribute,String faceFeature, String faceImg, Date createTime){
        this.createTime = createTime;
        this.faceImg = faceImg;
        this.faceFeature = faceFeature;

        //得到类对象 -- 此处获取其父类对象
        Class mainClass = (Class) mainAttribute.getClass().getSuperclass();
        /* 得到父类中的所有属性集合 */
        Field[] fields = mainClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true); // 设置些私有属性是可以访问的
            try{
                //获取属性值
                Object filedValue = field.get(mainAttribute);
                String fieldName= field.getName();
                Class filedType = field.getType();

                StringBuffer sb = new StringBuffer();
                sb.append("set");
                sb.append(fieldName.substring(0,1).toUpperCase());
                sb.append(fieldName.substring(1,fieldName.length()));
                Method method = ReflectionUtils.findMethod(this.getClass(),sb.toString(),filedType);
                //将属性值设置到本类的属性中
                ReflectionUtils.invokeMethod(method, this, filedValue);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

    public MainData() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaceFeature() {
        return faceFeature;
    }

    public void setFaceFeature(String faceFeature) {
        this.faceFeature = faceFeature;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }
}
