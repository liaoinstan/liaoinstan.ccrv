package com.example.ccrvlz.order;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.pojo.OrderContact;
import com.example.pojo.OrderItem;
import com.example.pojo.OrderTraveller;
import com.lidroid.xutils.BitmapUtils;

public class AdapterForOrderDetailList extends BaseAdapter{
	private Context context = null;
	private int src;
	//data
	private List<OrderItem> results;
	public List<OrderItem> getResults() {
		return results;
	}
	
    LayoutInflater inflater;  
    
    AdapterForOrderDetailList(Context context,BitmapUtils bitmapUtils,int src,List<OrderItem> results){
    	this.context = context;
    	this.src = src;
    	this.results = results;
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
				convertView = inflater.inflate(src,  parent, false); 
				hoder1 = new ViewHolder1();
				hoder1.text_order_detail_list_item_name = (TextView)convertView.findViewById(R.id.text_order_detail_list_item_name);
				hoder1.text_order_detail_list_item_price = (TextView)convertView.findViewById(R.id.text_order_detail_list_item_price);
				convertView.setTag(hoder1);
		}else {
			hoder1 = (ViewHolder1) convertView.getTag();
		}
		OrderItem detail = results.get(position);
		hoder1.text_order_detail_list_item_name.setText(detail.getName());
		if(position == getCount()-1){//最后一项有特殊的样式
			hoder1.text_order_detail_list_item_price.setText("￥"+detail.getFlag()*detail.getValue());
//			hoder1.text_order_detail_list_item_price.setTextColor(0xfff87b5c);
		}else {
			hoder1.text_order_detail_list_item_price.setText(detail.getFlag()*detail.getValue()+"");
		}
		
		return convertView;
	}
	
	public class ViewHolder1 {  
		TextView text_order_detail_list_item_name;
		TextView text_order_detail_list_item_price;
    }
}
