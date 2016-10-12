package base.impl;

import java.util.ArrayList;

import com.itheima.zhsh66.MainActivity;
import com.itheima.zhsh66.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import domain.NewsMenuData.NewsData;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import base.BaseFragment;

public class LeftMenuFragment extends BaseFragment {
	
	@ViewInject(R.id.lv_list)
	private ListView lvList;
	
	private ArrayList<NewsData> mMenuList;

	private MenuAdapter mAdapter;

	private int mCurrrentPos;// ��ǰ��ѡ�еĲ˵�λ��
	@Override
	public View initView() {

		View view=View.inflate(mActivity, R.layout.fragment_left_menu, null);
		ViewUtils.inject(this,view);
		return view;
	}
	
	
	public void setData(ArrayList<NewsData> data){
		mMenuList = data;
		mAdapter = new MenuAdapter();
		lvList.setAdapter(mAdapter);
		
		lvList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrrentPos = position;
				mAdapter.notifyDataSetChanged();
//				// ֪ͨ��������,�л�ҳ��
//				setCurrentMenuDetailPager(position);
//				// ���ز����
//				toggle();
			}
		});
		mCurrrentPos = 0;//���õ�ǰҳ��λ��
	}
	
	
//	/**
//	 * �л��˵�����ҳҳ��
//	 * 
//	 * @param position
//	 */
//	protected void setCurrentMenuDetailPager(int position) {
//		// ��ȡ�������Ķ���NewsCenterPager
//		// 1.�Ȼ�ȡMainActivity,
//		// 2.ͨ��MainActiivty��ȡContentFragment
//		// 3.ͨ��ContentFragment��ȡNewsCenterPager
//		MainActivity mainUI = (MainActivity) mActivity;
//		ContentFragment contentFragment = mainUI.getContentFragment();
//	    NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
//
//		// ����������ҳ���FrameLayout��䲼��
//		newsCenterPager.setCurrentMenuDetailPager(position);
//	}
	
//	/**
//	 * �����չ����������ķ���
//	 */
//	private void toggle() {
//		MainActivity mainUI = (MainActivity) mActivity;
//		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
//		slidingMenu.toggle();// ����(���״̬Ϊ��,���͹�;���״̬Ϊ��,���Ϳ�)
//	}
//	
	
	class MenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mMenuList.size();
		}

		@Override
		public NewsData getItem(int position) {
			return mMenuList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mActivity, R.layout.list_item_left_menu,
					null);
			TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);

			NewsData data = getItem(position);
			tvMenu.setText(data.title);

			if (mCurrrentPos == position) {
				// �����ǰҪ���Ƶ�item�պ��Ǳ�ѡ�е�, ��Ҫ����Ϊ��ɫ
				tvMenu.setEnabled(true);
			} else {
				// ����item���ǰ�ɫ
				tvMenu.setEnabled(false);
			}

			return view;
		}

	}

}
