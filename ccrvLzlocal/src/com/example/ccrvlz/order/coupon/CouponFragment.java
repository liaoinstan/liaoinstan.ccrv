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

package com.example.ccrvlz.order.coupon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.detail.AutoHListView;
import com.example.pojo.OrderAddSer;
import com.example.pojo.OrderCoupon;

public class CouponFragment extends Fragment implements OnClickListener{

	private AutoHListView listV;
	private AdapterForCouponList adapter;
	private View rootView = null;//缓存Fragment view
	private int position;
	private List<OrderCoupon> results = new ArrayList<OrderCoupon>();
	public void setPosition(int position) {
		this.position = position;
	}
	public void setResults(List<OrderCoupon> results) {
		this.results = results;
	}
	public static CouponFragment newInstance(int position) {
		CouponFragment f = new CouponFragment();
		Bundle b = new Bundle();
		b.putInt("position", position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		position = getArguments().getInt("position");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.coupon_list_layout,container,false);
		
		if (position == 0) {
			rootView.findViewById(R.id.act_next).setVisibility(View.VISIBLE);
			rootView.findViewById(R.id.act_next).setOnClickListener(this);
		}
		
		//设置XlistView
		listV = (AutoHListView) rootView.findViewById(R.id.coupon_list);
		adapter = new AdapterForCouponList(this.getActivity(), null,R.layout.coupon_list_item, position);
		listV.setAdapter(adapter);
		
		//加载XlistView数据
//		listV.setVisibility(View.GONE);//这行代码，会导致不可用优惠券无法显示
		adapter.getResults().addAll(results);
		adapter.notifyDataSetChanged();
		listV.autoHeight(true);
			
		return rootView;
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.act_next:
			remove(results);
			intent.putExtra("coupons", (ArrayList<OrderCoupon>)results);
			this.getActivity().setResult(this.getActivity().RESULT_OK, intent);
			this.getActivity().finish();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 去除未选择的优惠券返回
	 * @param results
	 */
	private void remove(List<OrderCoupon> results) {
		for (Iterator<OrderCoupon> it = results.iterator(); it.hasNext();) {
			OrderCoupon coupon = it.next();
			if (coupon.getSelect() == 0) {
				it.remove();
			}
		}
	}
}