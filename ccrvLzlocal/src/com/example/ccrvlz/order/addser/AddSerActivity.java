package com.example.ccrvlz.order.addser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.R.integer;
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
import com.example.pojo.OrderAddSer;
import com.example.pojo.OrderAll;

public class AddSerActivity extends Activity implements OnClickListener{
	private AutoHListView listV;
	private AdapterForAddserList adapter;
	private Handler mHander;	//�첽����
	//data
	private OrderAll orderAll;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ser);
		orderAll = (OrderAll)getIntent().getSerializableExtra("orderAll");
		position = getIntent().getIntExtra("position", -1);
		mHander = new Handler();
		init();
	}

	private void init() {
		///////////////////��ʼ��
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.act_next).setOnClickListener(this);
		((TextView)findViewById(R.id.text_addser_starttime)).setText(TimeUtil.getTime(orderAll.getStartDate()));
		((TextView)findViewById(R.id.text_addser_startcity)).setText(orderAll.getStratCity());
		///////////////////��ʼ���б�
		listV = (AutoHListView)findViewById(R.id.list_addser);
		adapter = new AdapterForAddserList(this, null,R.layout.addser_list_item);
		listV.setAdapter(adapter);
		
		///////////////////����б�����
		loadData(adapter.getResults());
	}
	
	
	private void loadData(final List<OrderAddSer> results) {
		//������position����������Ҫ��������ֱ�Ӵ������л�ȡ����
		if(position!=-1){
			results.add(orderAll.getAddSers().get(position));
			adapter.notifyDataSetChanged();
		}else{
			mHander.postDelayed(new Runnable() {
				@Override
				public void run() {
					listV.setVisibility(View.GONE);
					results.add(new OrderAddSer("10000", "����ѡ����������", 98, "��",1, "http://img14.360buyimg.com/n1/g15/M04/11/00/rBEhWlJmspAIAAAAAAJCBySqmvEAAEdTwHUzt8AAkIf756.jpg", 1, 1, 1));
					results.add(new OrderAddSer("10001", "�����", 58, "��",2, "http://img.vx.com/uploadfile/data/2014/1018/20141018081951573.jpg", 0, 0, 2));
					results.add(new OrderAddSer("10002", "����Ƥͧ", 128, "��",1, "http://img3.imgtn.bdimg.com/it/u=1753105712,3705574901&fm=21&gp=0.jpg", 0, 0, 3));
					setReadyData(results);//�����Ѿ����ڵ����ݣ�������
					adapter.notifyDataSetChanged();
					listV.autoHeight(true);
				}
			}, 500);
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();    
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		case R.id.act_next:
			////ȥ����ѡ�����ֵ���񷵻أ�������Ϊ0�ģ�
			remove(adapter.getResults());
			ArrayList<OrderAddSer> results = adapter.getResults();
			intent.putExtra("addSers", adapter.getResults());
			intent.putExtra("position", position);
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
	
	/**
	 * �����Ѿ����ڵ����ݣ�������
	 * @param results
	 */
	private void setReadyData(List<OrderAddSer> results) {
		for (int i = 0; i < results.size(); i++) {
			for (OrderAddSer addser : orderAll.getAddSers()) {
				if (results.get(i).getId().equals(addser.getId())) {
					results.get(i).setCount(addser.getCount());
				}
			}
		}
	}

	/**
	 * ȥ����ѡ�����ֵ���񷵻أ�������Ϊ0�ģ�
	 * @param results
	 */
	private void remove(ArrayList<OrderAddSer> results) {
		for (Iterator<OrderAddSer> it = results.iterator(); it.hasNext();) {
			OrderAddSer addser = it.next();
			if (addser.getCount() == 0) {
				it.remove();
			}
		}
	}
}
