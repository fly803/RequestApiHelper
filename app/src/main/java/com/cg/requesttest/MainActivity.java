package com.cg.requesttest;

import android.annotation.TargetApi;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cg.requestapi.base.BaseResponse;
import com.cg.requestapi.configs.BaseProjectConfig;
import com.cg.requestapi.request.RequestAPI;
import com.cg.requestapi.request.retrofit.exception.ServerException;
import com.cg.requestapi.request.retrofit.interfaces.SubscriberOnNextListener;
import com.cg.requestapi.request.retrofit.subscriber.ProgressSubscriber;
import com.cg.requestapi.view.CommonLoading;
import com.cg.requesttest.adapter.MainInterfaceListAdapter;
import com.cg.requesttest.api.AppConfig;
import com.cg.requesttest.api.RequestApiInterface;
import com.cg.requesttest.data.MainInterfaceItem;
import com.cg.requesttest.data.response.AppList;
import com.cg.requesttest.data.response.AppRecommend;
import com.cg.requesttest.data.response.MyResponse;
import com.cg.requesttest.data.response.SeachResult;
import com.cg.requesttest.data.viewmodel.ApplistViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面，测试BaseProject的相关方法
 * @author sam
 * @version 1.0
 * @date 2016/6/8
 */
public class MainActivity extends AppCompatActivity {
    RecyclerView mRvDataIndex;
    List<MainInterfaceItem> listMainInterfaceItem = new ArrayList<>();
    ApplistViewModel mApplistViewModel;

    @ColorInt
    private static final int[] BG_COLORS = {
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87, 
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        mApplistViewModel = ViewModelProviders.of(this).get(ApplistViewModel.class);
        initView();
        initMainInterfaceAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        mRvDataIndex = (RecyclerView) findViewById(R.id.rvDataIndex);
    }

