package com.cg.requesttest.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.cg.requestapi.base.BaseResponse;
import com.cg.requestapi.request.RequestAPI;
import com.cg.requestapi.request.retrofit.exception.ServerException;
import com.cg.requestapi.request.retrofit.interfaces.SubscriberOnNextListener;
import com.cg.requestapi.request.retrofit.subscriber.ProgressSubscriber;
import com.cg.requesttest.AppApplication;
import com.cg.requesttest.api.RequestApiInterface;
import com.cg.requesttest.data.response.AppList;

import java.util.List;

import static com.cg.requestapi.request.RequestAPI.*;

/**
 * @author sam
 * @version 1.0
 * @date 2019/5/10
 */
public class AppListRepository {
    private static AppListRepository instance;
    private static MutableLiveData<List<AppList.DataBean>> liveAppList;
    private Application mApplication;

    public AppListRepository(Application application) {
        this.mApplication = application;
        getAppList();
    }



    public MutableLiveData<List<AppList.DataBean>> getLiveAppList() {
        return liveAppList;
    }

    public void getAppList() {
        getInstance().toSubscribe(((RequestApiInterface) (getInstance().getApi(RequestApiInterface.class))).getApplist(),
                new ProgressSubscriber<BaseResponse<List<AppList.DataBean>>>(new SubscriberOnNextListener<List<AppList.DataBean>>() {
                    @Override
                    public void onNext(List<AppList.DataBean> myResponse) {
                        liveAppList.setValue(myResponse);
                    }

                    @Override
                    public void onSeverError(int code, String msg) {
                        
                    }

                }, mApplication.getApplicationContext()));
    }
   
}