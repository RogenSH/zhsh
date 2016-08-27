package base.impl;

import java.util.ArrayList;

import com.itheima.zhsh66.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import base.BaseFragment;
import base.BasePager;

public class ContentFragment extends BaseFragment{

	@ViewInject(R.id.vp_content)
	private ViewPager mViewPager;
	
	private ArrayList<BasePager> mpages;
	
	@Override
	public View initView() {
		View view=View.inflate(mActivity, R.layout.fagement_content, null);
		
	//	mViewPager = (ViewPager) view.findViewById(R.id.vp_content);

		ViewUtils.inject(this,view);//注入View和事件
	
		
		return view;
	}
	
	@Override
	public void initData() {
		//初始化5个界面
		mpages =new ArrayList<BasePager>();
		mpages.add(new HomePager(mActivity));
		mpages.add(new NewsCenterPager(mActivity));
		mpages.add(new SmartServicePager(mActivity));
		mpages.add(new GovAffairsPager(mActivity));
		mpages.add(new SettingPager(mActivity));
		
		mViewPager.setAdapter(new ContentAdapter());
	}

	
	class ContentAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mpages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager=mpages.get(position);
			container.addView(pager.mRootView);//将页面布局添加到容器中
			pager.initData();//初始化数据
			return pager.mRootView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);

		}
		
	}

}
