package com.example.ccrvlz.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoadingViewUtil {
	
	/**
	 * 添加加载视图，在布局中心（仅限根布局为releative）
	 * @param act
	 * @param src
	 * @return
	 */
	public static View showLoadingInCenter(Activity act,int src) {
		ViewGroup decorView = (ViewGroup)act.getWindow().getDecorView();
		View loadingView = LayoutInflater.from(act).inflate(src, null, false);
		decorView.addView(loadingView);
		
		return loadingView;
	}
	
	/**
	 * 删除加载视图
	 * @param act
	 * @param loadingView
	 */
	public static void dismiss(Activity act,View loading) {
		ViewGroup decorView = (ViewGroup)act.getWindow().getDecorView();
		decorView.removeView(loading);
	}
}
