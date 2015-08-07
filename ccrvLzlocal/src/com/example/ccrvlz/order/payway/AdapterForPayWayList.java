package com.example.ccrvlz.order.payway;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.pojo.TestPojo;
import com.lidroid.xutils.BitmapUtils;

public class AdapterForPayWayList extends BaseAdapter{
	private Context context = null;
	private List<TestPojo> results = null;
	private int src;
	
	public List<TestPojo> getResults() {
		return results;
	}
//	public int currentPage = 1;
	
    LayoutInflater inflater;  
    
    AdapterForPayWayList(Context context,BitmapUtils bitmapUtils,int src){
    	this.context = context;
    	this.src = src;
    	
    	results = new ArrayList<TestPojo>();	//一定在初始化的时候生成数据实体实例
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
			hoder1.text_payway_title = (TextView) convertView.findViewById(R.id.text_payway_title);
			hoder1.text_payway_intro = (TextView) convertView.findViewById(R.id.text_payway_intro);
			hoder1.img_payway_logo = (ImageView) convertView.findViewById(R.id.img_payway_logo);
			convertView.setTag(hoder1);
		}else {
				hoder1 = (ViewHolder1) convertView.getTag();
		}
		
		hoder1.text_payway_title.setText(results.get(position).getTitle());
		hoder1.text_payway_intro.setText(results.get(position).getDetail());
		if ("weixin".equals(results.get(position).getStatus())) {
			hoder1.img_payway_logo.setImageResource(R.drawable.logo_weixin);
		}else if("zhifubao".equals(results.get(position).getStatus())){
			hoder1.img_payway_logo.setImageResource(R.drawable.logo_zhifubao);
		}
		
		return convertView;
	}
	
	public class ViewHolder1 {  
		TextView text_payway_title;
		TextView text_payway_intro;
        ImageView img_payway_logo;
    }

}
