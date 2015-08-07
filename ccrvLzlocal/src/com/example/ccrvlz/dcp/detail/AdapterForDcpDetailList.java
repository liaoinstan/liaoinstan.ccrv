package com.example.ccrvlz.dcp.detail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.order.OrderActivity;
import com.example.pojo.OrderAll;
import com.example.pojo.OrderPak;
import com.example.pojo.TestPojo;
import com.lidroid.xutils.BitmapUtils;

public class AdapterForDcpDetailList extends BaseAdapter{
	private Context context = null;
	private List<OrderPak> results = null;
	
	public List<OrderPak> getResults() {
		return results;
	}
//	public int currentPage = 1;
	
    LayoutInflater inflater;  
    
    AdapterForDcpDetailList(Context context,BitmapUtils bitmapUtils){
    	this.context = context;
    	
    	results = new ArrayList<OrderPak>();	//一定在初始化的时候生成数据实体实例
    }
	
	@Override
	public long getItemId(int position) {
		return position;
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
				convertView = inflater.inflate(R.layout.dcp_list_detail_item,  parent, false); 
				hoder1 = new ViewHolder1();
				hoder1.text_dcp_detail_tc_title = (TextView)convertView.findViewById(R.id.text_dcp_detail_tc_title);
				hoder1.text_dcp_detail_tc_price = (TextView)convertView.findViewById(R.id.text_dcp_detail_tc_price);
				hoder1.text_dcp_detail_tc_btn = (TextView)convertView.findViewById(R.id.text_dcp_detail_tc_btn);
				hoder1.text_dcp_detail_tc_btn.setOnClickListener(new MyOnClickListener(position));
				convertView.setTag(hoder1);
		}else {
				hoder1 = (ViewHolder1) convertView.getTag();
		}
		
		hoder1.text_dcp_detail_tc_title.setText(results.get(position).getName());
		hoder1.text_dcp_detail_tc_price.setText(results.get(position).getPrice()+"");
		if (0 == results.get(position).getCount()) {
			hoder1.text_dcp_detail_tc_btn.setText("售罄");
			hoder1.text_dcp_detail_tc_btn.setTextAppearance(context, R.style.text_btn_n);
			hoder1.text_dcp_detail_tc_btn.setBackgroundResource(R.drawable.icon_btn_n);
		} else {
			hoder1.text_dcp_detail_tc_btn.setText("抢购");
			hoder1.text_dcp_detail_tc_btn.setTextAppearance(context, R.style.text_btn_y);
			hoder1.text_dcp_detail_tc_btn.setBackgroundResource(R.drawable.hot_icon_dcp_btn_y);
		}
		
//		hoder1.textV1.setText(((RecomCamPojo) getItem(position)).getCamp_address());
//		bitmapUtils.display(hoder1.imgV1, StaticConfig.doMain+"/"+((RecomCamPojo) getItem(position)).getComp_img());
		
		return convertView;
	}
	
	private class MyOnClickListener implements OnClickListener{
		private int position;
		MyOnClickListener(int position){
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			if(0 != results.get(position).getCount()){
				TestPojo testPojo = ((DcpDetailActivity)context).getTestPojo();
				Intent intent = new Intent((DcpDetailActivity)context, OrderActivity.class);
				OrderAll orderAll = new OrderAll(testPojo.getId(),5, testPojo.getTitle(),testPojo.getBigImg(),testPojo.getAddr(),388F,138F,Float.parseFloat(testPojo.getPrice()), new Date());
				intent.putExtra("orderAll", orderAll);
				intent.putExtra("orderPak", results.get(position));
				((DcpDetailActivity)context).startActivity(intent);
				((DcpDetailActivity)context).overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
				
//				Intent intent = new Intent((DcpDetailActivity)context, PakActivity.class);
//				intent.putExtra("itemPojo", results.get(position));
//				((DcpDetailActivity)context).startActivity(intent);
			}
		}
	}
	
	public class ViewHolder1 {  
		TextView text_dcp_detail_tc_title;
        TextView text_dcp_detail_tc_price;
        TextView text_dcp_detail_tc_btn;
    }
}
