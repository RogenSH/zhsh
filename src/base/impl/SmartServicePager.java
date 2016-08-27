package base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import base.BasePager;

public class SmartServicePager extends BasePager {

	public SmartServicePager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initData() {
		TextView textView=new TextView(mActivity);
		textView.setText("�ǻ۷���");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		
		flContent.addView(textView);
		
	}

}
