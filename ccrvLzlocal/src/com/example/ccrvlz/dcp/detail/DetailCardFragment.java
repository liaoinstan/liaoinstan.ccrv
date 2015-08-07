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

	private static final String ARG_POSITION = "position";
	private int position;
	private String data;
	public void setData(String data) {
		this.data = data;
	}

	public static DetailCardFragment newInstance(int position) {
		DetailCardFragment f = new DetailCardFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dcp_detail_card_layout,container,false);
		//////////////≥ı ºªØrootView
		WebView webView = (WebView)view.findViewById(R.id.web_dcp_detail_card);
		//String html = "<html><body><font>html5 test<br>html5 test<br>html5 test<br>html5 test<br></font></body></html>";
		webView.loadData(data, "text/html", "UTF-8");
		return view;
	}
}