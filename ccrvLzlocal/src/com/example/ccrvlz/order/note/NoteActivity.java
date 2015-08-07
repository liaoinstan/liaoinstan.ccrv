package com.example.ccrvlz.order.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.ccrvlz.R;
import com.example.ccrvlz.utils.StringUtil;

public class NoteActivity extends Activity implements OnClickListener{
	private String note;
	private EditText edit_note;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		note = getIntent().getStringExtra("note");
		
		initBase();
	}

	private void initBase() {
		///////////////////≥ı ºªØ
		findViewById(R.id.img_action_back).setOnClickListener(this);
		findViewById(R.id.act_next).setOnClickListener(this);
		edit_note = (EditText)findViewById(R.id.edit_note);
		if (!StringUtil.isEmpty(note)) {
			edit_note.setText(note);
		}
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.img_action_back:
			finish();
			break;
		case R.id.act_next:
			intent.putExtra("note", edit_note.getText().toString());
			setResult(RESULT_OK, intent);
			super.finish();
			break;
		default:
			break;
		}		
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
	}
}
