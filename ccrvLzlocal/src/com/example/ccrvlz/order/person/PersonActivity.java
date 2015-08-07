package com.example.ccrvlz.order.person;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.detail.AutoHListView;
import com.example.ccrvlz.utils.TimeUtil;
import com.example.pojo.OrderAll;
import com.example.pojo.TestPojo;

public class PersonActivity extends Activity implements OnClickListener{
//	private TestOrder testOrder;
	private OrderAll orderAll;
	private AutoHListView listV;
	private AdapterForPersonList adapter;
	private Handler mHander;	//异步测试

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);
//		testOrder = (TestOrder)getIntent().getSerializableExtra("testOrder");
		orderAll = (OrderAll)getIntent().getSerializableExtra("orderAll");
		mHander = new Handler();
		init();
	}
	
	private void init() {
		///////////////////初始化
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.act_next).setOnClickListener(this);
		((TextView)findViewById(R.id.text_person_starttime)).setText(TimeUtil.getTime(orderAll.getStartDate()));
		((TextView)findViewById(R.id.text_person_startcity)).setText(orderAll.getStratCity());
		///////////////////初始化列表
		listV = (AutoHListView)findViewById(R.id.list_person);
		adapter = new AdapterForPersonList(this, null,R.layout.person_list_item);
		adapter.getResults().add(new TestPojo("10000", "成人", "获取中...",orderAll.getAdultCount(), 4));
		adapter.getResults().add(new TestPojo("10001", "儿童（12周岁以下）", "获取中...",orderAll.getChildCount(), 4));
		listV.setAdapter(adapter);
		
		///////////////////添加列表数据
		loadData(adapter.getResults());
	}
	
	private void loadData(final List<TestPojo> results) {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				results.get(0).setPrice(orderAll.getAdultPrice()+"");
				results.get(1).setPrice(orderAll.getChildPrice()+"");
				adapter.setMaxCount(100);//设置出行人数上限 不设置则默认100：该功能可取消
				adapter.notifyDataSetChanged();
				listV.autoHeight(false);
			}
		}, 0);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();    
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		case R.id.act_next:
			intent.putExtra("adultCount", adapter.getResults().get(0).getNum());
			intent.putExtra("childCount", adapter.getResults().get(1).getNum());
			setResult(RESULT_OK, intent);
			super.finish();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
	}
}
