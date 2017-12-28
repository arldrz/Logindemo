package com.haozhu.logindemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/12/4
 * 欢迎页面
 */

public class WelcomeActivity extends AppCompatActivity {
    private PrefManager prefManager;
    private ImageView mBgView4;
    private ImageView mBgView3;
    private ImageView mBgView2;
    private ImageView mBgView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        //在setContentView()前检查是否第一次运行
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        //让状态栏透明
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_welcome_activity);
        assignViews();
        initView();
        //让状态栏透明
        changeStatusBarColor();
    }

    private void changeStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void assignViews() {
        mBgView4 = (ImageView) findViewById(R.id.login_bg_image4);
        mBgView3 = (ImageView) findViewById(R.id.login_bg_image3);
        mBgView2 = (ImageView) findViewById(R.id.login_bg_image2);
        mBgView1  = (ImageView) findViewById(R.id.login_bg_image1);
    }
    private void initView() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mBgView1, "alpha", 1.0f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mBgView2, "alpha", 0f, 1.0f);
        ObjectAnimator animatorScale1 = ObjectAnimator.ofFloat(mBgView1, "scaleX", 1.0f, 1.3f);
        ObjectAnimator animatorScale2 = ObjectAnimator.ofFloat(mBgView1, "scaleY", 1.0f, 1.3f);
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.setDuration(5000);
        animatorSet1.play(animator1).with(animator2).with(animatorScale1).with(animatorScale2);


        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mBgView2, "alpha", 1.0f, 0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mBgView3, "alpha", 0f, 1.0f);
        ObjectAnimator animatorScale3 = ObjectAnimator.ofFloat(mBgView2, "scaleX", 1.0f, 1.3f);
        ObjectAnimator animatorScale4 = ObjectAnimator.ofFloat(mBgView2, "scaleY", 1.0f, 1.3f);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(5000);
        animatorSet2.play(animator3).with(animator4).with(animatorScale3).with(animatorScale4);


        ObjectAnimator animator5 = ObjectAnimator.ofFloat(mBgView3, "alpha", 1.0f, 0f);
        ObjectAnimator animator6 = ObjectAnimator.ofFloat(mBgView4, "alpha", 0f, 1.0f);
        ObjectAnimator animatorScale5 = ObjectAnimator.ofFloat(mBgView3, "scaleX", 1.0f, 1.3f);
        ObjectAnimator animatorScale6 = ObjectAnimator.ofFloat(mBgView3, "scaleY", 1.0f, 1.3f);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.setDuration(5000);
        animatorSet3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 放大的View复位
                mBgView1.setScaleX(1.0f);
                mBgView1.setScaleY(1.0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet3.play(animator5).with(animator6).with(animatorScale5).with(animatorScale6);


        ObjectAnimator animator7 = ObjectAnimator.ofFloat(mBgView4, "alpha", 1.0f, 0f);
        ObjectAnimator animator8 = ObjectAnimator.ofFloat(mBgView1, "alpha", 0f, 1.0f);
        ObjectAnimator animatorScale7 = ObjectAnimator.ofFloat(mBgView4, "scaleX", 1.0f, 1.3f);
        ObjectAnimator animatorScale8 = ObjectAnimator.ofFloat(mBgView4, "scaleY", 1.0f, 1.3f);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.setDuration(5000);
        animatorSet4.play(animator7).with(animator8).with(animatorScale7).with(animatorScale8);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorSet1, animatorSet2, animatorSet3, animatorSet4);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 将放大的View 复位
                mBgView2.setScaleX(1.0f);
                mBgView2.setScaleY(1.0f);
                mBgView3.setScaleX(1.0f);
                mBgView3.setScaleY(1.0f);
                mBgView4.setScaleX(1.0f);
                mBgView4.setScaleY(1.0f);
                // 循环播放
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.start();
    }

public void loginMain(View v){
    prefManager.setFirstTimeLaunch(false);
    startActivity(new Intent(this,LoginActivity.class));
    finish();
}
public void register(View v){
    prefManager.setFirstTimeLaunch(false);
    startActivity(new Intent(this,RegisterActivity.class));
    finish();
}

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(true);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}