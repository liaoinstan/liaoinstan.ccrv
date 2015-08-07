package com.example.ccrvlz.dcp.city;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.pojo.City;
import com.lidroid.xutils.BitmapUtils;

public class AdapterForCityList extends BaseAdapter{
	private Context context = null;
	private List<City> results = null;
	private int src;
	
	public List<City> getResults() {
		return results;
	}
	
    LayoutInflater inflater;  
    
    AdapterForCityList(Context context,BitmapUtils bitmapUtils,int src){
    	this.context = context;
    	this.src = src;
    	
    	results = new ArrayList<City>();	//一定在初始化的时候生成数据实体实例
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
			hoder1.text_city_name = (TextView) convertView.findViewById(R.id.text_city_name);
			hoder1.text_city_spell = (TextView) convertView.findViewById(R.id.text_city_spell);
			convertView.setTag(hoder1);
		}else {
			hoder1 = (ViewHolder1) convertView.getTag();
		}
		
		hoder1.text_city_name.setText(results.get(position).getName());
		hoder1.text_city_spell.setText(results.get(position).getSpell());
		
		return convertView;
	}
	
	public class ViewHolder1 {  
		TextView text_city_name;
		TextView text_city_spell;
    }

}
