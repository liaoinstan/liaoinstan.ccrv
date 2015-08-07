package com.example.ccrvlz.order.traveller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.detail.AutoHListView;
import com.example.pojo.OrderTraveller;

public class TravellerActivity extends Activity implements OnClickListener{
	private Handler mHander;
	private AutoHListView listV;
	private AdapterForTravellerList adapter;
	//data
	private OrderTraveller orderTraveller;
	private int count;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traveller);
		orderTraveller = (OrderTraveller)getIntent().getSerializableExtra("orderTraveller");
		count = getIntent().getIntExtra("count",0);
		position = getIntent().getIntExtra("position", -1);
		
		mHander = new Handler();
		initBase();
	}

	private void initBase() {
		///////////////////初始化
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.act_next).setOnClickListener(this);
		
		listV = (AutoHListView) findViewById(R.id.list_traveller);
		adapter = new AdapterForTravellerList(this, R.layout.traveller_list_item);
		listV.setAdapter(adapter);
		if (count!=0) {
			for (int i = 0; i < count; i++) {
				adapter.getResults().add(new OrderTraveller());
			}
		}
		if (orderTraveller!=null) {
			adapter.getResults().add(orderTraveller);
		}
		adapter.notifyDataSetChanged();
		listV.autoHeight(false);
//		getViewByPosition(0, listV).findViewById(R.id.edit_traveller_name).callOnClick();	//无效，使用requestFocus()会导致内存溢出
	}
	

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		case R.id.act_next:
			setData();
			intent.putExtra("orderTravellers", adapter.getResults());
			intent.putExtra("position", position);
			setResult(RESULT_OK, intent);
			super.finish();
			break;
		default:
			break;
		}		
	}

	private void setData() {
		for (int i = 0; i < listV.getChildCount(); i++) {
			View view = getViewByPosition(i, listV);
			String name = ((EditText)view.findViewById(R.id.edit_traveller_name)).getText().toString();
			String idCard = ((EditText)view.findViewById(R.id.edit_traveller_idcard)).getText().toString();
			adapter.getResults().get(i).setName(name);
			adapter.getResults().get(i).setIdCard(idCard);
		}
	}

	private View getViewByPosition(int pos, ListView listView) {
		final int firstListItemPosition = listView.getFirstVisiblePosition();
		final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
		if (pos < firstListItemPosition || pos > lastListItemPosition) {
			return listView.getAdapter().getView(pos, null, listView);
		} else {
			final int childIndex = pos - firstListItemPosition;
			return listView.getChildAt(childIndex);
		}
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
	}
}
