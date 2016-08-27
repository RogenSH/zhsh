package com.itheima.zhsh66;

import utils.PrefUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	private RelativeLayout rlRoot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// 旋转，缩放，渐变
		rlRoot = (RelativeLayout) findViewById(R.id.rl_root);

		RotateAnimation animRotate_Splash = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animRotate_Splash.setDuration(1000);
		// 保持状态
		animRotate_Splash.setFillAfter(true);

		ScaleAnimation animScale_Splash = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animScale_Splash.setDuration(1000);
		animScale_Splash.setFillAfter(true);

		AlphaAnimation animAlpha_Splash = new AlphaAnimation(0, 1);
		animAlpha_Splash.setDuration(2000);
		animAlpha_Splash.setFillAfter(true);

		// 动画集合
		AnimationSet animSet = new AnimationSet(true);
		animSet.addAnimation(animAlpha_Splash);
		animSet.addAnimation(animScale_Splash);
		animSet.addAnimation(animRotate_Splash);

		rlRoot.startAnimation(animSet);

		animSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 判断是否需要新手引导
				boolean isGuideShow = PrefUtils.getBoolean("is_guide_show",
						false, getApplicationContext());

				if(isGuideShow){
					// startActivity(new
					// Intent(SplashActivity.this,MainActivity.class));
					startActivity(new Intent(getApplicationContext(),
							MainActivity.class));
	
				}else{
					startActivity(new Intent(getApplicationContext(),
							GuideActivity.class));
		
				}
				finish();
			}
		});

	}

}
