package com.example.ccrvlz.order.clause;

import com.example.ccrvlz.R;
import com.example.ccrvlz.R.layout;
import com.example.ccrvlz.R.menu;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.EditText;

public class ClauseActivity extends Activity implements OnClickListener{
	private WebView web_clause;
	private Handler mHander;	//“Ï≤Ω≤‚ ‘
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clause);
		mHander = new Handler();
		initBase();
		loadData();
	}

	private void initBase() {
		///////////////////≥ı ºªØ
		findViewById(R.id.img_action_back).setOnClickListener(this);
		web_clause = (WebView) findViewById(R.id.web_clause);
	}
	
	private void loadData() {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				String html = "<html><body><font>html5 test<br>html5 test<br>html5 test<br>html5 test<br></font></body></html>";
				web_clause.loadData(html, "text/html", "UTF-8");
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
}
