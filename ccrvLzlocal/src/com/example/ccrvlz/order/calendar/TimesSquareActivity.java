package com.example.ccrvlz.order.calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.ccrvlz.R;
import com.example.ccrvlz.utils.LoadingViewUtil;
import com.example.ccrvlz.utils.TimeUtil;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.DateSelectableFilter;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;

public class TimesSquareActivity extends Activity implements OnClickListener{
	private Date startDate;
	private CalendarPickerView calendar;
	private Handler mHander;	//“Ï≤Ω≤‚ ‘
	//data
	private List<DatePojo> datePojos = new ArrayList<DatePojo>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_times_square);
		startDate = (Date)getIntent().getSerializableExtra("startDate");
		mHander = new Handler();
		initBase();
		loadData();
	}

	private void initBase() {
		///////////////////≥ı ºªØ
		findViewById(R.id.img_action_back).setOnClickListener(this);
	}
	
	private void initCtrl() {
		calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
		
		calendar.setDecorators(Arrays.<CalendarCellDecorator>asList(new PriceDecorator(datePojos)));
		calendar.setDateSelectableFilter(new DateSelectableFilter() {
			@Override
			public boolean isDateSelectable(Date date) {
				return DatePojo.isIn(date,datePojos)!=-1?true:false;
			}
		});
		calendar.setOnDateSelectedListener(new OnDateSelectedListener() {
			@Override
			public void onDateSelected(Date date) {
				Intent intent = new Intent();
				intent.putExtra("startDate", date);
				setResult(RESULT_OK, intent);
				finish();
			}
			@Override
			public void onDateUnselected(Date date) {
			}
		});
	    calendar.init(new Date(), TimeUtil.add(new Date(), Calendar.MONTH, 2)) //
	        .inMode(SelectionMode.SINGLE) //
	        .withSelectedDate(datePojos.get(0).getDate());
	}

	private void loadData() {
		final View loading = LoadingViewUtil.showLoadingInCenter(this, R.layout.layout_loading);
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				Date date = TimeUtil.getDateNoSC(new Date());
				for (int i = 0; i < 10; i++) {
					date = TimeUtil.add(date, 5, 1);
					datePojos.add(new DatePojo(date, i));
				}
				initCtrl();
				LoadingViewUtil.dismiss(TimesSquareActivity.this, loading);
			}
		}, 100);
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
