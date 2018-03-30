package com.songjiyuan.api.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.songjiyuan.api.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_animation)
    Button btnAnimation;
    @BindView(R.id.btn_app)
    Button btnApp;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.btn_animation, R.id.btn_app})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_animation:
                startActivity(AnimationActivity.class);
                break;
            case R.id.btn_app:
                break;
        }
    }
}
