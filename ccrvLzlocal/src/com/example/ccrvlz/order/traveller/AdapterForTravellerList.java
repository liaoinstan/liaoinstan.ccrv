package com.example.ccrvlz.order.traveller;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.utils.NumUtil;
import com.example.pojo.OrderTraveller;

public class AdapterForTravellerList extends BaseAdapter{
	private Context context = null;
	private ArrayList<OrderTraveller> results = null;
	private int src;
	
	public ArrayList<OrderTraveller> getResults() {
		return results;
	}
//	public int currentPage = 1;
	
    LayoutInflater inflater;  
    
    AdapterForTravellerList(Context context,int src){
    	this.context = context;
    	this.src = src;
    	
    	results = new ArrayList<OrderTraveller>();	//一定在初始化的时候生成数据实体实例
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
			hoder1.edit_traveller_name = (EditText)convertView.findViewById(R.id.edit_traveller_name);
			hoder1.edit_traveller_idcard = (EditText)convertView.findViewById(R.id.edit_traveller_idcard);
			hoder1.text_traveller_index = (TextView)convertView.findViewById(R.id.text_traveller_index);
			if (getCount()==1) {
				hoder1.lay_traveller_head = convertView.findViewById(R.id.lay_traveller_head);
			}
			convertView.setTag(hoder1);
		}else {
			hoder1 = (ViewHolder1) convertView.getTag();
		}
		
//		以下注释代码会引起内存泄露，该activity无法正常释放，因为edittext隐式保留了context的强引用，这个bug困扰了我很久
//		if (position == 0) {
//			hoder1.edit_traveller_name.requestFocus();
//		}
		hoder1.text_traveller_index.setText("第"+NumUtil.intToZH(position+1)+"位旅客");
		hoder1.edit_traveller_name.setText(results.get(position).getName());
		hoder1.edit_traveller_idcard.setText(results.get(position).getIdCard());
		if (getCount()==1) {
			hoder1.lay_traveller_head.setVisibility(View.GONE);
		}
		
		return convertView;
	}
	
	public class ViewHolder1 {
		View lay_traveller_head;
		TextView text_traveller_index;
		EditText edit_traveller_name;
		EditText edit_traveller_idcard;
    }

}
