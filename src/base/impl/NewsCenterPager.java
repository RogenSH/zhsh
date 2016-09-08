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
		textView.setText("新闻中心");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		
		flContent.addView(textView);
		
		
		getDataFromServer();
		
	}

	
	/**
	 * 从服务器获取数据 
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, Constants.CATEGORIES_URL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// 请求成功
						String result = responseInfo.result;// 获取json字符串
//						 System.out.println("result:" + result);
						processResult(result);
						// 写缓存
//						CacheUtils.setCache(Constants.CATEGORIES_URL, result,
//								mActivity);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 请求失败
						error.printStackTrace();
						Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
								.show();
					}
				});
	}

	/**
	 * 解析json数据
	 * 
	 * @param result
	 */
	protected void processResult(String result) {
		// gson->json
		Gson gson = new Gson();

		mNewsMenuData = gson.fromJson(result, NewsMenuData.class);
		System.out.println("解析结果:" + mNewsMenuData);

		// 获取侧边栏对象
		MainActivity mainUi=(MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment=mainUi.getLeftMenuFragment();
		// 将网络数据设置给侧边栏
		leftMenuFragment.setData(mNewsMenuData.data);
		


//		// 初始化4个菜单详情页
//		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
//		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,
//				mNewsMenuData.data.get(0).children));
//		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
//		mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
//		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));
//
//		// 菜单详情页-新闻作为初始页面
//		setCurrentMenuDetailPager(0);
	}
//
//	// 给新闻中心页面的FrameLayout填充布局
//	protected void setCurrentMenuDetailPager(int position) {
//		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
//		// 移除之前所有的view对象, 清理屏幕
//		flContent.removeAllViews();
//		flContent.addView(pager.mRootView);
//		pager.initData();// 初始化数据
//
//		// 更改标题
//		tvTitle.setText(mNewsMenuData.data.get(position).title);
//	}

}
