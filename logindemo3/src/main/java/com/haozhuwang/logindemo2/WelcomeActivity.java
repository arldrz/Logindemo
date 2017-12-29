package com.haozhuwang.logindemo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/12/4
 * 欢迎页面
 */

public class WelcomeActivity extends Activity implements Runnable{



    private PrefManager prefManager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()){
            launchHomeScreen();
            return;
        }
        setContentView(R.layout.activity_welcome_activity);
        new  Thread(this).start();
    }

    @Override
    public void run() {
        try{
            /**
             * 延迟1秒时间
             */
            Thread.sleep(1000);
            /**
             *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
             */
            if (!prefManager.isFirstTimeLaunch()) {
                Intent intent2 = new Intent();
                intent2.setClass(WelcomeActivity.this, MainActivity.class);
                startActivity(intent2);
            }

            prefManager.setFirstTimeLaunch(false);

            finish();

        } catch (InterruptedException e) {
        }
    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(true);
        startActivity(new Intent(this,GuideActivity.class));
        finish();
    }
}