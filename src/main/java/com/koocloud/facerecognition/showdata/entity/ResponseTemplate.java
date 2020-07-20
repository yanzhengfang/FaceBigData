package com.koocloud.facerecognition.showdata.entity;

/**
 * 返回json数据模板格式
 */
public class ResponseTemplate {

    public Integer code;

    public String message;

    public Long count;

    public Object data;

    ResponseTemplate(Integer code,String message,Long count, Object data) {
        this.code=code;
        this.message=message;
        this.count=count;
        this.data=data;
    }
    public static ResponseTemplateBuilder builder() {
        return new ResponseTemplateBuilder();
    }

    public static class ResponseTemplateBuilder {
        private Integer code;
        private String message;
        public Long count;
        private Object data;

        ResponseTemplateBuilder() {
        }

        public ResponseTemplateBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public ResponseTemplateBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseTemplateBuilder count(Long count) {
            this.count = count;
            return this;
        }

        public ResponseTemplateBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public ResponseTemplate build() {
            return new ResponseTemplate(this.code, this.message,this.count,this.data);
        }

        public String toString() {
            return "User.UserBuilder(code=" + this.code + ", message=" + this.message + ", count=" + this.count + ",data=" + this.data + ")";
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
