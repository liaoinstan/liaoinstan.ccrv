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
	private Handler mHandler;//�첽����
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
		///////////��ʼ����ҳHListView�ؼ�
		list = (AutoHListView) findViewById(R.id.list_dcp_detail_pak);
		adapter = new AdapterForDcpDetailList(this, null);
		list.setAdapter(adapter);
		
		///////////�����ײ��б���Ϣ
		loadTcData(adapter.getResults());
	}
	
	private void initDetail(){
		///////////������Ϣ����
		((TextView)findViewById(R.id.text_dcp_detail_title)).setText(testPojo.getTitle());
		((TextView)findViewById(R.id.text_dcp_detail_num)).setText(testPojo.getNum()+"");
		((TextView)findViewById(R.id.text_dcp_detail_addr)).setText(testPojo.getAddr());
		((TextView)findViewById(R.id.text_dcp_detail_price)).setText(testPojo.getPrice());
		((TextView)findViewById(R.id.text_dcp_detail_oldprice)).setText(testPojo.getPrice2());
		((ImageView)findViewById(R.id.img_dcp_detail_like)).setImageResource("1".equals(testPojo.getLike())?R.drawable.icon_dcp_like:R.drawable.icon_dcp_like2);
	}
	
	private void initCtrl(String[] titles,ArrayList<String> datas){
		///////////��ʼ����ҳpager�ؼ�
		PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.detail_tabs);
		//�Զ���tabs��ʽ
		tabs.setShouldExpand(true);//����Ӧ���
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
	 * ���Լ����б���Ϣ
	 * @param results
	 */
	private void loadTcData(final List<OrderPak> results) {
		mHandler.postDelayed(new Runnable(){
			@Override
			public void run(){
				//������Ϣ����
				testPojo = new TestPojo("10000", "����Ϫ7����-����", "�ɶ�", "388", "438", 0, "1");
				initDetail();
				//�����ײ��б�ҳ��
				list.setVisibility(View.GONE);
				results.add(new OrderPak("10000", "�����ײͣ�2�ˣ�", 600F, 2, 0,5));
				results.add(new OrderPak("10001", "��ͥ�����ײͣ�2����1��ͯ��", 800F, 2, 1,10));
				results.add(new OrderPak("10002", "�ػݵͼ��ײͣ�1�ˣ�", 158, 1, 0,0));
				adapter.notifyDataSetChanged();
				list.autoHeight(true);
				//����cardpage htmlҳ��
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
				initCtrl(new String[]{"��Ʒ����", "�г̸���", "��������"},new ArrayList<String>(){{add(html);add(html);add(html);}});
			}
		}, 500);
	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter {
		private String[] TITLES;// = { "��Ʒ����", "�г̸���", "��������" };
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
        	if ("1".equals(testPojo.getLike())) {//ϲ�� ���ó� ��ϲ��
				testPojo.setLike("0");
				((ImageView)v).setImageResource(R.drawable.icon_dcp_like2);
			}else {//��ϲ�� ���ó� ϲ��
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
