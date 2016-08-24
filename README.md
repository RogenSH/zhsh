
# 智慧城市

## 项目分析
需要完成什么样的功能，如何实现？

## 项目实现
项目分为几个部分：
+ 闪屏界面：
+ 新手引导界面：

### 闪屏界面：
1. 实现闪屏静态布局：
闪屏页面通过设置Activity，将界面布置为所需的小马等图片，同时在Manifest中将界面的titlebar等部分去除。

2. 实现闪屏动画：
需要整体布局完成旋转，缩放，渐变的动画。
动画方法：
```java
RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue)
ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue)
ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue)
```
3. 设置动画集合并且在布局中使用，同时对该动画集合设置监听器，当动画结束后使用Intent()跳转到另外一个Activity中。

代码：
```java
package com.itheima.zhsh66;

import utils.PrefUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

```

### 新手引导界面：
1. 在闪屏界面动画结束后判断是否跳转到新手页面（使用工具包PrefUtils）

2. 新手引导页面使用ViewPager的方式展现，设置Adapter适配器进行适配。

3. 优化代码：将初始化ImageView的过程放置在Adapter外，这样可以减少初始化的次数，防止每次都进行初始化.以后使用只要从集合中取即可。

4. 加入自定义控件（原点）。
+ 设置布局：新增线性布局将自定义控件包裹起来
+ 绘制shape：两个圆点，一个红点一个灰点。
``` xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval" >
    
    
    <size android:width="10dp"
        android:height="10dp"/>
    
    <solid android:color="#5000"/>

</shape>


<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval" >
    
    
    <size android:width="10dp"
        android:height="10dp"/>
    
    <solid android:color="#f00"/>

</shape>

```
+ 初始化并且将shape添加至LinearLayout中
+ 调整参数，对点的大小，间距等进行调整
+ 设置滑动事件

