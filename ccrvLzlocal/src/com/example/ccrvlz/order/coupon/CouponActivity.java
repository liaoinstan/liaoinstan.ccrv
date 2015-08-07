package com.example.ccrvlz.order.coupon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.ccrvlz.R;
import com.example.pojo.OrderCoupon;

public class CouponActivity extends FragmentActivity implements OnClickListener{
	private ArrayList<OrderCoupon> results;
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	private Handler mHander;
	public ArrayList<OrderCoupon> getResults() {
		return results;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon);
		results = (ArrayList<OrderCoupon>)getIntent().getSerializableExtra("coupons");
		mHander = new Handler();
		initBase();
		loadData();
	}

	private void initBase() {
		///////////////////初始化
		findViewById(R.id.img_action_back).setOnClickListener(this);
	}
	
	private void initCtrl(String[] titles,List<ArrayList<OrderCoupon>> resultsList) {
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		//自定义tabs样式
		tabs.setShouldExpand(true);//自适应宽度
		tabs.setIndicatorColorResource(R.color.cc_orage);
		tabs.setUnderlineHeight(2);
		tabs.setIndicatorHeight(4);
		tabs.setDividerColorResource(R.color.none);
		tabs.setUnderlineColorResource(R.color.cc_underlinelight);
		tabs.setTextSize(14);
		tabs.setTextColorHotResource(R.color.cc_orage);
		//
		pager = (ViewPager) findViewById(R.id.pager);		
		adapter = new MyPagerAdapter(getSupportFragmentManager(),titles,resultsList);
		pager.setAdapter(adapter);
		tabs.setViewPager(pager);
	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter {
		private final String[] TITLES;
		private List<ArrayList<OrderCoupon>> resultsList;
		public MyPagerAdapter(FragmentManager fm,String[] titles,List<ArrayList<OrderCoupon>> resultsList) {
			super(fm);
			this.TITLES = titles;
			this.resultsList = resultsList;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return CouponFragment.newInstance(position);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			CouponFragment frag = (CouponFragment) super.instantiateItem(container, position);
			frag.setPosition(position);
			frag.setResults(resultsList.get(position));
			return frag;
		}
	}
	
	/**
	 * 添加数据的方法，下面是没有后台接口的测试
	 * @param list
	 */
	private void loadData() {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				
				List<ArrayList<OrderCoupon>> resultsList = new ArrayList<ArrayList<OrderCoupon>>();
				ArrayList<OrderCoupon> temp1 = new ArrayList<OrderCoupon>();
				temp1.add(new OrderCoupon("10001", 30, 168, new Date() , new Date(), 0, 0));
				temp1.add(new OrderCoupon("10002", 3, 0, new Date() , new Date(), 0, 0));
				temp1.add(new OrderCoupon("10004", 32, 128, new Date() , new Date(), 0, 0));
				setReadyData(temp1);
				resultsList.add(temp1);
				ArrayList<OrderCoupon> temp2 = new ArrayList<OrderCoupon>();
				temp2.add(new OrderCoupon("10003", 10, 99, new Date() , new Date(), 0, 0));
				temp2.add(new OrderCoupon("10005", 3, 0, new Date() , new Date(), 0, 0));
				resultsList.add(temp2);
				String[] titles = new String[]{"可用优惠券（"+resultsList.get(0).size()+"）","不可用优惠券（"+resultsList.get(1).size()+"）"};
				
				initCtrl(titles, resultsList);
			}
		}, 500);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 设置已经存在的数据（是否选择）
	 * @param results
	 */
	private void setReadyData(List<OrderCoupon> temp) {
		for (int i = 0; i < temp.size(); i++) {
			for (OrderCoupon coupon : results) {
				if (temp.get(i).getId().equals(coupon.getId())) {
					temp.get(i).setSelect(coupon.getSelect());
				}
			}
		}
	}
}
