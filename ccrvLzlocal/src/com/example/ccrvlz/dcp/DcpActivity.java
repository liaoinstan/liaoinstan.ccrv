package com.example.ccrvlz.dcp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.city.CityActivity;

public class DcpActivity extends FragmentActivity implements OnClickListener{
	private Handler mHander = new Handler();
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	public MyPagerAdapter getAdapter() {
		return adapter;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dcp);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);		
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);
		
		initBase();
	}

	private void initBase() {
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.lay_action_more).setOnClickListener(this);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {
		private final String[] TITLES = { "全部", "成都", "重庆", "桂林", "呼和浩特", "乌鲁木齐" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
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
			return SuperAwesomeCardFragment.newInstance(position,TITLES[position]);
		}
		
		public int getIndexByTitle(String title) {
			for (int i = 0; i < TITLES.length; i++) {
				if (TITLES[i].equals(title)) {
					return i;
				}
			}
			return 0;
		}
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		case R.id.lay_action_more:
			Intent intent = new Intent();
			intent.setClass(this, CityActivity.class);
			startActivityForResult(intent, 1);
			overridePendingTransition(R.anim.in_from_right,R.anim.state); 
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if(resultCode == RESULT_OK){
				String city = data.getStringExtra("city");
				setCity(city);
			}
			break;
		default:
			break;
		}
	}
	
	private void setCity(final String city) {
		pager.setCurrentItem(adapter.getIndexByTitle(city),false);
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
	}
}
