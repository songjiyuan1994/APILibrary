package com.songjiyuan.api.Activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.songjiyuan.api.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TransitionActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    Button btnBack;

    @Override
    public void initParams(Bundle params) {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int bindLayout() {
        // 允许使用transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = new Explode();
        transition.setDuration(1000)
                .setInterpolator(new BounceInterpolator());
        // 设置当前Activity进入时动画
        getWindow().setEnterTransition(transition);
        return R.layout.activity_transition;
    }

    @Override
    public void initView() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.btn_back)
    public void onClick() {
        finishAfterTransition();
    }
}
