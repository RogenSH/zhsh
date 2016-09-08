package base.impl;

import global.Constants;

import com.google.gson.Gson;
import com.itheima.zhsh66.MainActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import domain.NewsMenuData;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import base.BasePager;

public class NewsCenterPager extends BasePager {

	private NewsMenuData mNewsMenuData;

	public NewsCenterPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initData() {
		TextView textView=new TextView(mActivity);
		textView.setText("��������");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		
		flContent.addView(textView);
		
		
		getDataFromServer();
		
	}

	
	/**
	 * �ӷ�������ȡ���� 
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, Constants.CATEGORIES_URL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// ����ɹ�
						String result = responseInfo.result;// ��ȡjson�ַ���
//						 System.out.println("result:" + result);
						processResult(result);
						// д����
//						CacheUtils.setCache(Constants.CATEGORIES_URL, result,
//								mActivity);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// ����ʧ��
						error.printStackTrace();
						Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
								.show();
					}
				});
	}

	/**
	 * ����json����
	 * 
	 * @param result
	 */
	protected void processResult(String result) {
		// gson->json
		Gson gson = new Gson();

		mNewsMenuData = gson.fromJson(result, NewsMenuData.class);
		System.out.println("�������:" + mNewsMenuData);

		// ��ȡ���������
		MainActivity mainUi=(MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment=mainUi.getLeftMenuFragment();
		// �������������ø������
		leftMenuFragment.setData(mNewsMenuData.data);
		


//		// ��ʼ��4���˵�����ҳ
//		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
//		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,
//				mNewsMenuData.data.get(0).children));
//		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
//		mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
//		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));
//
//		// �˵�����ҳ-������Ϊ��ʼҳ��
//		setCurrentMenuDetailPager(0);
	}
//
//	// ����������ҳ���FrameLayout��䲼��
//	protected void setCurrentMenuDetailPager(int position) {
//		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
//		// �Ƴ�֮ǰ���е�view����, ������Ļ
//		flContent.removeAllViews();
//		flContent.addView(pager.mRootView);
//		pager.initData();// ��ʼ������
//
//		// ���ı���
//		tvTitle.setText(mNewsMenuData.data.get(position).title);
//	}

}
