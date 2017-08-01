package com.shenni.torontofoods;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.shenni.torontofoods.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StartActivity extends BaseActivity {
    private static final int ANIMATION_TIME = 2000;
    private static final float SCALE_END = 1.13F;
    @Bind(R.id.iv_entry)
    ImageView ivEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏代码
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        //这里是第二种方法 消除启动前黑白屏
        setTranslucentStatus(true);
        isStartAnim(true);

    }


    private void isStartAnim(boolean start) {
        if (start) {
            startAnim();
//            startAnim2();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2 * 1000);
        }
    }

    private void startAnim() {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                startActivity(new Intent(StartActivity.this, MainActivity.class));
                StartActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void startAnim2() {
        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                String userId = PrefUtils.get("user", "userId", getApplicationContext());
//                if (userId != null && !userId.equals("")) {
//                    startMainActivity();
//                } else {
//                    startLoginActivity();
//                }

                startActivity(new Intent(StartActivity.this, MainActivity.class));
                StartActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivEntry.startAnimation(scaleAnim);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
