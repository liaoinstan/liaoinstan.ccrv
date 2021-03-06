package com.example.ccrvlz.order;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ccrvlz.R;
/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class LoadingDialog extends Dialog {
	private String msg;
	
    public LoadingDialog(Context context,String msg) {
        super(context,R.style.LoadingDialog);
        this.msg = msg;
        setLoadingDialog();
    }
 
    private void setLoadingDialog() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());  
        View v = inflater.inflate(R.layout.dialog_loading_layout, null);// 得到加载view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局  
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字  
        tipTextView.setText(msg);// 设置加载信息  
        
        /**
         * <item name="android:windowCloseOnTouchOutside">false</item>
         * 可以在style文件中进行配置，最低app level 11（当前8）
         */
        this.setCanceledOnTouchOutside(false);
        this.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.FILL_PARENT,  
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局  
        
        super.setContentView(v);
    }
    
}
