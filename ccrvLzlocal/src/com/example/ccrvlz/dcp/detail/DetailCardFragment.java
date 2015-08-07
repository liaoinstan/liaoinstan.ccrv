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

package com.example.ccrvlz.dcp.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.ccrvlz.R;

public class DetailCardFragment extends Fragment {
	private View rootView = null;//ª∫¥ÊFragment view
	private int position;
	private String data;

	public static DetailCardFragment newInstance(int position,String data) {
		DetailCardFragment f = new DetailCardFragment();
		Bundle b = new Bundle();
		b.putInt("position", position);
		b.putString("data", data);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt("position");
		data = getArguments().getString("data");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(null != rootView){
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if(null != parent){
				parent.removeView(rootView);
			}
		}else{
			rootView = inflater.inflate(R.layout.dcp_detail_card_layout,container,false);
			//////////////≥ı ºªØrootView
			WebView webView = (WebView)rootView.findViewById(R.id.web_dcp_detail_card);
			//String html = "<html><body><font>html5 test<br>html5 test<br>html5 test<br>html5 test<br></font></body></html>";
			webView.loadData(data, "text/html", "UTF-8");
		}
		return rootView;
	}
}