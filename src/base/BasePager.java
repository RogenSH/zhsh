package base;

import com.itheima.zhsh66.R;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public abstract class BasePager {

	public Activity mActivity;

	public View mRootView;

	public TextView tvTitle;

	public ImageButton btnMenu;

	public FrameLayout flContent;

	//���췽��
	public BasePager(Activity activity){
		mActivity=activity;
		initView();
	}
	
	
	//��ʼ������
	public void initView(){
		mRootView=View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
		btnMenu=(ImageButton)mRootView.findViewById(R.id.btn_menu);
		flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);
	}
	
	//��ʼ������
	public abstract void initData();
	
}
