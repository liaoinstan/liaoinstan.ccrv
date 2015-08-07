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
import com.lidroid.xutils.BitmapUtils;

public class AdapterForOrderContactList extends BaseAdapter{
	private Context context = null;
	private int src;
	//data
	private List<OrderContact> results;
	public List<OrderContact> getResults() {
		return results;
	}
	
    LayoutInflater inflater;  
    
    AdapterForOrderContactList(Context context,BitmapUtils bitmapUtils,int src,List<OrderContact> results){
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
				hoder1.text_order_ges_list_item_name = (TextView)convertView.findViewById(R.id.text_order_ges_list_item_name);
				hoder1.text_order_ges_list_item_num = (TextView)convertView.findViewById(R.id.text_order_ges_list_item_num);
				convertView.setTag(hoder1);
		}else {
			hoder1 = (ViewHolder1) convertView.getTag();
		}
		
		hoder1.text_order_ges_list_item_name.setText(results.get(position).getName());
		hoder1.text_order_ges_list_item_num.setText(results.get(position).getPhone());
		
		return convertView;
	}
	
	public class ViewHolder1 {  
		TextView text_order_ges_list_item_name;
		TextView text_order_ges_list_item_num;
    }
}
