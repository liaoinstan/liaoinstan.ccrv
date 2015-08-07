package com.example.ccrvlz.order.payway;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.detail.AutoHListView;
import com.example.ccrvlz.order.MsgDialog;
import com.example.pojo.OrderAll;
import com.example.pojo.TestPojo;

public class PayWayActivity extends Activity implements OnClickListener,OnItemClickListener{
	private OrderAll orderAll;
	private AutoHListView listV;
	private AdapterForPayWayList adapter;
	private Handler mHander = new Handler();	//�첽����
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_way);
		orderAll = (OrderAll)getIntent().getSerializableExtra("orderAll");
		init();
	}

	private void init() {
		///////////////////��ʼ��
		findViewById(R.id.img_action_back).setOnClickListener(this);
		((ListView)findViewById(R.id.list_payway)).setOnItemClickListener(this);
		///////////////////��ʼ���б�
		listV = (AutoHListView)findViewById(R.id.list_payway);
		adapter = new AdapterForPayWayList(this, null,R.layout.payway_list_item);
		listV.setAdapter(adapter);
		
		///////////////////����б�����
		loadData(adapter.getResults());
	}

	private void loadData(final List<TestPojo> results) {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				listV.setVisibility(View.GONE);
				results.add(new TestPojo("10000", "΢��֧��", "֧������ȫ֧��", "weixin", 0));
				results.add(new TestPojo("10000", "֧����", "֧����֪�и�", "zhifubao", 0));
				adapter.notifyDataSetChanged();
				listV.autoHeight(true);
			}
		}, 500);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
//		Intent intent = new Intent(this, ContactActivity.class);
//		intent.putExtra("testOrder", orderAll);
//		startActivity(intent);
//		this.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
	}
	
	public void doFinish(){
		super.finish();
		overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
	}

	@Override
	public void finish() {
		final MsgDialog dialog = new MsgDialog(this,"�ף�ȷ��Ҫ����֧����");
		dialog.setOnNegativeListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	dialog.dismiss();
	        	doFinish();
	        }
	    });
		dialog.show();
	}
}
