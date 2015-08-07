package com.example.ccrvlz.dcp.city;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.ccrvlz.R;
import com.example.ccrvlz.app.MyApplication;
import com.example.ccrvlz.dcp.DcpActivity;
import com.example.ccrvlz.dcp.detail.AutoHListView;
import com.example.ccrvlz.utils.StringUtil;
import com.example.pojo.City;

public class CityActivity extends Activity implements OnClickListener,OnItemClickListener{
	private Handler mHander;	//�첽����
	private AutoHListView list_city;
	private TextView text_city_position;
	private AdapterForCityList adapter;
	//data
	private String city;
	//baidu map
	public LocationClient mLocationClient = null;
	public MyLocationListenner locationListenner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		mHander = new Handler();
		initBase();
		setBDMap();
		loadData(adapter.getResults());
	}
	
	private void initBase() {
		///////////////////��ʼ��
		text_city_position = (TextView)findViewById(R.id.text_city_position);
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.lay_city_position).setOnClickListener(this);
		list_city = ((AutoHListView)findViewById(R.id.list_city));
		adapter = new AdapterForCityList(this, null, R.layout.city_list_item);
		list_city.setAdapter(adapter);
		list_city.setOnItemClickListener(this);
	}
	
	private void setBDMap() {
		mLocationClient = ((MyApplication)getApplication()).mLocationClient;
		locationListenner = new MyLocationListenner();
		mLocationClient.registerLocationListener(locationListenner);
		//////////////option
		setLocation();
		mLocationClient.start();
	}
	
	private void setLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setIsNeedAddress(true);
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);
	}
	
	@Override
	protected void onDestroy() {
		mLocationClient.unRegisterLocationListener(locationListenner);	//ȡ��ע���λ�ü����������ڴ�й¶
		mLocationClient.stop();
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		case R.id.lay_city_position:
			Intent intent = null;
			if (!StringUtil.isEmpty(city)) {
				if(checkCityName(adapter.getResults(),city))
				{
					intent = new Intent();
					intent.setClass(this, DcpActivity.class);
					intent.putExtra("city", city);
					setResult(RESULT_OK,intent);
					finish();
				}else {
					Toast.makeText(this, "�ó��л�û��Ӫ��", Toast.LENGTH_SHORT).show();
				}
			} else {	//��λδ�ɹ�
				mLocationClient.stop();
				setLocation();
				mLocationClient.start();
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		Intent intent = new Intent();
		intent.setClass(this, DcpActivity.class);
		intent.putExtra("city", adapter.getResults().get(position).getName());
		setResult(RESULT_OK,intent);
		finish();
	}

	private class MyLocationListenner implements BDLocationListener{
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (!StringUtil.isEmpty(location.getCity())) {
				city = StringUtil.trimLast(location.getCity(), "��");
				text_city_position.setTextColor(R.color.cc_lightdark);
				text_city_position.setText("��ǰ���У�"+city+"��");
			}else {
				text_city_position.setTextColor(R.color.cc_lightdark);
				text_city_position.setText("��λʧ�ܣ�������¶�λ");
			}
		}
	}
	
	private void loadData(final List<City> results) {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				list_city.setVisibility(View.GONE);
				results.add(new City("10000", "����", "Chong Qing"));
				results.add(new City("10001", "�ɶ�", "Cheng Du"));
				results.add(new City("10002", "����", "Gui Lin"));
				results.add(new City("10003", "����", "Li Jiang"));
				results.add(new City("10004", "����", "San Ya"));
				adapter.notifyDataSetChanged();
				list_city.autoHeight(true);
			}
		}, 500);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.state, R.anim.out_to_right);
	}

	private boolean checkCityName(List<City> citys ,String currentCity){
		for (City city : citys) {
			if (city.getName().equals(currentCity)) {
				return true;
			}
		}
		return false;
	}
}
