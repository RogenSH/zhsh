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
		// ȥ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// ��Ӳ����
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(500);

		initFragment();
	}

	// ��ʼ��Fragment����
	private void initFragment() {
		//Fragment������
		// getFragmentManager()Ҳ���Բ��������Ǵ˷�����4.0��֧�ֵģ�����ķ�����������֧��2.0,�����ԽϺ�
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction=fm.beginTransaction();//��ʼ����
		//��֡�����滻Ϊ��Ӧ��Fragment,TAG�������Ѱ��Fragment
		//fm.findFragmentByTag(TAG_CONTENT);
		transaction.replace(R.id.fl_content, new ContentFragment(),TAG_CONTENT);
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),TAG_LEFTMENU);
		transaction.commit();//�ύ����
		
	}

}
