package com.example.ccrvlz.home;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.utils.StaticConfig;
import com.example.ccrvlz.utils.TimeUtil;
import com.example.pojo.CamPojo;
import com.example.pojo.RecomCamPojo;
import com.example.pojo.RecomTripPojo;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

public class AdapterForMainList extends BaseAdapter{
	private Context context = null;
	private MainListenerHelper helper = null;
	private BitmapUtils bitmapUtils = null;
	private CamPojo result = new CamPojo();
	private Map<String, List<Object>> resultMaps = new HashMap<String, List<Object>>();
	public int currentPage = 1;
	
	private BitmapDisplayConfig bigPicDisplayConfig = new BitmapDisplayConfig();
    //bigPicDisplayConfig.setShowOriginal(true); // 显示原始图片,不压缩, 尽量不要使用, 图片太大时容易OOM。
	
	public CamPojo getResult() {
		return result;
	}
	public Map getResultMaps() {
		return resultMaps;
	}
	
	final int VIEW_TYPE = 7;//类型总数
	final int STATICNUM = 3;//起始静态item数
    final int TYPE_1 = 0;  
    final int TYPE_2 = 1;  
    final int TYPE_3 = 2; 
    final int TYPE_4 = 3; 
    final int TYPE_5 = 4;
    final int TYPE_6 = 5; 
    final int TYPE_7 = 6; 
    LayoutInflater inflater;  
    
    AdapterForMainList(Context context,BitmapUtils bitmapUtils){
    	this.context = context;
    	this.helper = (MainListenerHelper) context;
    	this.bitmapUtils = bitmapUtils;
    	
    	bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
	    bigPicDisplayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
    }
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public Object getItem(int position) {
		//return result.getList().get(arg0-4);
		
		int reCamSize = resultMaps.get("recomCamPojo").size();
		int type = this.getItemViewType(position);
		if (type == TYPE_5) {
			return resultMaps.get("recomCamPojo").get(position-STATICNUM-1);
		}else if(type == TYPE_7){
			return resultMaps.get("recomTripPojo").get(position-STATICNUM-1-reCamSize-1);
		}else {
			return null;
		}
	}
	
	@Override
	public int getCount() {
		//return result.getList().size()+4;
		
		int count = 3;
		Iterator<String> it=resultMaps.keySet().iterator();    
		while(it.hasNext()){    
		     String key=it.next().toString();    
		     List<Object> list = resultMaps.get(key);
		     count = count + list.size() + 1;
		}
		return count;
	}
	
	@Override
	public int getItemViewType(int position) {
		int STARTNUM = STATICNUM - 1; //数据项前额外的位置
		if(position==0) {
			return TYPE_1;
		}else if(position==1){
			return TYPE_2;
		}else if(position==2){
			return TYPE_3;
		}else {
			int reCamSize = resultMaps.get("recomCamPojo")!=null?resultMaps.get("recomCamPojo").size():0;
			int reTriSize = resultMaps.get("recomTripPojo")!=null?resultMaps.get("recomTripPojo").size():0;
			if (position == STARTNUM+1) {
				return TYPE_4;
			}else if(STARTNUM+1<position && position<=STARTNUM+1+reCamSize){
				return TYPE_5;
			}else if(position == STARTNUM+1+reCamSize+1){
				return TYPE_6;
			}else if (STARTNUM+1+reCamSize+1<position && position<=STARTNUM+1+reCamSize+1+reTriSize) {
				return TYPE_7;
			}else {
				return -1;
			}
		}
	}
	
	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder1 hoder1 = null;
		ViewHolder2 hoder2 = null;
		ViewHolder3 hoder3 = null;
		ViewHolder4 hoder4 = null;
		ViewHolder5 hoder5 = null;
		ViewHolder6 hoder6 = null;
		ViewHolder7 hoder7 = null;
		int type = getItemViewType(position);
		
