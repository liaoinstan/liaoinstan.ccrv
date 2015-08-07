package com.example.ccrvlz.order.coupon;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.utils.TimeUtil;
import com.example.pojo.OrderCoupon;
import com.example.pojo.TestPojo;
import com.lidroid.xutils.BitmapUtils;

public class AdapterForCouponList extends BaseAdapter{
	private Context context = null;
	private int src;
	private List<OrderCoupon> results = null;
	private int fragPosition;
	
	public List<OrderCoupon> getResults() {
		return results;
	}
	
    LayoutInflater inflater;  
    
    AdapterForCouponList(Context context,BitmapUtils bitmapUtils,int src,int fragPosition){
    	this.context = context;
    	this.src = src ;
    	this.fragPosition = fragPosition;
    	results = new ArrayList<OrderCoupon>();
    }
	
	@Override
	public long getItemId(int position) {
		return Long.parseLong(results.get(position).getId());
	}
	
	@Override
	public Object getItem(int position) {
		return results.get(position);
	}
	
	@Override
	public int getCount() {
		return results.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder1 hoder1 = null;
		
		if(convertView == null){
			inflater = LayoutInflater.from(context);  
			convertView = inflater.inflate(src,  parent, false); 
			hoder1 = new ViewHolder1();
			hoder1.text_coupon_price = (TextView)convertView.findViewById(R.id.text_coupon_price);
			hoder1.text_coupon_tf = (TextView)convertView.findViewById(R.id.text_coupon_tf);
			hoder1.text_coupon_limit = (TextView)convertView.findViewById(R.id.text_coupon_limit);
			hoder1.text_coupon_starttime = (TextView)convertView.findViewById(R.id.text_coupon_starttime);
			hoder1.text_coupon_endtime = (TextView)convertView.findViewById(R.id.text_coupon_endtime);
			if(fragPosition == 0){
				hoder1.img_coupon_check = (ImageView)convertView.findViewById(R.id.img_coupon_check);
				hoder1.img_coupon_check.setOnClickListener(new MyOnClickListener(hoder1,position));
			}
			convertView.setTag(hoder1);
		}else {
			hoder1 = (ViewHolder1) convertView.getTag();
		}
		
		hoder1.text_coupon_price.setText(Math.round(results.get(position).getPrice())+"");
		hoder1.text_coupon_limit.setText(0 == Math.round(results.get(position).getLimit())?"不限":"满"+Math.round(results.get(position).getLimit())+"可用");
		hoder1.text_coupon_starttime.setText(TimeUtil.getTimeSimple(results.get(position).getStartDate()));
		hoder1.text_coupon_endtime.setText(TimeUtil.getTimeSimple(results.get(position).getEndDate()));
		if (fragPosition == 0) {
			hoder1.img_coupon_check.setVisibility(View.VISIBLE);
			setSelect(hoder1,position);
		}
//		hoder1.img_dcp_like.setImageResource("1".equals(results.get(position).getLike())?R.drawable.icon_dcp_like:R.drawable.icon_dcp_like2);
		
		return convertView;
	}
	

	private void setSelect(ViewHolder1 hoder1, int position) {
		if (0 == results.get(position).getSelect()) {
			hoder1.img_coupon_check.setImageResource(R.drawable.icon_coupon_uncheck);
			hoder1.text_coupon_tf.setTextAppearance(context,R.style.text_coupon_price_y1);
			hoder1.text_coupon_price.setTextAppearance(context,R.style.text_coupon_price_y1);
		}else {
			hoder1.img_coupon_check.setImageResource(R.drawable.icon_coupon_check);
			hoder1.text_coupon_tf.setTextAppearance(context,R.style.text_coupon_price_y2);
			hoder1.text_coupon_price.setTextAppearance(context,R.style.text_coupon_price_y2);
		}
	}


	private class MyOnClickListener implements OnClickListener{
		private ViewHolder1 hoder1;
		private int position;
		public MyOnClickListener(ViewHolder1 hoder1,int position){
			this.hoder1 = hoder1;
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			if (1 == results.get(position).getSelect()) {
				results.get(position).setSelect(0);
			}else {
				results.get(position).setSelect(1);
			}
			setSelect(hoder1,position);
		}
	}
	
	public class ViewHolder1 {  
        TextView text_coupon_price;
        TextView text_coupon_tf;
        TextView text_coupon_limit;
        TextView text_coupon_starttime;
        TextView text_coupon_endtime;
        ImageView img_coupon_check;
    }
}
