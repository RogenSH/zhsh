package com.itheima.zhsh66;

import base.impl.ContentFragment;
import base.impl.LeftMenuFragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private static final String TAG_CONTENT = null;
	private static final String TAG_LEFTMENU = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去掉标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// 添加侧边栏
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(500);

		initFragment();
	}

	// 初始化Fragment对象
	private void initFragment() {
		//Fragment管理器
		// getFragmentManager()也可以操作，但是此方法是4.0后支持的，下面的方法可以向下支持2.0,兼容性较好
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction=fm.beginTransaction();//开始事务
		//将帧布局替换为对应的Fragment,TAG方便后期寻找Fragment
		//fm.findFragmentByTag(TAG_CONTENT);
		transaction.replace(R.id.fl_content, new ContentFragment(),TAG_CONTENT);
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),TAG_LEFTMENU);
		transaction.commit();//提交事务
		
	}

}
