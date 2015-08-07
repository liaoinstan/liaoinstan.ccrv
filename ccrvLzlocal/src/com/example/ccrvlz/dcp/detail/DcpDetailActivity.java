package com.example.ccrvlz.dcp.detail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.ccrvlz.R;
import com.example.ccrvlz.order.OrderActivity;
import com.example.pojo.OrderAll;
import com.example.pojo.OrderCoupon;
import com.example.pojo.OrderPak;
import com.example.pojo.TestPojo;
import com.example.slideshowdemo.customview.SlideShowView;

public class DcpDetailActivity extends FragmentActivity implements OnClickListener{
//	private PagerSlidingTabStrip tabs;
//	private ViewPager pager;
	private AutoHListView list;
	private AdapterForDcpDetailList adapter;
	MyPagerAdapter pagerAdapter;
	private Handler mHandler;//异步测试
	private List<Fragment> fragments = new ArrayList<Fragment>();
	
	//data
	private TestPojo testPojo;
	private List<TestPojo> cardHtmls = new ArrayList<TestPojo>();
	public TestPojo getTestPojo() {
		return testPojo;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dcp_detail);
		testPojo = (TestPojo) getIntent().getSerializableExtra("itemPojo");
		mHandler = new Handler();
		
		init();
		setListener();
	}
	
	private void init(){
		///////////初始化翻页HListView控件
		list = (AutoHListView) findViewById(R.id.list_dcp_detail_pak);
		adapter = new AdapterForDcpDetailList(this, null);
		list.setAdapter(adapter);
		
		///////////加载套餐列表信息
		loadTcData(adapter.getResults());
	}
	
	private void initDetail(){
		///////////详情信息加载
		((TextView)findViewById(R.id.text_dcp_detail_title)).setText(testPojo.getTitle());
		((TextView)findViewById(R.id.text_dcp_detail_num)).setText(testPojo.getNum()+"");
		((TextView)findViewById(R.id.text_dcp_detail_addr)).setText(testPojo.getAddr());
		((TextView)findViewById(R.id.text_dcp_detail_price)).setText(testPojo.getPrice());
		((TextView)findViewById(R.id.text_dcp_detail_oldprice)).setText(testPojo.getPrice2());
		((ImageView)findViewById(R.id.img_dcp_detail_like)).setImageResource("1".equals(testPojo.getLike())?R.drawable.icon_dcp_like:R.drawable.icon_dcp_like2);
	}
	
	private void initCtrl(String[] titles,ArrayList<String> datas){
		///////////初始化翻页pager控件
		PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.detail_tabs);
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
		ViewPager pager = (ViewPager) findViewById(R.id.detail_pager);
		pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),titles,datas);
		pager.setAdapter(pagerAdapter);
		tabs.setViewPager(pager);
	}
	
	/**
	 * 测试加载列表信息
	 * @param results
	 */
	private void loadTcData(final List<OrderPak> results) {
		mHandler.postDelayed(new Runnable(){
			@Override
			public void run(){
				//详情信息加载
				testPojo = new TestPojo("10000", "黄龙溪7日游-包饭", "成都", "388", "438", 0, "1");
				initDetail();
				//加载套餐列表页面
				list.setVisibility(View.GONE);
				results.add(new OrderPak("10000", "情侣套餐（2人）", 600F, 2, 0,5));
				results.add(new OrderPak("10001", "家庭亲子套餐（2成人1儿童）", 800F, 2, 1,10));
				results.add(new OrderPak("10002", "特惠低价套餐（1人）", 158, 1, 0,0));
				adapter.notifyDataSetChanged();
				list.autoHeight(true);
				//加载cardpage html页面
				final String html = "<div>"
								+"this is a html page<br>"
								+"this is a html page<br>"
								+"this is a html page<br>"
								+"this is a html page<br>"
								+"this is a html page<br>"
								+"<img src='http://b.hiphotos.baidu.com/album/pic/item/cb8065380cd79123c6f9b8dead345982b2b7807a.jpg?psign=c6f9b8dead345982b2b7d0a20cf431adcaef76094b36a442' style='width:137px; height:90px;'><br>"
								+"this is a html page<br>"
								+"this is a html page<br>"
								+"<img src='http://pica.nipic.com/2007-11-13/2007111317599808_2.jpg' style='width:137px; height:90px;'>"
								+"<div>";
				initCtrl(new String[]{"商品详情", "行程概览", "房车介绍"},new ArrayList<String>(){{add(html);add(html);add(html);}});
			}
		}, 500);
	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter {
		private String[] TITLES;// = { "商品详情", "行程概览", "房车介绍" };
		private List<String> results;// = new ArrayList<String>(){{add("");add("");add("");}};
		public MyPagerAdapter(FragmentManager fm,String[] titles,List<String> results) {
			super(fm);
			this.TITLES = titles;
			this.results = results;
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
			return DetailCardFragment.newInstance(position,results.get(position));
		}
		
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) {
//			DetailCardFragment frag = (DetailCardFragment)super.instantiateItem(container, position);
//			frag.setData(results.get(position));
//			return frag;
//		}
	}
	
	private void setListener() {
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.img_dcp_detail_like).setOnClickListener(this);
		findViewById(R.id.act_next).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
        case R.id.img_action_back: 
        	finish();
            break;  
        case R.id.img_dcp_detail_like: 
        	if ("1".equals(testPojo.getLike())) {//喜欢 设置成 不喜欢
				testPojo.setLike("0");
				((ImageView)v).setImageResource(R.drawable.icon_dcp_like2);
			}else {//不喜欢 设置成 喜欢
				testPojo.setLike("1");
				((ImageView)v).setImageResource(R.drawable.icon_dcp_like);
			}
            break; 
        case R.id.act_next:
			Intent intent = new Intent(this, OrderActivity.class);
			OrderAll orderAll = new OrderAll(testPojo.getId(),5, testPojo.getTitle(),testPojo.getBigImg(),testPojo.getAddr(),388F,138F,Float.parseFloat(testPojo.getPrice()),new Date());
			intent.putExtra("orderAll", orderAll);
			startActivity(intent);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
        	break;
		}
	}
	
	@Override
		protected void onStop() {
			((SlideShowView)findViewById(R.id.slide_detail)).destory();
			super.onStop();
		}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
	}
}
