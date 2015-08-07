package com.example.ccrvlz.order.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.pojo.OrderContact;

public class ContactActivity extends Activity implements OnClickListener{
	private OrderContact orderContact;
	private TimeCount time;
	private Handler mHander;	//异步测试
	private TextView text_validate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		orderContact = (OrderContact)getIntent().getSerializableExtra("orderContact");
		mHander = new Handler();
		time = new TimeCount(60000, 1000);//构造CountDownTimer对象
		initBase();
	}
	
	private void initBase() {
		///////////////////初始化
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.act_next).setOnClickListener(this);
		text_validate = (TextView) findViewById(R.id.text_contact_validate);
		text_validate.setOnClickListener(this);
		if (orderContact!=null) {
			((EditText)findViewById(R.id.edit_contact_name)).setText(orderContact.getName());
			((EditText)findViewById(R.id.edit_contact_phone)).setText(orderContact.getPhone());
//			((EditText)findViewById(R.id.edit_contact_note)).setText(orderContact.getNote());
		}
	}
	
	private void loadValidate() {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				text_validate.setText("重新获取");
				text_validate.setBackgroundResource(R.drawable.icon_btn_re);
				text_validate.setTextAppearance(ContactActivity.this, R.style.text_btn_n);
				time.start();
			}
		}, 2000);
	}

	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
		}
		@Override
		public void onFinish() {//计时完毕时触发
			text_validate.setTextAppearance(ContactActivity.this, R.style.text_btn_y);
			text_validate.setBackgroundResource(R.drawable.icon_btn_y);
			text_validate.setText("重新获取");
			text_validate.setClickable(true);
		}
		@Override
		public void onTick(long millisUntilFinished){//计时过程显示
			text_validate.setTextAppearance(ContactActivity.this, R.style.text_btn_re);
			text_validate.setBackgroundResource(R.drawable.icon_btn_re);
			text_validate.setText("重新获取("+millisUntilFinished /1000+")");
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
			if(orderContact==null){
				orderContact = new OrderContact();
			}
			orderContact.setName(((EditText)findViewById(R.id.edit_contact_name)).getText().toString());
			orderContact.setPhone(((EditText)findViewById(R.id.edit_contact_phone)).getText().toString());
//			orderContact.setNote(((EditText)findViewById(R.id.edit_contact_note)).getText().toString());
			intent.putExtra("orderContact", orderContact);
			setResult(RESULT_OK, intent);
			super.finish();
			break;
		case R.id.text_contact_validate:
			((TextView)v).setBackgroundResource(R.drawable.icon_btn_n);
			((TextView)v).setTextAppearance(this,R.style.text_btn_n);
			v.setClickable(false);
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
