package base.impl;

import com.itheima.zhsh66.R;

import android.view.View;
import base.BaseFragment;

public class ContentFragment extends BaseFragment{

	@Override
	public View initView() {
		View view=View.inflate(mActivity, R.layout.fagement_content, null);
		return view;
	}

}
