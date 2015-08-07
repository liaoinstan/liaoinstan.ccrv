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
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.utils.StringUtil;
/**
 * @Function: �Զ���Ի���
 * @Date: 2013-10-28
 * @Time: ����12:37:43
 * @author Tom.Cai
 */
public class MsgDialog extends Dialog {
	private EditText editText;
    private TextView positiveButton, negativeButton;
    private TextView title;
    private String msg;
 
    public MsgDialog(Context context){
    	super(context,R.style.MsgDialog);
    	setMsgDialog();
    }
    
    public MsgDialog(Context context,String msg) {
        super(context,R.style.MsgDialog);
        this.msg = msg;
        setMsgDialog();
    }
 
    private void setMsgDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_msg_layout, null);
        title = (TextView) mView.findViewById(R.id.title);
        title.setText(StringUtil.isEmpty(msg)?"��һ�벻��������һ�����漣��":msg);
        positiveButton = (TextView) mView.findViewById(R.id.positiveButton);
        negativeButton = (TextView) mView.findViewById(R.id.negativeButton);
        positiveButton.setOnClickListener(listener);
        negativeButton.setOnClickListener(listener);
        super.setContentView(mView);
    }
    
    @Override
    public void show() {
    	super.show();
    	Window dialogWindow = this.getWindow();  
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); 
        /////////��ȡ��Ļ���
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);;
		wm.getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		/////////���ø߿�
        lp.width = (int) (screenWidth * 0.75); // ���  
        lp.height = (int) (lp.width*0.65); // �߶�  
        dialogWindow.setAttributes(lp);  
    }
     
    public View getEditText(){
        return editText;
    }
     
     @Override
    public void setContentView(int layoutResID) {
    }
 
    @Override
    public void setContentView(View view, LayoutParams params) {
    }
 
    @Override
    public void setContentView(View view) {
    }
 
    /**
     * ȷ����������
     * @param listener
     */ 
    public void setOnPositiveListener(View.OnClickListener listener){ 
        positiveButton.setOnClickListener(listener); 
    } 
    /**
     * ȡ����������
     * @param listener
     */ 
    public void setOnNegativeListener(View.OnClickListener listener){ 
        negativeButton.setOnClickListener(listener); 
    }
    
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	MsgDialog.this.dismiss();
        }
    };
}
