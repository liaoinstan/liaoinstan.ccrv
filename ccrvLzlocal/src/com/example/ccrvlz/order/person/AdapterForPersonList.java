package com.example.ccrvlz.order.person;

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
import com.example.ccrvlz.home.BitmapHelp;
import com.example.pojo.TestPojo;
import com.lidroid.xutils.BitmapUtils;

public class AdapterForPersonList extends BaseAdapter{
	private Context context = null;
	private int src;
	//data
	private List<TestPojo> results = null;
	private int maxCount = 100;
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public List<TestPojo> getResults() {
		return results;
	}
//	public int currentPage = 1;
	
    LayoutInflater inflater;  
    
    AdapterForPersonList(Context context,BitmapUtils bitmapUtils,int src){
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
				hoder1.text_person_title = (TextView)convertView.findViewById(R.id.text_person_title);
				hoder1.text_person_price = (TextView)convertView.findViewById(R.id.text_person_price);
				hoder1.text_person_num = (TextView)convertView.findViewById(R.id.text_person_num);
				hoder1.img_person_sub = (ImageView)convertView.findViewById(R.id.img_person_sub);
				hoder1.img_person_add = (ImageView)convertView.findViewById(R.id.img_person_add);
				hoder1.img_person_sub.setOnClickListener(new MyOnClickListener(position,hoder1));
				hoder1.img_person_add.setOnClickListener(new MyOnClickListener(position,hoder1));
				convertView.setTag(hoder1);
		}else {
				hoder1 = (ViewHolder1) convertView.getTag();
		}
		
		hoder1.text_person_title.setText(results.get(position).getTitle());
		hoder1.text_person_price.setText(results.get(position).getPrice());
		hoder1.text_person_num.setText(results.get(position).getNum()+"");
		hoder1.img_person_sub.setImageResource(results.get(position).getNum() <= 0?R.drawable.icon_dcp_sub_false:R.drawable.hot_icon_dcp_sub_true);
		hoder1.img_person_add.setImageResource(results.get(position).getNum() >= maxCount?R.drawable.icon_dcp_add_false:R.drawable.hot_icon_dcp_add_true);
		return convertView;
	}
	
	private class MyOnClickListener implements OnClickListener{
		private int position;
		private ViewHolder1 hoder;
		MyOnClickListener(int position,ViewHolder1 hoder){
			this.position = position;
			this.hoder = hoder;
		}
		@Override
		public void onClick(View v) {
			TestPojo person = results.get(position);
			int num = person.getNum();
			switch (v.getId()) {
			case R.id.img_person_sub:
				if(num > 0){
					person.setNum(--num);
					hoder.text_person_num.setText(num+"");
				}
				if(person.getNum() == 0){
					hoder.img_person_sub.setImageResource(R.drawable.icon_dcp_sub_false);
				}
				if(person.getNum() == maxCount-1){
					hoder.img_person_add.setImageResource(R.drawable.hot_icon_dcp_add_true);
				}
				break;
			case R.id.img_person_add:
				if(num < maxCount){
					person.setNum(++num);
					hoder.text_person_num.setText(num+"");
				}
				if(person.getNum() == maxCount){
					hoder.img_person_add.setImageResource(R.drawable.icon_dcp_add_false);
				}
				if(person.getNum() == 1){
					hoder.img_person_sub.setImageResource(R.drawable.hot_icon_dcp_sub_true);
				}
				break;
			}
		}
	}
	
	public class ViewHolder1 {  
		TextView text_person_title;
		TextView text_person_price;
        TextView text_person_num;
        ImageView img_person_sub;
        ImageView img_person_add;
    }

}
