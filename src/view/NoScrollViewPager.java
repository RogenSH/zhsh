package view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

//���ܻ�����ViewPage
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// �����¼��Ƿ��ж�
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;// �������¼�, ��Ƕ�׵���viewpager�л�����Ӧ�����¼�
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// ��дViewPager�����¼�, ��Ϊʲô������	
		//return super.onTouchEvent(ev);
		return true;
	}
	
}
