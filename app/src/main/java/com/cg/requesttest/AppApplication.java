package com.cg.requesttest;

import com.cg.requestapi.BaseApplication;
import com.cg.requestapi.configs.BaseProjectConfig;
import com.cg.requesttest.api.AppConfig;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2018/3/2
 */
public class AppApplication extends BaseApplication {
    private static AppApplication application;
    private static final int MEMORY_SIZE = 5 * 1024 * 1024;
    private static final int DISK_SIZE = 20 * 1024 * 1024;
    
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initBaseProjectConfig();//必须初始化
    }

    public static AppApplication getInstance() {
        return application;
    }

    /**
     * 初始化BaseProject
     * application 应用的application
     * isLeakCanary 是否集成内存检测库LeakCanary
     * isCrashHandel 是否集成全局Crash监控
     * isNetRequestInterceptor 是否打印网络请求log
     * isBaseUrl 是否开启BaseURL过滤器
     * isHeaderInterceptorOpen 是否开启请求头过滤器
     * stackview 栈结构分析样式
     * baseurl 网络请求baseurl
     * successcode 网络请求成功code，例如200
     * tag 打印log的tag
     * loadingmessage 数据加载loading的显示语
     * mapServerReturnCodeMap 应用工程自定义的API异常
     * mapBaseURL 应用工程多个自己的BaseURL
     */
    private void initBaseProjectConfig(){
        final boolean isLeakCanary = false;
        final boolean isCrashHandel = false;
        final boolean isNetRequestInterceptorOpen = true;
        final boolean isBaseURLInterceptorOpen = true;
        final boolean isHeaderInterceptorOpen = false;
        final String baseURL = "http://116.62.60.235/";
        final int successCode = 0;
        final String tag = AppConfig.TAG;
        Map<Integer, String> mapServerReturnCodeMap = getServerReturnCodeMap();
        Map<String, String> mapBaseURL = getBaseURLMap();
        final String loadingMessage = "加载中...";
        BaseProjectConfig.init(this,
                isNetRequestInterceptorOpen,
                isBaseURLInterceptorOpen,
                isHeaderInterceptorOpen,
                baseURL,
                successCode,
                tag,
                loadingMessage,
                mapServerReturnCodeMap,
                mapBaseURL);
    }

    /** 
     * 外部工程传自己的API异常码给类库工程
     * @date   2019/3/20
     * @version 1.0
     * @param  * @param null
     * @return  
     */ 
    private Map<Integer, String> getServerReturnCodeMap(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(101, "消息101");
        map.put(102, "消息102");
        map.put(103, "消息101");
        map.put(104, "消息102");
        map.put(105, "消息101");
        map.put(106, "消息102");
        map.put(999, "不明原因999");
        return map;
    }

    /**
     * 外部工程传如果需要多BaseURL的时候，传需要切换的BaseURL给类库工程
     * @date   2019/3/20
     * @version 1.0
     * @param  * @param null
     * @return
     */
    private Map<String, String> getBaseURLMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("douban", "https://api.douban.com/");
        map.put("gank", "https://gank.io/");
        return map;
    }
}


