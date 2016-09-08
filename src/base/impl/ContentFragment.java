package base.impl;

import java.util.ArrayList;

import com.itheima.zhsh66.MainActivity;
import com.itheima.zhsh66.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import base.BaseFragment;
import base.BasePager;

public class ContentFragment extends BaseFragment {

	@ViewInject(R.id.vp_content)
	private ViewPager mViewPager;

	@ViewInject(R.id.rg_group)
	private RadioGroup rgGroup;

	private ArrayList<BasePager> mPagers;

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fagement_content, null);

		// mViewPager = (ViewPager) view.findViewById(R.id.vp_content);

		ViewUtils.inject(this, view);// ע��View���¼�

		return view;
	}

	@Override
	public void initData() {
		// ��ʼ��5������
		mPagers = new ArrayList<BasePager>();
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new NewsCenterPager(mActivity));
		mPagers.add(new SmartServicePager(mActivity));
		mPagers.add(new GovAffairsPager(mActivity));
		mPagers.add(new SettingPager(mActivity));

		mViewPager.setAdapter(new ContentAdapter());

		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rb_home:
					// ��ҳ
					// mViewPager.setCurrentItem(0);
					mViewPager.setCurrentItem(0, false);// ����ҳ���л��Ķ���Ч��
					mPagers.get(0).initData();// �е���ǰҳ��,�ٳ�ʼ������
					setSlidingMenuEnable(false);// ���ò����
					break;
				case R.id.rb_news:
					// ��������
					mViewPager.setCurrentItem(1, false);
					mPagers.get(1).initData();// �е���ǰҳ��,�ٳ�ʼ������
					setSlidingMenuEnable(true);// ���������
					break;
				case R.id.rb_smart:
					// �ǻ۷���
					mViewPager.setCurrentItem(2, false);
					mPagers.get(2).initData();// �е���ǰҳ��,�ٳ�ʼ������
					setSlidingMenuEnable(true);// ���������
					break;
				case R.id.rb_gov:
					// ����
					mViewPager.setCurrentItem(3, false);
					mPagers.get(3).initData();// �е���ǰҳ��,�ٳ�ʼ������
					setSlidingMenuEnable(true);// ���������
					break;
				case R.id.rb_setting:
					// ����
					mViewPager.setCurrentItem(4, false);
					mPagers.get(4).initData();// �е���ǰҳ��,�ٳ�ʼ������
					setSlidingMenuEnable(false);// ���ò����
					break;

				default:
					break;
				}
			}

		});
		mPagers.get(0).initData();// ��ʼ����ҳ����
		setSlidingMenuEnable(false);// ��ҳ���ò����
		
	}

	class ContentAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = mPagers.get(position);
			container.addView(pager.mRootView);// ��ҳ�沼����ӵ�������
//			pager.initData();// ��ʼ������   ���������Ԥ�ȼ��ر�ҳ������һҳ�棬�ʶ����ڴ˴���ʼ��������ÿ�ε��RadioButton��ʱ����г�ʼ��
			return pager.mRootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);

		}

	}
	
	
	private void setSlidingMenuEnable(boolean enable) {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();

		if (enable) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			// ���õ����������Ч��
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

}
