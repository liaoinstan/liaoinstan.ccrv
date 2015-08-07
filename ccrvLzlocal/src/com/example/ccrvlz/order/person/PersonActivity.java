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
	private Handler mHander;	//�첽����

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
		///////////////////��ʼ��
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.act_next).setOnClickListener(this);
		((TextView)findViewById(R.id.text_person_starttime)).setText(TimeUtil.getTime(orderAll.getStartDate()));
		((TextView)findViewById(R.id.text_person_startcity)).setText(orderAll.getStratCity());
		///////////////////��ʼ���б�
		listV = (AutoHListView)findViewById(R.id.list_person);
		adapter = new AdapterForPersonList(this, null,R.layout.person_list_item);
		adapter.getResults().add(new TestPojo("10000", "����", "��ȡ��...",orderAll.getAdultCount(), 4));
		adapter.getResults().add(new TestPojo("10001", "��ͯ��12�������£�", "��ȡ��...",orderAll.getChildCount(), 4));
		listV.setAdapter(adapter);
		
		///////////////////����б�����
		loadData(adapter.getResults());
	}
	
	private void loadData(final List<TestPojo> results) {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				results.get(0).setPrice(orderAll.getAdultPrice()+"");
				results.get(1).setPrice(orderAll.getChildPrice()+"");
				adapter.setMaxCount(100);//���ó����������� ��������Ĭ��100���ù��ܿ�ȡ��
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
