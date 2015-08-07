/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ccrvlz.dcp;

import java.util.List;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.detail.DcpDetailActivity;
import com.example.pojo.TestPojo;

public class SuperAwesomeCardFragment extends Fragment implements IXListViewListener,OnItemClickListener{
	private Handler mHander = new Handler();
	private XListView listV;
	private AdapterForDcpList adapter;
	private View rootView = null;//����Fragment view
	private int position;
	private String title;
	private Handler mHandler;//�첽����
	
	public AdapterForDcpList getAdapter() {
		return adapter;
	}

	public static SuperAwesomeCardFragment newInstance(int position,String title) {
		SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
		Bundle b = new Bundle();
		b.putInt("position", position);
		b.putString("title", title);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		position = getArguments().getInt("position");
		title = getArguments().getString("title");
		mHandler = new Handler();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if(null != rootView){
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if(null != parent){
				parent.removeView(rootView);
			}
		}else{
			rootView = inflater.inflate(R.layout.dcp_list_layout,container,false);
			
			//����XlistView
			listV = (XListView) rootView.findViewById(R.id.dcp_list);
			listV.setPullLoadEnable(true);
			adapter = new AdapterForDcpList(this.getActivity());
			listV.setAdapter(adapter);
			
			//����XlistView����
			loadData(adapter.getResults());
			
			//������������item����
			listV.setXListViewListener(this);
			listV.setOnItemClickListener(this);
		}
		return rootView;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		Intent intent = new Intent(getActivity(), DcpDetailActivity.class);
		intent.putExtra("itemPojo", adapter.getResults().get(position-1));
		getActivity().startActivity(intent);
		getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable(){
			@Override
			public void run(){
				adapter.getResults().clear();
				loadData(adapter.getResults());
				adapter.notifyDataSetChanged();
				listV.stopRefresh();
			}
		}, 1000);
		
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable(){
			@Override
			public void run(){
				adapter.getResults().add(new TestPojo("10000", "�ɶ�-��կ��-����3��2��˫��������", "����", "", "6.2/6.5/6.9", "300", "600", 33, "http://imga1.pic21.com/bizhi/131211/05080/s06.jpg", "1", "0", "1"));
				adapter.getResults().add(new TestPojo("10000", "����-����1����", "����", "", "6.2/6.5/6.9", "600", "800", 33, "http://qzone.qqjay.com/u/files/2012/0422/15962a365fa2523253e0fa097c424c00.jpg", "1", "1", "1"));
				adapter.getResults().add(new TestPojo("10000", "���ɽ�����Σ�������", "����", "", "6.2/6.5/6.9", "998", "1600", 33, "http://qzone.qqjay.com/u/files/2012/0225/e7d40af9ab0dbc41dec57a89a6cd4bf9.jpg", "1", "0", "1"));
				adapter.notifyDataSetChanged();
				listV.stopLoadMore();
			}
		}, 1000);
	}

	/**
	 * ������ݵķ�����������û�к�̨�ӿڵĲ���
	 * @param results
	 */
	private void loadData(final List<TestPojo> results) {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				if ("�ɶ�".equals(title) || "ȫ��".equals(title)) {
					results.add(new TestPojo("10000", "�ɶ�-��կ��-����3��2��˫��������", "����", "", "6.2/6.5/6.9", "300", "600", 33, "http://imga1.pic21.com/bizhi/131211/05080/s06.jpg", "1", "1", "1"));
					results.add(new TestPojo("10000", "����-����1����", "����", "", "6.2/6.5/6.9", "600", "800", 33, "http://qzone.qqjay.com/u/files/2012/0422/15962a365fa2523253e0fa097c424c00.jpg", "1", "0", "1"));
					results.add(new TestPojo("10000", "���ɽ�����Σ�������", "����", "", "6.2/6.5/6.9", "998", "1600", 33, "http://qzone.qqjay.com/u/files/2012/0225/e7d40af9ab0dbc41dec57a89a6cd4bf9.jpg", "1", "0", "1"));
				}
				if ("����".equals(title) || "ȫ��".equals(title)) {
					results.add(new TestPojo("10000", "����-������-������", "�ɶ�/����", "", "6.2/6.5/6.9", "330", "600", 33, "http://img.kuping.cc/data/Image/2013hjw/1yue/4hao/fjbz/15/201314104441984.jpg", "1", "0", "1"));
					results.add(new TestPojo("10000", "��Ůɽ-2��", "ȫ��", "", "6.2/6.5/6.9", "998", "1600", 33, "http://www.bz55.com/uploads/allimg/121121/1-121121112240.jpg", "1", "0", "1"));
					results.add(new TestPojo("10000", "���ϴ�˹�", "�ɶ�/����", "", "6.2/6.5/6.9", "98", "150", 33, "http://img.wallba.com/data/Image/2012hjw/12yue/1228/wmyj/15/20121227152454408.jpg", "1", "1", "1"));
				}
				if ("����".equals(title) || "ȫ��".equals(title)) {
					results.add(new TestPojo("10000", "����-������-�߿Ƽ�˽�˶��ƺ����ײ�", "�ɶ�/����", "", "6.2/6.5/6.9", "300", "600", 33, "http://pic1.win4000.com/wallpaper/b/539929be59b27.jpg", "1", "0", "1"));
					results.add(new TestPojo("10000", "���ؼۡ�5��2�������", "�ɶ�/����", "", "6.2/6.5/6.9", "300", "600", 33, "http://www.bz55.com/uploads/allimg/121121/1-121121112247.jpg", "1", "1", "1"));
					results.add(new TestPojo("10000", "���ؼۡ�5��2�������", "�ɶ�/����", "", "6.2/6.5/6.9", "300", "600", 33, "http://img.wallba.com/data/Image/2012hjw/12yue/1228/wmyj/12/20121227152454377.jpg", "1", "1", "1"));
				}
				if ("���ͺ���".equals(title) || "ȫ��".equals(title)) {
					results.add(new TestPojo("10000", "�����»���", "���ͺ���", "", "6.2/6.5/6.9", "300", "600", 33, "http://pic16.nipic.com/20110830/5252423_121513518264_2.jpg", "1", "1", "1"));
					results.add(new TestPojo("10000", "���ͺ���-���������", "���ͺ���/����", "", "6.2/6.5/6.9", "300", "600", 33, "http://www.bz55.com/uploads/allimg/140627/138-14062G02A3.jpg", "1", "1", "1"));
					results.add(new TestPojo("10000", "����˫����5����ˮ��Ӿ", "���ͺ���/�ɶ�", "", "6.2/6.5/6.9", "300", "600", 33, "http://ppt.downhot.com/d/file/p/2013/09/26/41b03587d889887a9c086b10007f9114.jpg", "1", "0", "1"));
				}
				if ("��³ľ��".equals(title) || "ȫ��".equals(title)) {
					results.add(new TestPojo("10000", "��³ľ��-�߶�������", "��³ľ��", "", "6.2/6.5/6.9", "300", "600", 33, "http://www.netbian.com/d/file/20121011/d93e3358764c4086f7387939cbdff35a.jpg", "1", "0", "1"));
					results.add(new TestPojo("10000", "�½��ز������", "�ɶ�/��³ľ��", "", "6.2/6.5/6.9", "300", "600", 33, "http://img.51ztzj.com/M00/07/30/3eu8NVHWcgCATB8uAAIXeCDgNHY547_670x419.jpg", "1", "1", "1"));
					results.add(new TestPojo("10000", "�½��ز������", "�ɶ�/��³ľ��", "", "6.2/6.5/6.9", "300", "600", 33, "http://pic1.win4000.com/wallpaper/d/5141b8c95de88.jpg", "1", "0", "1"));
				}
				adapter.notifyDataSetChanged();
			}
		}, 0);
	}
}