package com.itheima.zhsh66;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class GuideActivity extends Activity {
	private ViewPager mViewPager;

	private int[] mImageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };

	private ArrayList<ImageView> mImageViewList;

	private LinearLayout llContainer;

	private ImageView ivRedPoint;
	
	private int mPointWidth;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);

		mViewPager = (ViewPager) findViewById(R.id.vp_pager);

		llContainer = (LinearLayout) findViewById(R.id.ll_container_guide);

		ivRedPoint=(ImageView)findViewById(R.id.iv_red_point);
		
		mImageViewList = new ArrayList<ImageView>();

		// �ڴ˴���ʼ��ImageView���Լ��ٳ�ʼ���Ĵ�������ֹÿ�ζ����г�ʼ��.�Ժ�ʹ��ֻҪ�Ӽ�����ȡ���ɡ�
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(view);

			// ��ʼ��Բ��
			ImageView pointView = new ImageView(this);
			pointView.setImageResource(R.drawable.shape_circle_default);

			//��ʼ�����ֲ���
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			//�ӵڶ����㿪ʼ���ñ߾�
			if(i>0){
				params.leftMargin=10;
			}

			pointView.setLayoutParams(params);
			
			llContainer.addView(pointView);

		}

		mViewPager.setAdapter(new MyAdapter());
		
		//ҳ����ƽ���֮�󣬼�������ԭ��ļ��
		//��ͼ��
		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
	

			//layout����ִ�н�����λ��ȷ����
			@Override
			public void onGlobalLayout() {
				//�Ƴ�����
				ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				mPointWidth=llContainer.getChildAt(1).getLeft()-llContainer.getChildAt(0).getLeft();
				Log.i("test_log", "width="+mPointWidth);
			}
		});
		
	
		
		
		//���ü��������¼�  ��С�������ҳ�滬�����任λ�ã�
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				
			}
			
			//ҳ�滬�������в��ϻص��÷���
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				Log.i("test_log", "��ǰλ��:"+position);
				Log.i("test_log","ƫ�ưٷֱ�:"+positionOffset);
				Log.i("test_log", "ƫ������:"+positionOffsetPixels);
				//���㵱ǰС������߾�
				int leftMargin=(int) (mPointWidth * positionOffset+position*mPointWidth);
				RelativeLayout.LayoutParams params_lo_red=(LayoutParams) ivRedPoint.getLayoutParams();
				params_lo_red.leftMargin=leftMargin;
				ivRedPoint.setLayoutParams(params_lo_red);
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
			// ��ʼ��
			// ImageView view =new ImageView(getApplicationContext());
			// view.setBackgroundResource(mImageIds[position]);

			ImageView view = mImageViewList.get(position);

			container.addView(view);

			return view;
		}

		// ɾ��
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);

		}
	}
}
