package com.cg.requesttest;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cg.requesttest.adapter.MainInterfaceListAdapter;
import com.cg.requesttest.data.MainInterfaceItem;
import com.chad.library.adapter.base.BaseQuickAdapter;

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
        setContentView(R.layout.activity_main);
        initView();
        initMainInterfaceAdapter();
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


        for (int i = 14; i < 18; i++) {
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
            case "interfaceTest":
                interfaceTest();
                break;
            default:
                Snackbar.make(mRvDataIndex, "没有方法执行", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void interfaceTest() {
//        RequestAPI.getInstance().toSubscribe(((RequestApiInterface) (RequestAPI.getInstance().getApi(RequestApiInterface.class))).psalms(3),
//                new ProgressSubscriber<BaseResponse<MyResponse.DataBean>>(new SubscriberOnNextListener<MyResponse.DataBean>() {
//                    @Override
//                    public void onNext(MyResponse.DataBean myResponseException) {
//                        Log.d(AppConfig.TAG, "MainActivity onNext: " + myResponseException.getName());
//                        Snackbar.make(mRvDataIndex, "getEnvProportion:" + myResponseException.getPhone(), Snackbar.LENGTH_SHORT).show();
//                    }
//                }, MainActivity.this));
    }

}
