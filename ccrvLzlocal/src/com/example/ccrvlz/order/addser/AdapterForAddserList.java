package com.example.ccrvlz.order.addser;

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
import com.example.pojo.OrderAddSer;
import com.example.pojo.TestPojo;
import com.lidroid.xutils.BitmapUtils;

public class AdapterForAddserList extends BaseAdapter{
	private Context context = null;
	private ArrayList<OrderAddSer> results = null;
	private int src;
	
	public ArrayList<OrderAddSer> getResults() {
		return results;
	}
//	public int currentPage = 1;
	
    LayoutInflater inflater;  
    
    AdapterForAddserList(Context context,BitmapUtils bitmapUtils,int src){
    	this.context = context;
    	this.src = src;
    	
    	results = new ArrayList<OrderAddSer>();	//一定在初始化的时候生成数据实体实例
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
			hoder1.text_addser_title = (TextView)convertView.findViewById(R.id.text_addser_title);
			hoder1.text_addser_price = (TextView)convertView.findViewById(R.id.text_addser_price);
			hoder1.text_addser_unit = (TextView)convertView.findViewById(R.id.text_addser_unit);
			hoder1.text_addser_per = (TextView)convertView.findViewById(R.id.text_addser_per);
			hoder1.text_addser_num = (TextView)convertView.findViewById(R.id.text_addser_num);
			hoder1.img_addser_pic = (ImageView)convertView.findViewById(R.id.img_addser_pic);
			hoder1.img_addser_sub = (ImageView)convertView.findViewById(R.id.img_addser_sub);
			hoder1.img_addser_add = (ImageView)convertView.findViewById(R.id.img_addser_add);
			hoder1.img_addser_sub.setOnClickListener(new MyOnClickListener(position,hoder1));
			hoder1.img_addser_add.setOnClickListener(new MyOnClickListener(position,hoder1));
			convertView.setTag(hoder1);
		}else {
			hoder1 = (ViewHolder1) convertView.getTag();
		}
		OrderAddSer addser = results.get(position);
		hoder1.text_addser_title.setText(addser.getName());
		hoder1.text_addser_price.setText(addser.getPrice()+"");
		hoder1.text_addser_unit.setText(addser.getUnit());
		hoder1.text_addser_per.setText(addser.getPer()==1?"/单":"/天");
		hoder1.text_addser_num.setText(addser.getCount()<addser.getMin()?addser.getMin()+"":addser.getCount()+"");
		BitmapHelp.getBitmapUtils().display(hoder1.img_addser_pic, results.get(position).getSmallPic());
		hoder1.img_addser_sub.setImageResource(addser.getCount() <= addser.getMin()?R.drawable.icon_dcp_sub_false:R.drawable.hot_icon_dcp_sub_true);
		hoder1.img_addser_add.setImageResource(addser.getCount() >= addser.getMax()?R.drawable.icon_dcp_add_false:R.drawable.hot_icon_dcp_add_true);
		
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
			OrderAddSer addser = results.get(position);
			int num = addser.getCount()<addser.getMin()?addser.getMin():addser.getCount();
			switch (v.getId()) {
			case R.id.img_addser_sub:
				if(num > addser.getMin()){
					addser.setCount(--num);
					hoder.text_addser_num.setText(num+"");
				}
				if(addser.getCount() == addser.getMin()){
					hoder.img_addser_sub.setImageResource(R.drawable.icon_dcp_sub_false);
				}
				if(addser.getCount() == addser.getMax()-1){
					hoder.img_addser_add.setImageResource(R.drawable.hot_icon_dcp_add_true);
				}
				break;
			case R.id.img_addser_add:
				if(num < addser.getMax()){
					addser.setCount(++num);
					hoder.text_addser_num.setText(num+"");
				}
				if(addser.getCount() == addser.getMax()){
					hoder.img_addser_add.setImageResource(R.drawable.icon_dcp_add_false);
				}
				if(addser.getCount() == addser.getMin()+1){
					hoder.img_addser_sub.setImageResource(R.drawable.hot_icon_dcp_sub_true);
				}
				break;
			}
		}
	}
	
	public class ViewHolder1 {  
		TextView text_addser_title;
		TextView text_addser_price;
        TextView text_addser_unit;
        TextView text_addser_per;
        TextView text_addser_num;
        ImageView img_addser_pic;
        ImageView img_addser_sub;
        ImageView img_addser_add;
    }

}
