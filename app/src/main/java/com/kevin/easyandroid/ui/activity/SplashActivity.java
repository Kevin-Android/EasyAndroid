package com.kevin.easyandroid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.kevin.easyandroid.R;

public class SplashActivity extends AppCompatActivity implements Animator.AnimatorListener {

    private LottieAnimationView lottieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在你的activity的onCreate方法下添加下列代码（如果是自定义的BaseActivity的话，就在你自定义的Activity下添加，这样的话只要其他的继承你的BaseActivity，只需要改一次就可以）
//        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
//            getWindow().getDecorView().setSystemUiVisibility(View.GONE);
//            getWindow().getDecorView().setBackgroundColor(getColor(R.color.white));
//        } else if (Build.VERSION.SDK_INT >= 19) {
//            //for new api versions.
//            View decorView = getWindow().getDecorView();
//
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
//            decorView.setSystemUiVisibility(uiOptions);
//            decorView.setBackgroundColor(getColor(R.color.white));
//
//        }
        setContentView(R.layout.activity_splash);
        lottieView = findViewById(R.id.lottieView);
        lottieView.addAnimatorListener(this);
//        lottieView.setAnimation(R.raw.android);
        lottieView.playAnimation();
    }

    @Override
    public void onAnimationStart(Animator animator) {
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onAnimationCancel(Animator animator) {
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
    }
}