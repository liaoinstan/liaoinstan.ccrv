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
 * @Function: �Զ���Ի���
 * @Date: 2013-10-28
 * @Time: ����12:37:43
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
        View v = inflater.inflate(R.layout.dialog_loading_layout, null);// �õ�����view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// ���ز���  
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// ��ʾ����  
        tipTextView.setText(msg);// ���ü�����Ϣ  
        
        /**
         * <item name="android:windowCloseOnTouchOutside">false</item>
         * ������style�ļ��н������ã����app level 11����ǰ8��
         */
        this.setCanceledOnTouchOutside(false);
        this.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.FILL_PARENT,  
                LinearLayout.LayoutParams.FILL_PARENT));// ���ò���  
        
        super.setContentView(v);
    }
    
}
