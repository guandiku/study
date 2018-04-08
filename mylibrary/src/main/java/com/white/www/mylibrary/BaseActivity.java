package com.white.www.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.View;

/**
 * Created by White on 2018/4/7.
 * <p>
 * Version 1.0
 * <p>
 * Description:整合应用的BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局layout
        setContentView();
        // 初始化头部
        initTitle();
        //初始化界面
        initView();
        //初始化数据
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTitle();

    protected abstract void setContentView();


    protected void startActvity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected <T extends View> T viewById(@IdRes int id){
        return (T)findViewById(id);
    }

    //只能放一些通用的方法，每个activity都需要使用的方法




}
