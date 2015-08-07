package com.example.ccrvlz.order.calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.example.ccrvlz.utils.TimeUtil;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;

public class PriceDecorator implements CalendarCellDecorator {
	private List<DatePojo> datePojos;
	private static Date today = new Date();
	
	public PriceDecorator(List<DatePojo> datePojos){
		this.datePojos = datePojos;
	}
	
	@Override
	public void decorate(CalendarCellView cellView, Date date) {
		String dateStr = Integer.toString(date.getDate());
		SpannableString strSpan;
		long time = today.getTime()-date.getTime();
		if(cellView.isToday()){
			dateStr = "½ñÌì";
		}
		int index = DatePojo.isIn(date,datePojos);
		if (index != -1 && cellView.isSelectable()) {
			strSpan = new SpannableString(dateStr + "\n£¤" + datePojos.get(index).getPrice());
			strSpan.setSpan(new StyleSpan(Typeface.BOLD), 0, dateStr.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			strSpan.setSpan(new RelativeSizeSpan(0.8f), dateStr.length(),strSpan.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			strSpan.setSpan(new ForegroundColorSpan(0xfff87b5c), dateStr.length(),strSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			strSpan = new SpannableString(dateStr + "\n");
		}
		cellView.setText(strSpan);
	}
	
	

}
