package com.example.ccrvlz.order.calendar;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ccrvlz.R;
import com.example.ccrvlz.utils.TimeUtil;

public class CalendarActivity extends Activity implements OnClickListener{
	private Date startDate;
	private WebView web_date;
	private Handler mHander;	//异步测试
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		startDate = (Date)getIntent().getSerializableExtra("startDate");
		mHander = new Handler();
		initBase();
		loadData();
	}

	private void initBase() {
		///////////////////初始化
		findViewById(R.id.img_action_back).setOnClickListener(this);
		web_date = (WebView) findViewById(R.id.web_date);
		
		WebSettings setting = web_date.getSettings();  
		setting.setDefaultTextEncodingName("utf-8");
		setting.setJavaScriptEnabled(true);
		web_date.setWebChromeClient(new WebChromeClient());
		web_date.addJavascriptInterface(new JavaApp(this), "JavaApp");
	}
	
	private void loadData() {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
//				web_date.loadUrl("file:///android_asset/calendar.html");
				web_date.loadUrl("file:///android_asset/template.html");
			}
		}, 0);
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
	
	
	public class JavaApp {  
		private Context mContext;

		public JavaApp(Context context) {
			this.mContext = context;
		}
		// webview中调用toast原生组件
		public void showToast(String toast) {
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		}
		
		public void ret(String dateStr) {
			Intent intent = new Intent();
			intent.putExtra("startDate", TimeUtil.getDateByStr("yyyy/MM/dd", dateStr));
			setResult(RESULT_OK, intent);
			finish();
		}
	}

}
