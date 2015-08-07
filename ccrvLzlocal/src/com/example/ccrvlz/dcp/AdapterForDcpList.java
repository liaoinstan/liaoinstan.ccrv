package com.example.ccrvlz.dcp;

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

public class AdapterForDcpList extends BaseAdapter implements OnClickListener{
	private Context context = null;
	private BitmapUtils bitmapUtils;
	private List<TestPojo> results = null;
	private int position;
	
	public List<TestPojo> getResults() {
		return results;
	}
	
    LayoutInflater inflater;  
    
    AdapterForDcpList(Context context){
    	this.context = context;
    	this.results = new ArrayList<TestPojo>();
    	this.bitmapUtils = BitmapHelp.getBitmapUtils();
    	this.bitmapUtils.configDefaultLoadingImage(R.drawable.bk_dcp_item);
    	this.bitmapUtils.configDefaultLoadFailedImage(R.drawable.bk_dcp_item);
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
		this.position = position;
		ViewHolder1 hoder1 = null;
		
		if(convertView == null){
			inflater = LayoutInflater.from(context);  
				convertView = inflater.inflate(R.layout.dcp_list_item,  parent, false); 
				hoder1 = new ViewHolder1();
				hoder1.text_dcp_title = (TextView)convertView.findViewById(R.id.text_dcp_title);
				hoder1.text_dcp_addr = (TextView)convertView.findViewById(R.id.text_dcp_addr);
				hoder1.text_startdate = (TextView)convertView.findViewById(R.id.text_startdate);
				hoder1.text_dcp_price = (TextView)convertView.findViewById(R.id.text_dcp_price);
				hoder1.text_dcp_oldprice = (TextView)convertView.findViewById(R.id.text_dcp_oldprice);
				hoder1.text_dcp_num = (TextView)convertView.findViewById(R.id.text_dcp_num);
				hoder1.img_pic = (ImageView)convertView.findViewById(R.id.img_pic);
				hoder1.img_dcp_like = (ImageView)convertView.findViewById(R.id.img_dcp_like);
				hoder1.img_dcp_star = (ImageView)convertView.findViewById(R.id.img_dcp_star);
				convertView.setTag(hoder1);
				
		}else {
				hoder1 = (ViewHolder1) convertView.getTag();
		}
		
		hoder1.text_dcp_title.setText(results.get(position).getTitle());
		hoder1.text_dcp_addr.setText(results.get(position).getAddr());
		hoder1.text_startdate.setText(results.get(position).getDate());
		hoder1.text_dcp_price.setText(results.get(position).getPrice());
		hoder1.text_dcp_oldprice.setText(results.get(position).getPrice2());
		hoder1.text_dcp_num.setText(results.get(position).getNum()+"");
		bitmapUtils.display(hoder1.img_pic, results.get(position).getBigImg() ,new MyBitmapLoadCallBack());
		hoder1.img_dcp_like.setImageResource("1".equals(results.get(position).getLike())?R.drawable.icon_dcp_like:R.drawable.icon_dcp_like2);
		hoder1.img_dcp_like.setOnClickListener(this);
		
		return convertView;
	}
	
	@Override
	public void onClick(View v) {
		if ("1".equals(results.get(position).getLike())) {//œ≤ª∂ …Ë÷√≥… ≤ªœ≤ª∂
			results.get(position).setLike("0");
			((ImageView)v).setImageResource(R.drawable.icon_dcp_like2);
		}else {//≤ªœ≤ª∂ …Ë÷√≥… œ≤ª∂
			results.get(position).setLike("1");
			((ImageView)v).setImageResource(R.drawable.icon_dcp_like);
		}
	}  
	
	public class ViewHolder1 {  
        TextView text_dcp_title;
        TextView text_dcp_addr;
        TextView text_startdate;
        TextView text_dcp_price;
        TextView text_dcp_oldprice;
        TextView text_dcp_num;
        ImageView img_dcp_like;
        ImageView img_dcp_star;
        ImageView img_pic;
    }

	
}
