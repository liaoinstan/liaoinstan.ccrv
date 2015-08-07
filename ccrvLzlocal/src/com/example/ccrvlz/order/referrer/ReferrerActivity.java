package com.example.ccrvlz.order.referrer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.ccrvlz.R;
import com.example.ccrvlz.order.clause.ClauseActivity;
import com.example.ccrvlz.order.traveller.TravellerActivity;
import com.example.pojo.TestOrder;

public class ReferrerActivity extends Activity implements OnClickListener{
//	private TestOrder testOrder;
	private String referrerPhone;
	private float referrerPrice;
	private Handler mHander;	//异步测试
	private View actNextLay;
	private View msgLay;
	private EditText edit_phone;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_referrer);
		referrerPhone = getIntent().getStringExtra("referrerPhone");
		referrerPrice = getIntent().getFloatExtra("referrerPrice",0);
		mHander = new Handler();
		initBase();
	}

	private void initBase() {
		///////////////////初始化
		findViewById(R.id.img_action_back).setOnClickListener(this);
		actNextLay = findViewById(R.id.act_next);
		actNextLay.setOnClickListener(this);
		
		msgLay = findViewById(R.id.layout_msg);
		edit_phone = (EditText) findViewById(R.id.edit_referrer_phone);
	}

	//异步验证，返回推荐码可抵价值
	private void loadValidate() {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				actNextLay.setClickable(true);
				actNextLay.setBackgroundResource(R.color.cc_orage);
				if ("".equals(edit_phone.getText().toString())) {
					msgLay.setVisibility(View.VISIBLE);
				}else {
					referrerPrice = 50;/////测试加载为50元
					referrerPhone = edit_phone.getText().toString();
					Intent intent = new Intent();
					intent.putExtra("referrerPhone", referrerPhone);
					intent.putExtra("referrerPrice", referrerPrice);
					setResult(RESULT_OK, intent);
					ReferrerActivity.super.finish();
				}
			}
		}, 1000);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		case R.id.act_next:
			v.setClickable(false);
			v.setBackgroundResource(R.color.act_next_n);
			msgLay.setVisibility(View.GONE);
			loadValidate();
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
