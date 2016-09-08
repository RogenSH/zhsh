package base.impl;

import java.util.ArrayList;

import com.itheima.zhsh66.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import domain.NewsMenuData.NewsData;
import android.view.View;
import android.view.ViewGroup;
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

	}
	
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

//			if (mCurrrentPos == position) {
//				// �����ǰҪ���Ƶ�item�պ��Ǳ�ѡ�е�, ��Ҫ����Ϊ��ɫ
//				tvMenu.setEnabled(true);
//			} else {
//				// ����item���ǰ�ɫ
//				tvMenu.setEnabled(false);
//			}

			return view;
		}

	}

}
