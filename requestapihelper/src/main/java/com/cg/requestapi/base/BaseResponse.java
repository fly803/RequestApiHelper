package com.cg.requestapi.base;

import java.util.List;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/14
 * https://blog.csdn.net/mjb00000/article/details/79297677
 * https://www.jianshu.com/p/337c06f322c2
 * https://www.jianshu.com/p/c105a4177982
 * 可能错误情况
    一般情况这是我们的返回json格式：
    {
        "code":200,
        "msg":"成功",
        "data":{}
    }
    失败的时候返回的实体又是一个数组，这样子就会抱一个json解析异常拿不多失败的状态码和提示信息
    {
    "code":200,
    "msg":"成功",
    "data":[]
    }
    解决：
    方法1：我们可以让后台返回的json数据中的data永远是个数组。
    方法2：1、自定义Gson响应体变换器 2、自定义一个响应变换工厂 继承自 retrofit的 converter.Factory
           3、在我们的自定义的Rxjava订阅者 subscriber中的onError（）中加入我们刚才定义的ResultException。
 */
public class BaseResponse<T> {
    private int code;
    private String message;
    private int err_code;
    private String err_msg;
    private T data;
    private int dataType;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    
    /**
     * 扩展字段
     * 0:data为对象
     * 1:data为集合
     * 2:date为空或者null字段
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", dataType=" + dataType +
                '}';
    }
    
    public String getNewJsonStrin(){
        return "{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", dataType=" + dataType +
                '}'; 
    }
}