		if(convertView == null){
			inflater = LayoutInflater.from(context);  
			switch (type) {
			case TYPE_1:
				convertView = inflater.inflate(R.layout.main_list_item_1,  parent, false); 
				hoder1 = new ViewHolder1();
				convertView.setTag(hoder1);
				break;
			case TYPE_2:
				convertView = inflater.inflate(R.layout.main_list_item_2,  parent, false); 
				hoder2 = new ViewHolder2();
				convertView.setTag(hoder2);
				break;
			case TYPE_3:
				convertView = inflater.inflate(R.layout.main_list_item_3,  parent, false); 
				hoder3 = new ViewHolder3();
				hoder3.img_dcp = (ImageView) convertView.findViewById(R.id.img_dcp);
				hoder3.img_fcxt = (ImageView) convertView.findViewById(R.id.img_fcxt);
				hoder3.img_omfc = (ImageView) convertView.findViewById(R.id.img_omfc);
				hoder3.img_ssb = (ImageView) convertView.findViewById(R.id.img_ssb);
				hoder3.img_whd = (ImageView) convertView.findViewById(R.id.img_whd);
				hoder3.img_xgn = (ImageView) convertView.findViewById(R.id.img_xgn);
				hoder3.img_zfc = (ImageView) convertView.findViewById(R.id.img_zfc);
				hoder3.img_zyd = (ImageView) convertView.findViewById(R.id.img_zyd);
				convertView.setTag(hoder3);
				break;
			case TYPE_4:
				convertView = inflater.inflate(R.layout.main_list_item_4,  parent, false); 
				hoder4 = new ViewHolder4();
				convertView.setTag(hoder4);
				break;
			case TYPE_5:
				convertView = inflater.inflate(R.layout.main_list_item_5,  parent, false); 
				hoder5 = new ViewHolder5();
				hoder5.text_location = (TextView) convertView.findViewById(R.id.text_location);
				hoder5.text_zan = (TextView) convertView.findViewById(R.id.text_zan);
				hoder5.text_pj = (TextView) convertView.findViewById(R.id.text_pj);
				hoder5.text_title = (TextView) convertView.findViewById(R.id.text_title);
				hoder5.img_campImg  = (ImageView) convertView.findViewById(R.id.img_campImg);
				convertView.setTag(hoder5);
				break;
			case TYPE_6:
				convertView = inflater.inflate(R.layout.main_list_item_4,  parent, false); 
				hoder6 = new ViewHolder6();
				convertView.setTag(hoder6);
				break;
			case TYPE_7:
				convertView = inflater.inflate(R.layout.main_list_item_7,  parent, false); 
				hoder7 = new ViewHolder7();
				hoder7.text_title = (TextView) convertView.findViewById(R.id.text_title);
				hoder7.text_cusName = (TextView) convertView.findViewById(R.id.text_cusName);
				hoder7.text_time = (TextView) convertView.findViewById(R.id.text_time);
				hoder7.img_tripImg = (ImageView) convertView.findViewById(R.id.img_tripImg);
				convertView.setTag(hoder7);
				break;
			default:
				break;
			}
		}else {
			switch (type) {
			case TYPE_1:
				hoder1 = (ViewHolder1) convertView.getTag();
				break;
			case TYPE_2:
				hoder2 = (ViewHolder2) convertView.getTag();
				break;
			case TYPE_3:
				hoder3 = (ViewHolder3) convertView.getTag();
				break;
			case TYPE_4:
				hoder4 = (ViewHolder4) convertView.getTag();
				break;
			case TYPE_5:
				hoder5 = (ViewHolder5) convertView.getTag();
				break;
			case TYPE_6:
				hoder6 = (ViewHolder6) convertView.getTag();
				break;
			case TYPE_7:
				hoder7 = (ViewHolder7) convertView.getTag();
				break;
			default:
				break;
			}
		}
		
		switch (type) {
		case TYPE_1:
			break;
		case TYPE_2:
			break;
		case TYPE_3:
			OnClickListener onClickListener = new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					helper.onClick(arg0);
				}
			};
			hoder3.img_dcp.setOnClickListener(onClickListener);
			hoder3.img_fcxt.setOnClickListener(onClickListener);
			hoder3.img_omfc.setOnClickListener(onClickListener);
			hoder3.img_ssb.setOnClickListener(onClickListener);
			hoder3.img_whd.setOnClickListener(onClickListener);
			hoder3.img_xgn.setOnClickListener(onClickListener);
			hoder3.img_zfc.setOnClickListener(onClickListener);
			hoder3.img_zyd.setOnClickListener(onClickListener);
			break;
		case TYPE_4:
			break;
		case TYPE_5:
			hoder5.text_location.setText(((RecomCamPojo) getItem(position)).getCamp_address());
			hoder5.text_zan.setText(((RecomCamPojo) getItem(position)).getComp_good_num());
			hoder5.text_pj.setText(""+225);
			hoder5.text_title.setText(((RecomCamPojo) getItem(position)).getCamp_name());
			bitmapUtils.display(hoder5.img_campImg, StaticConfig.doMain+"/"+((RecomCamPojo) getItem(position)).getComp_img());
			break;
		case TYPE_6:
			break;
		case TYPE_7:
			hoder7.text_title.setText(((RecomTripPojo) getItem(position)).getT_title());
			hoder7.text_cusName.setText(((RecomTripPojo) getItem(position)).getUsernick());
			hoder7.text_time.setText(TimeUtil.cutDateStr(((RecomTripPojo) getItem(position)).getUpdate_time()));
			bitmapUtils.display(hoder7.img_tripImg, StaticConfig.doMain+"/"+((RecomTripPojo) getItem(position)).getT_cover_image());
			break;
		default:
			break;
		}
		
		return convertView;
	}
	
	static int i = 1;
	
	public class ViewHolder1 {  
        TextView textV1;  
        ImageView imgV1;  
    }  
  
    public class ViewHolder2 {  
        TextView textV1;  
        ImageView imgV1; 
    }  
  
    public class ViewHolder3 {  
        ImageView img_dcp;
        ImageView img_fcxt;
        ImageView img_omfc;
        ImageView img_ssb;
        ImageView img_whd;
        ImageView img_xgn;
        ImageView img_zfc;
        ImageView img_zyd;
    }
    public class ViewHolder4 {  
        TextView textV1;  
        ImageView imgV1;  
    }  
    public class ViewHolder5 {  
        TextView text_location;
        TextView text_zan;  
        TextView text_pj;  
        TextView text_title;  
        ImageView img_campImg;  
    }
    public class ViewHolder6 {  
        TextView textV1;  
        ImageView imgV1;  
    }  
    public class ViewHolder7 {  
        TextView text_title;
        TextView text_cusName;  
        TextView text_time;  
        ImageView img_tripImg;  
    } 
}
