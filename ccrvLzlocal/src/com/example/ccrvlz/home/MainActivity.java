package com.example.ccrvlz.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import org.afinal.simplecache.ACache;
import org.apache.http.Header;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.DcpActivity;
import com.example.ccrvlz.utils.BaiduContextUtil;
import com.example.pojo.RecomCamPojo;
import com.example.pojo.RecomTripPojo;
import com.example.pojo.RecomTripPojoList;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pcmgr.mld.detect.PcmgrAppMonitorTool;

public class MainActivity extends Activity implements MainListenerHelper,IXListViewListener{
	
	private String url1 = "http://test.cczc.hk:8887/wCamps/recomCamp";//推荐营地
	private String url2 = "http://test.cczc.hk:8887/strategy/recomList";//精彩推荐（攻略游记）
	private BitmapUtils bitmapUtils = null;
	private XListView mainlist = null;
	private AdapterForMainList adapter = null;
	private Handler mHandler;
	private ACache mCache;

	//private int page = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY,BaiduContextUtil.getMetaValue(this, "api_key"));//百度云推送
        bitmapUtils = BitmapHelp.getBitmapUtils(this.getApplicationContext());
        mCache = ACache.get(this);
        
        httpRequest(url1);
        
        showListView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
    	String chanId = intent.getStringExtra("channelID");
    	if(chanId!=null){
    		((TextView)findViewById(R.id.channel_id)).setText(chanId);
    	}
    }
    
	private void showListView() {
    	mainlist = (XListView) findViewById(R.id.mainlist);
    	mainlist.setPullLoadEnable(true);
    	adapter = new AdapterForMainList(this,bitmapUtils);
		mainlist.setAdapter(adapter);
		mainlist.setXListViewListener(this);
	}
    
    private void httpRequest(String url){
//    	List<RecomCamPojo> cacheCam = null;
//		try {
//			cacheCam = (List<RecomCamPojo>)mCache.get("recomCamPojo");
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//    	if(cacheCam!=null){
//    		adapter.getResultMaps().put("recomCamPojo", cacheCam);
//    		adapter.notifyDataSetChanged();
//    		return;
//    	}
		AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler hander = new AsyncHttpResponseHandler(){
        	@Override
        	public void onSuccess(int statusCode, Header[] headers, byte[] data) {
        		super.onSuccess(statusCode, headers, data);
        		try {
        			String str = new String(data,"UTF-8");
        			Gson gson = new Gson();
        			RecomCamPojo recomCamPojo = gson.fromJson(str, RecomCamPojo.class);
        			
        			ArrayList<RecomCamPojo> templist = new ArrayList<RecomCamPojo>();
        			templist.add(recomCamPojo);
        			adapter.getResultMaps().put("recomCamPojo", templist);
        			adapter.notifyDataSetChanged();
        			
//        			mCache.put("recomCamPojo",templist);	//存入缓存
        			List<RecomTripPojo> cacheTrip = (List<RecomTripPojo>)mCache.getAsObject("recomTripPojo");
//        	    	if (cacheTrip!=null ) {
//        	    		adapter.getResultMaps().put("recomTripPojo", cacheTrip);
//        	    		adapter.notifyDataSetChanged();
//        	    		mainlist.stopLoadMore();
//        				mainlist.stopRefresh();
//        	    		return;
//        			}
        			httpRequest2(url2);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	@Override
        	public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
        		super.onFailure(arg0, arg1, arg2, arg3);
        	}
        };
        RequestParams params = new RequestParams();
        params.put("addr", "成都");
        params.put("hickyNum", "0002F");
        params.put("serialNum", "888555");
        client.post(url, params, hander);
	}
    
    private void httpRequest2(String url){
		AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler hander = new AsyncHttpResponseHandler(){
        	@Override
        	public void onSuccess(int statusCode, Header[] headers, byte[] data) {
        		super.onSuccess(statusCode, headers, data);
        		try {
        			String str = new String(data,"UTF-8");
        			Gson gson = new Gson();
        			
        			RecomTripPojoList recomTripPojoList = gson.fromJson(str, RecomTripPojoList.class);
        			if (recomTripPojoList.getList().size()>0) {
						adapter.currentPage++;
					}
        			Map<String, List> results = adapter.getResultMaps();
        			if(results.get("recomTripPojo")==null){
        				results.put("recomTripPojo", recomTripPojoList.getList());
        				mCache.put("recomTripPojo",recomTripPojoList.getList());	//存入缓存
        			}else {
        				List<RecomTripPojo> tempReTris = (List<RecomTripPojo>)results.get("recomTripPojo");
        				tempReTris.addAll(recomTripPojoList.getList());
        				List<RecomTripPojo> tempCaches = (List<RecomTripPojo>)mCache.getAsObject("recomTripPojo");	//存入缓存
        				tempCaches.addAll(recomTripPojoList.getList());
					}
        			adapter.notifyDataSetChanged();
        			
        			mainlist.stopLoadMore();
        			mainlist.stopRefresh();
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	@Override
        	public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
        		super.onFailure(arg0, arg1, arg2, arg3);
        	}
        };
        RequestParams params = new RequestParams();
        params.put("currentPage",adapter.currentPage+""); 
        params.put("pageSize", "3"); 
        params.put("hickyNum", "0003I");
        params.put("serialNum", "888555");
        client.post(url, params, hander);
	}
    
    private void httpRequestAppend(String url) {
		AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler hander = new AsyncHttpResponseHandler(){
        	@Override
        	public void onSuccess(int statusCode, Header[] headers, byte[] data) {
        		super.onSuccess(statusCode, headers, data);
        		try {
        			String str = new String(data,"UTF-8");
        			Gson gson = new Gson();
        			
        			RecomTripPojoList recomTripPojoList = gson.fromJson(str, RecomTripPojoList.class);

        			adapter.getResultMaps().put("recomTripPojo", recomTripPojoList.getList());
        			adapter.notifyDataSetChanged();
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	@Override
        	public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
        		super.onFailure(arg0, arg1, arg2, arg3);
        	}
        };
        RequestParams params = new RequestParams();
        params.put("currentPage", ++adapter.currentPage);
        params.put("hickyNum", "0003I");
        params.put("serialNum", "888555");
        client.post(url, params, hander);
	}
    
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
        case R.id.img_dcp: 
        	Intent intent = new Intent(this, DcpActivity.class);
    		startActivity(intent);
    		overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
            break;  
        case R.id.img_fcxt: 
            break; 
        case R.id.img_omfc: 
            break; 
        case R.id.img_ssb: 
            break; 
        case R.id.img_whd: 
            break; 
        case R.id.img_xgn: 
            break; 
        case R.id.img_zfc: 
            break; 
        case R.id.img_zyd: 
            break;  
		}  
	}
	
	@Override
	protected void onDestroy() {
		PcmgrAppMonitorTool.exit();
		super.onDestroy();
	}


	@Override
	public void onRefresh() {
		adapter.currentPage = 1;
		adapter.getResultMaps().clear();
		httpRequest(url1);
	}


	@Override
	public void onLoadMore() {
		httpRequest2(url2);
	}  
}