    private void initMainInterfaceAdapter() {
        MainInterfaceListAdapter dataIndexBaseQuickAdapter = new MainInterfaceListAdapter(R.layout.list_maininterface_item, initData());
        //        mRvDataIndex.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        //        mRvDataIndex.setLayoutManager(new LinearLayoutManager(this));
        mRvDataIndex.setAdapter(dataIndexBaseQuickAdapter);
        dataIndexBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                runMethod(listMainInterfaceItem.get(position).getMethod());
            }
        });
    }

    private List<MainInterfaceItem> initData() {
        MainInterfaceItem mainInterfaceItem0 = new MainInterfaceItem();
        mainInterfaceItem0.setName("Call方式Get请求");
        mainInterfaceItem0.setMethod("callTypeGet");
        mainInterfaceItem0.setBackgroundColor(BG_COLORS[0]);
        listMainInterfaceItem.add(mainInterfaceItem0);

        MainInterfaceItem mainInterfaceItem1 = new MainInterfaceItem();
        mainInterfaceItem1.setName("Call方式Post请求");
        mainInterfaceItem1.setMethod("callTypePost");
        mainInterfaceItem1.setBackgroundColor(BG_COLORS[1]);
        listMainInterfaceItem.add(mainInterfaceItem1);

        MainInterfaceItem mainInterfaceItem2 = new MainInterfaceItem();
        mainInterfaceItem2.setName("Observable方式RxGet请求");
        mainInterfaceItem2.setMethod("rxGet");
        mainInterfaceItem2.setBackgroundColor(BG_COLORS[2]);
        listMainInterfaceItem.add(mainInterfaceItem2);

        MainInterfaceItem mainInterfaceItem3 = new MainInterfaceItem();
        mainInterfaceItem3.setName("Observable方式RxPost请求");
        mainInterfaceItem3.setMethod("rxPost");
        mainInterfaceItem3.setBackgroundColor(BG_COLORS[3]);
        listMainInterfaceItem.add(mainInterfaceItem3);

        MainInterfaceItem mainInterfaceItem03 = new MainInterfaceItem();
        mainInterfaceItem03.setName("项目接口测试");
        mainInterfaceItem03.setMethod("interfaceTest");
        mainInterfaceItem03.setBackgroundColor(BG_COLORS[12]);
        listMainInterfaceItem.add(mainInterfaceItem03);

        MainInterfaceItem mainInterfaceItem4 = new MainInterfaceItem();
        mainInterfaceItem4.setName("App列表测试");
        mainInterfaceItem4.setMethod("appListinterfaceTest");
        mainInterfaceItem4.setBackgroundColor(BG_COLORS[13]);
        listMainInterfaceItem.add(mainInterfaceItem4);

        MainInterfaceItem mainInterfaceItem5 = new MainInterfaceItem();
        mainInterfaceItem5.setName("App推荐列表");
        mainInterfaceItem5.setMethod("appListinterfaceTest");
        mainInterfaceItem5.setBackgroundColor(BG_COLORS[14]);
        listMainInterfaceItem.add(mainInterfaceItem5);

        MainInterfaceItem mainInterfaceItem6 = new MainInterfaceItem();
        mainInterfaceItem6.setName("App搜索");
        mainInterfaceItem6.setMethod("seachApp");
        mainInterfaceItem6.setBackgroundColor(BG_COLORS[15]);
        listMainInterfaceItem.add(mainInterfaceItem6);

        MainInterfaceItem mainInterfaceItem7 = new MainInterfaceItem();
        mainInterfaceItem7.setName("liveData得到applist");
        mainInterfaceItem7.setMethod("liveDataApplist");
        mainInterfaceItem7.setBackgroundColor(BG_COLORS[16]);
        listMainInterfaceItem.add(mainInterfaceItem7);
        
        MainInterfaceItem mainInterfaceItem8 = new MainInterfaceItem();
        mainInterfaceItem8.setName("软件列表测试");
        mainInterfaceItem8.setMethod("appRecommendListinterfaceTest");
        mainInterfaceItem8.setBackgroundColor(BG_COLORS[17]);
        listMainInterfaceItem.add(mainInterfaceItem8);

        for (int i = 27; i < 38; i++) {
            MainInterfaceItem mainInterfaceItem = new MainInterfaceItem();
            mainInterfaceItem.setName("待添加操作" + i);
            mainInterfaceItem.setMethod("");
//            mainInterfaceItem.setBackgroundColor(BG_COLORS[0]);
            listMainInterfaceItem.add(mainInterfaceItem);
        }

        return listMainInterfaceItem;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void runMethod(String methodName) {
        switch (methodName) {
            case "liveDataApplist":
                liveDataApplist();
                break;
            case "seachApp":
                seachApp();
                break;
            case "appRecommendListinterfaceTest":
                appRecommendListinterfaceTest();
                break;
            case "appListinterfaceTest":
                appListinterfaceTest();
                break;
            case "interfaceTest":
                interfaceTest();
                break;
            default:
                Snackbar.make(mRvDataIndex, "没有方法执行", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void liveDataApplist() {
        mApplistViewModel.getLiveAppList().observe(this, new Observer<List<AppList.DataBean>>() {
            @Override
            public void onChanged(@Nullable List<AppList.DataBean> appListData) {
                if (appListData != null) {
                    Log.d("cg", "onChanged: "+appListData.get(0).getItems().get(0).toString());
                }
            }
        });
    }
    
    private void interfaceTest() {
        RequestAPI.getInstance().toSubscribe(((RequestApiInterface) (RequestAPI.getInstance().getApi(RequestApiInterface.class))).psalms(3),
                new ProgressSubscriber<BaseResponse<MyResponse.DataBean>>(new SubscriberOnNextListener<MyResponse.DataBean>() {
                    @Override
                    public void onNext(MyResponse.DataBean myResponseException) {
                        Snackbar.make(mRvDataIndex, "getEnvProportion:" + myResponseException.getPhone(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSeverError(int code, String msg) {
                        
                    }

                    @Override
                    public void showDialog() {

                    }

                    @Override
                    public void dismissDialog() {

                    }


                }, MainActivity.this));
    }

    private void seachApp() {
        RequestAPI.getInstance().toSubscribe(((RequestApiInterface) (RequestAPI.getInstance().getApi(RequestApiInterface.class))).seachApp("sfagagg","百度"),
                new ProgressSubscriber<BaseResponse<AppList.DataBean>>(new SubscriberOnNextListener<AppList.DataBean>() {
                    @Override
                    public void onNext(AppList.DataBean myResponse) {
                        Snackbar.make(mRvDataIndex, "onNext:" + myResponse.getItems().get(0).getName(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSeverError(int code, String msg) {
                        
                    }

                    @Override
                    public void showDialog() {

                    }

                    @Override
                    public void dismissDialog() {

                    }

                }, AppApplication.getInstance().getApplicationContext(),MainActivity.this));
    }
    
    private void appListinterfaceTest() {
        RequestAPI.getInstance().toSubscribe(((RequestApiInterface) (RequestAPI.getInstance().getApi(RequestApiInterface.class))).appListinterfaceTest(),
                new ProgressSubscriber<BaseResponse<List<AppList.DataBean>>>(new SubscriberOnNextListener<List<AppList.DataBean>>() {
                    @Override
                    public void onNext(List<AppList.DataBean> myResponse) {
                        Snackbar.make(mRvDataIndex, "onNext:" + myResponse.get(0).getItems().get(0).getName(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSeverError(int code, String msg) {
                        
                    }

                    @Override
                    public void showDialog() {

                    }

                    @Override
                    public void dismissDialog() {

                    }

                }, MainActivity.this));
    }

    private void appRecommendListinterfaceTest() {
        RequestAPI.getInstance().toSubscribe(((RequestApiInterface) (RequestAPI.getInstance().getApi(RequestApiInterface.class))).appRecommendListinterfaceTest(10,"xxoo"),
                new ProgressSubscriber<BaseResponse<List<AppRecommend.DataBean>>>(new SubscriberOnNextListener<List<AppRecommend.DataBean>>() {
                    @Override
                    public void onNext(List<AppRecommend.DataBean> myResponse) {
                        Log.d("cg", " appRecommendListinterfaceTest onNext: "+myResponse.get(0).getAppName());
                        Snackbar.make(mRvDataIndex, "onNext:" + myResponse.get(0).getAppName(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSeverError(int code, String msg) {
                        
                    }

                    @Override
                    public void showDialog() {

                    }

                    @Override
                    public void dismissDialog() {

                    }

                }, MainActivity.this));
    }

    @Subscribe
    public void onEvent(DialogEvent event) {
        //处理逻辑
        Log.i("ccc", "onEvent:" + event.needShow);
        CommonLoading commonLoading = new CommonLoading(this, BaseProjectConfig.loadingMessage);
        if (event.needShow){
            commonLoading.showLoading();
        }else{
            commonLoading.closeLoading();
        }
    }

}
