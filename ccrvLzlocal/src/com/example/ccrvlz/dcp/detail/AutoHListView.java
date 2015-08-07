package com.example.ccrvlz.dcp.detail;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AutoHListView extends ListView {
	static final int ANIMATION_DURATION = 1000;
	public AutoHListView(Context context) {
		super(context);
		this.setVerticalScrollBarEnabled(false);
	}
	public AutoHListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setVerticalScrollBarEnabled(false);
	}
    public AutoHListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setVerticalScrollBarEnabled(false);
	}
    
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }
    
    private int getTotalHeight() {
		ListAdapter mAdapter = this.getAdapter();
		if (mAdapter == null) {
			return -1;
		}
		int totalHeight = 0;
		for (int i = 0; i < mAdapter.getCount(); i++) {
			View mView = mAdapter.getView(i, null, this);
			mView.measure(
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			// mView.measure(0, 0);
			totalHeight += mView.getMeasuredHeight();
			Log.w("HEIGHT" + i, String.valueOf(totalHeight));
		}
		ViewGroup.LayoutParams params = this.getLayoutParams();
		params.height = totalHeight
				+ (this.getDividerHeight() * (mAdapter.getCount() - 1));

		return params.height;
	}
	
	public void autoHeight(boolean anim) {
		ViewGroup.LayoutParams params = this.getLayoutParams();
		params.height = getTotalHeight();
		this.setLayoutParams(params);
		this.requestLayout();
		if (anim) {
			goAnim();
		}
	}

	private void goAnim() {
		final int initialHeight = this.getTotalHeight();

		AnimationListener al = new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
				AutoHListView.this.setVisibility(View.VISIBLE);
			}
		};

		Animation anim = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				AutoHListView.this.getLayoutParams().height = (int) (initialHeight * interpolatedTime);
				AutoHListView.this.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		anim.setAnimationListener(al);
		anim.setDuration(initialHeight/2);
		AutoHListView.this.startAnimation(anim);
	}
}