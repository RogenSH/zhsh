package base;

import android.app.Activity;
import android.view.View;

/**
 * ����� �˵�����ҳ����
 * 
 */
public abstract class BaseMenuDetailPager {

	public Activity mActivity;
	// �˵�����ҳ������
	public View mRootView;

	public BaseMenuDetailPager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
	}

	public abstract View initView();

	public void initData() {

	}
}
