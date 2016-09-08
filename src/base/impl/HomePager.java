package base.impl;

import android.R;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import base.BasePager;

public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("ÖÇ»ÛÉÏº£");
		btnMenu.setVisibility(View.GONE);
		
		TextView view=new TextView(mActivity);
		view.setText("Ê×Ò³");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
	}

}
