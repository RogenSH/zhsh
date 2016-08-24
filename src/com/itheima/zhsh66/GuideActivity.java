package com.itheima.zhsh66;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends Activity {
	private ViewPager mViewPager;

	private int[] mImageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };

	private ArrayList<ImageView> mImageViewList;

	private LinearLayout llContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);

		mViewPager = (ViewPager) findViewById(R.id.vp_pager);

		llContainer = (LinearLayout) findViewById(R.id.ll_container_guide);

		mImageViewList = new ArrayList<ImageView>();

		// 在此处初始化ImageView可以减少初始化的次数，防止每次都进行初始化.以后使用只要从集合中取即可。
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(view);

			// 初始化圆点
			ImageView pointView = new ImageView(this);
			pointView.setImageResource(R.drawable.shape_circle_default);

			//初始化布局参数
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			//从第二个点开始设置边距
			if(i>0){
				params.leftMargin=10;
			}

			pointView.setLayoutParams(params);
			
			llContainer.addView(pointView);

		}

		mViewPager.setAdapter(new MyAdapter());
		
		//计算两个原点的间距
		
		
		
		//设置监听滑动事件  （小红点随着页面滑动而变换位置）
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				
			}
			
			//页面滑动过程中不断回调该方法
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
		//		System.out.print("当前位置:"+position+";偏移百分比:"+positionOffset+";偏移像素:"+positionOffsetPixels);
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageIds.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 初始化
			// ImageView view =new ImageView(getApplicationContext());
			// view.setBackgroundResource(mImageIds[position]);

			ImageView view = mImageViewList.get(position);

			container.addView(view);

			return view;
		}

		// 删除
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);

		}
	}
}
