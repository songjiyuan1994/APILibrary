package com.songjiyuan.api.Activity;

import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.songjiyuan.api.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener {


    @BindView(R.id.btn_explode)
    Button btnExplode;
    @BindView(R.id.btn_slide)
    Button btnSlide;
    @BindView(R.id.radio_interpolator)
    RadioGroup radioInterpolator;
    @BindView(R.id.seek_duration)
    SeekBar seekDuration;
    @BindView(R.id.text2)
    TextView text2;
    private TimeInterpolator timeInterpolator = null;
    private int duration = 1500;
    private Transition transition;

    @Override
    public void initParams(Bundle params) {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int bindLayout() {
        // 允许使用transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_animation;
    }

    @Override
    public void initView() {
        timeInterpolator = new AccelerateDecelerateInterpolator();
        radioInterpolator.setOnCheckedChangeListener(this);
        seekDuration.setProgress(duration);
        seekDuration.setOnSeekBarChangeListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn_explode, R.id.btn_slide, R.id.btn_fade})
    public void onClick(View view) {
        if (timeInterpolator != null) {
            // 共享多个元素
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair.create(view, "transition"),
                    Pair.create(text2, "text"));
            //  共享一个元素
            //  startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
            //       this, view, "transition").toBundle());
            Intent intent = new Intent(this, TransitionActivity.class);

            switch (view.getId()) {
                case R.id.btn_explode:
                    transition = new Explode();
                    transition.setDuration(duration)
                            .setInterpolator(timeInterpolator);
                    // 设置动画开始时间，延迟n毫秒播放。类型：long
                    // .setStartDelay()
                    // 设置动画的运行路径
                    // .setPathMotion()
                    // 改变动画 出现/消失 的模式。Visibility.MODE_IN:进入；Visibility.MODE_OUT：退出。
                    // .setMode()
                    // 设置动画的监听事件
                    // .addListener();
                    break;
                case R.id.btn_slide:
                    transition = new Slide();
                    transition.setDuration(duration)
                            .setInterpolator(timeInterpolator);
                    break;
                case R.id.btn_fade:
                    transition = new Fade();
                    transition.setDuration(duration)
                            .setInterpolator(timeInterpolator);
                    break;
            }
            // 设置当前Activity退出时动画
            getWindow().setExitTransition(transition);
            // 设置当前Activity进入时动画
            getWindow().setEnterTransition(transition);
            getWindow().setSharedElementEnterTransition(transition);
            getWindow().setSharedElementExitTransition(transition);
            startActivity(intent, options.toBundle());
        } else {
            Log.e(TAG, "onClick: 获取Interpolator失败");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radio_ip1:
                // 设置子元素的动画
                timeInterpolator = new AccelerateDecelerateInterpolator();
                break;
            case R.id.radio_ip2:
                timeInterpolator = new AccelerateInterpolator();
                break;
            case R.id.radio_ip3:
                timeInterpolator = new AnticipateInterpolator();
                break;
            case R.id.radio_ip4:
                timeInterpolator = new AnticipateOvershootInterpolator();
                break;
            case R.id.radio_ip5:
                timeInterpolator = new BounceInterpolator();
                break;
            case R.id.radio_ip6:
                timeInterpolator = new CycleInterpolator(10f);
                break;
            case R.id.radio_ip7:
                timeInterpolator = new DecelerateInterpolator();
                break;
            case R.id.radio_ip8:
                timeInterpolator = new LinearInterpolator();
                break;
            case R.id.radio_ip9:
                timeInterpolator = new OvershootInterpolator();
                break;
            default:
                timeInterpolator = new AccelerateDecelerateInterpolator();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        duration = progress;
        Log.d(TAG, "onProgressChanged: " + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
