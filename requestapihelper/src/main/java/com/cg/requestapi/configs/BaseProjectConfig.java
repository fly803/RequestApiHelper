package com.cg.requestapi.configs;

import android.app.Application;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/20
 */
public class BaseProjectConfig {
    public static  int successCode = 0;
    public static  boolean isNetRequestInterceptor = true;
    public static  boolean isBaseURLInterceptor = true;
    public static  boolean isHeaderInterceptor = false;
    public static  String baseURL = "http://ip.taobao.com/";
    public static  String TAG = "BaseProjectConfig";
    public static Map<Integer, String> mapServerReturnCode = new HashMap<Integer, String>();
    public static Map<String, String> mapBaseURL = new HashMap<String, String>();
    public static String loadingMessage = "数据请求中...";
    

    /**
     * 初始化BaseProject
     * @param application 应用的application
     * @param isNetRequestInterceptorOpen 是否打印网络请求log
     * @param isBaseURLInterceptorOpen 是否开启BaseURL过滤器
     * @param isHeaderInterceptorOpen 是否开启请求头过滤器
     * @param baseurl 网络请求baseurl
     * @param successcode 网络请求成功code，例如200
     * @param tag 打印log的tag
     * @param loadingmessage 数据加载loading的显示语
     * @param serverReturnCodeMap 应用工程自定义的API异常
     * @param baseURLMap 应用工程自己的多个BaseURL
     */
    public static void init(Application application, 
                            boolean isNetRequestInterceptorOpen, boolean isBaseURLInterceptorOpen,boolean isHeaderInterceptorOpen,
                            String baseurl, int successcode, String tag,String loadingmessage, 
                            Map<Integer, String> serverReturnCodeMap,Map<String, String> baseURLMap){
        isNetRequestInterceptor = isNetRequestInterceptorOpen;
        isBaseURLInterceptor = isBaseURLInterceptorOpen;
        isHeaderInterceptor = isHeaderInterceptorOpen;
        baseURL = baseurl;
        successCode = successcode;
        TAG = tag;
        loadingMessage = loadingmessage;
        mapServerReturnCode = serverReturnCodeMap;
        mapBaseURL = baseURLMap;
    }
    
    /** 
     * 根据异常码，显示异常原因
     * @date   2019/3/20
     * @version 1.0
     * @param  * @param null
     * @return  
     */ 
    public static String getApiReason(int code){
        String reason = "未知";
        try {
            for(Map.Entry<Integer, String> entry : mapServerReturnCode.entrySet()){
                if(entry.getKey() == code){
                    reason = entry.getValue();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return reason;
    }
    
    public static boolean isServerExcepition(int code){
        return mapServerReturnCode.containsKey(code);
    }
}
