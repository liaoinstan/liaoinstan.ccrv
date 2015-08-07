package com.example.ccrvlz.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ccrvlz.R;
import com.example.ccrvlz.dcp.detail.AutoHListView;
import com.example.ccrvlz.home.BitmapHelp;
import com.example.ccrvlz.order.addser.AddSerActivity;
import com.example.ccrvlz.order.calendar.CalendarActivity;
import com.example.ccrvlz.order.calendar.TimesSquareActivity;
import com.example.ccrvlz.order.clause.ClauseActivity;
import com.example.ccrvlz.order.contact.ContactActivity;
import com.example.ccrvlz.order.coupon.CouponActivity;
import com.example.ccrvlz.order.note.NoteActivity;
import com.example.ccrvlz.order.payway.PayWayActivity;
import com.example.ccrvlz.order.person.PersonActivity;
import com.example.ccrvlz.order.referrer.ReferrerActivity;
import com.example.ccrvlz.order.traveller.TravellerActivity;
import com.example.ccrvlz.utils.StringUtil;
import com.example.ccrvlz.utils.TimeUtil;
import com.example.pojo.OrderAddSer;
import com.example.pojo.OrderAll;
import com.example.pojo.OrderContact;
import com.example.pojo.OrderCoupon;
import com.example.pojo.OrderPak;
import com.example.pojo.OrderTraveller;

public class OrderActivity extends Activity implements OnClickListener,OnItemClickListener{
	private View actNextLay;
	private OrderAll orderAll;
	private Handler mHander;	//异步测试
	private Dialog dialog;
	private AutoHListView list_order_addser;
	private AdapterForOrderAddserList adapter_addser;
	private ListView list_order_contact;
	private AdapterForOrderContactList adapter_contact;
	private AutoHListView list_order_traveller;
	private AdapterForOrderTravellerList adapter_traveller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		orderAll = (OrderAll) getIntent().getSerializableExtra("orderAll");
		orderAll.setOrderPak((OrderPak) getIntent().getSerializableExtra("orderPak"));
		mHander = new Handler();
		initBase();
		loadData();
	}

	private void initBase() {
		///////////////////初始化
		findViewById(R.id.img_action_back).setOnClickListener(this);
		actNextLay = findViewById(R.id.act_next);
		actNextLay.setOnClickListener(this);
		findViewById(R.id.lay_order_calen).setOnClickListener(this);
		findViewById(R.id.lay_order_person).setOnClickListener(this);
		findViewById(R.id.lay_order_addser).setOnClickListener(this);
		findViewById(R.id.lay_order_contact_add).setOnClickListener(this);
		findViewById(R.id.lay_order_traveller_add).setOnClickListener(this);
		findViewById(R.id.lay_order_note_add).setOnClickListener(this);
		findViewById(R.id.lay_order_coupon).setOnClickListener(this);
		findViewById(R.id.lay_order_ccbean).setOnClickListener(this);
		findViewById(R.id.lay_order_referrer).setOnClickListener(this);
		findViewById(R.id.text_order_clause).setOnClickListener(this);
		
		if (orderAll.getOrderPak()!=null) {
			orderAll.setGoodsPrice(orderAll.getOrderPak().getPrice());
			orderAll.setAdultCount(orderAll.getOrderPak().getAdultCount());
			orderAll.setChildCount(orderAll.getOrderPak().getChildCount());
			findViewById(R.id.lay_order_person).setClickable(false);
			((TextView)findViewById(R.id.text_order_person_text)).setText(orderAll.getOrderPak().getName());
			findViewById(R.id.img_order_person_next).setVisibility(View.INVISIBLE);
		}
		
		///////////////////设置商品信息
		((TextView)findViewById(R.id.text_order_goods_name)).setText(orderAll.getGoodsName());
		((TextView)findViewById(R.id.text_order_goods_price)).setText(orderAll.getAdultPrice()+"");
		setGoodsPrice();
		BitmapHelp.getBitmapUtils().display(findViewById(R.id.img_order_goods_pic), orderAll.getGoodsPic());
		
		///////////////////设置出发时间
		setStartDate();
		///////////////////设置出发人数
		setPerson();
		
		///////////////////初始化增值服务列表
		list_order_addser = (AutoHListView)findViewById(R.id.list_order_addser);
		list_order_addser.setOnItemClickListener(this);
		adapter_addser = new AdapterForOrderAddserList(this, null,R.layout.order_addser_list_item,orderAll.getAddSers());
		list_order_addser.setAdapter(adapter_addser);
		
		///////////////////初始化联系人列表
		list_order_contact = (ListView)findViewById(R.id.list_order_contact);
		list_order_contact.setOnItemClickListener(this);
		adapter_contact = new AdapterForOrderContactList(this, null,R.layout.order_ges_list_item, orderAll.getContacts());
		list_order_contact.setAdapter(adapter_contact);
		setContact();
		//////////////////初始化旅客列表
		list_order_traveller = (AutoHListView)findViewById(R.id.list_order_traveller);
		list_order_traveller.setOnItemClickListener(this);
		adapter_traveller = new AdapterForOrderTravellerList(this, null,R.layout.order_ges_list_item, orderAll.getTravellers());
		list_order_traveller.setAdapter(adapter_traveller);
		setTraveller();
		//////////////////初始化留言
		setNote();
		//////////////////初始化优惠
		setCoupon();//优惠券
		setCcBean();//cc豆
		setReferrer();//推荐码
		//////////////////初始化明细列表
		setDetail();
		
	}
	private void setDetail() {
		float basePrice = orderAll.getGoodsPrice();
		((TextView)findViewById(R.id.text_order_detail_baseprice_value)).setText(basePrice+"");
		findViewById(R.id.lay_order_detail_baseprice).setVisibility(basePrice==0?View.GONE:View.VISIBLE);
		
		float adderValue = getAdderValue(orderAll.getAddSers());
		((TextView)findViewById(R.id.text_order_detail_addser_value)).setText(adderValue+"");
		findViewById(R.id.lay_order_detail_addser).setVisibility(adderValue==0?View.GONE:View.VISIBLE);
		
		float couponValue = getCouponValue(orderAll.getCoupons()) * -1;
		((TextView)findViewById(R.id.text_order_detail_coupon_value)).setText(couponValue+"");
		findViewById(R.id.lay_order_detail_coupon).setVisibility(couponValue==0?View.GONE:View.VISIBLE);
		
		float ccBeanValue = (orderAll.isUserbean()?((float)orderAll.getCcbean()/1000):0F)  * -1;
		((TextView)findViewById(R.id.text_order_detail_ccbean_value)).setText(ccBeanValue+"");
		findViewById(R.id.lay_order_detail_ccbean).setVisibility(!orderAll.isUserbean()?View.GONE:View.VISIBLE);
		
		float referrerValue = orderAll.getReferrerPrice() * -1;
		((TextView)findViewById(R.id.text_order_detail_referrer_value)).setText(referrerValue+"");
		findViewById(R.id.lay_order_detail_referrer).setVisibility(referrerValue==0?View.GONE:View.VISIBLE);
		
		float allPrice = basePrice + adderValue + couponValue + ccBeanValue + referrerValue;
		((TextView)findViewById(R.id.text_order_detail_allprice_value)).setText("￥"+(float)Math.round(allPrice*100)/100);
	}
	private void setGoodsPrice() {
		float goodsPrice = orderAll.getOrderPak()==null ? orderAll.getAdultPrice()*orderAll.getAdultCount()+orderAll.getChildPrice()*orderAll.getChildCount() : orderAll.getOrderPak().getPrice();
		orderAll.setGoodsPrice(goodsPrice);
	}
	private void setReferrer() {
		if(StringUtil.isEmpty(orderAll.getReferrerPhone())){
			((TextView)findViewById(R.id.text_order_referrer_select)).setText("未填写");
		}else {
			((TextView)findViewById(R.id.text_order_referrer_select)).setText(orderAll.getReferrerPhone());
		}
	}
	private void setCcBean() {
		if (orderAll.isUserbean()) {
			((ImageView)findViewById(R.id.check_order_ccbean)).setImageResource(R.drawable.icon_selectbox_y);
		}else {
			((ImageView)findViewById(R.id.check_order_ccbean)).setImageResource(R.drawable.icon_selectbox_n);
		}
		if(orderAll.getCcbean()==0){
			((TextView)findViewById(R.id.text_order_ccbean_detail)).setText("无可用cc豆");
		}else {
			((TextView)findViewById(R.id.text_order_ccbean_detail)).setText("可用"+orderAll.getCcbean()+"，抵"+((float)orderAll.getCcbean())/1000);
		}
	}
	private void setCoupon() {
		if (orderAll.getCoupons().size()==0) {
			((TextView)findViewById(R.id.text_order_coupon_select)).setText("未选择");
		}else {
			float priceTemp = 0;
			for (OrderCoupon coupon : orderAll.getCoupons()) {
				priceTemp += coupon.getPrice();
			}
			((TextView)findViewById(R.id.text_order_coupon_select)).setText("-"+priceTemp);
		}
	}
	private void setTraveller() {
		int size = orderAll.getTravellers().size();		//当前旅客人数
		int count = orderAll.getAdultCount()+orderAll.getChildCount();	//旅客总人数
		if(size>=count){
			findViewById(R.id.lay_order_traveller_add).setVisibility(View.GONE);
		}else {
			findViewById(R.id.lay_order_traveller_add).setVisibility(View.VISIBLE);
		}
	}
	private void setNote() {
		if (!StringUtil.isEmpty(orderAll.getNote())) {
			((TextView)findViewById(R.id.text_order_note)).setText(orderAll.getNote());
//			findViewById(R.id.lay_order_note_add).setVisibility(View.GONE);
		}else {
			((TextView)findViewById(R.id.text_order_note)).setText("如有特殊需求，请在此留言");
//			findViewById(R.id.lay_order_note_add).setVisibility(View.VISIBLE);
		}
	}
	private void setContact() {
		if(orderAll.getContacts().size()>0){
			findViewById(R.id.lay_order_contact_add).setVisibility(View.GONE);
		}else {
			findViewById(R.id.lay_order_contact_add).setVisibility(View.VISIBLE);
		}
	}
	private void setStartDate() {
		((TextView)findViewById(R.id.text_order_calen_startdate)).setText(TimeUtil.getTime(orderAll.getStartDate()));
		if (orderAll.getStartDate()==null) {
			((TextView)findViewById(R.id.text_order_calen_select)).setText("未选择");
		}else {
			((TextView)findViewById(R.id.text_order_calen_select)).setText("");
		}
	}
	private void setPerson() {
		if (orderAll.getAdultCount()==0 && orderAll.getChildCount()==0) {
			((TextView)findViewById(R.id.text_order_person_select)).setText("未选择");
		}else {
			((TextView)findViewById(R.id.text_order_person_select)).setText(StringUtil.trimStr("成人"+orderAll.getAdultCount()+"/儿童"+orderAll.getChildCount(), "/"));
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.lay_order_calen:
//			intent.setClass(this, CalendarActivity.class);
			intent.setClass(this, TimesSquareActivity.class);
			intent.putExtra("startDate", orderAll.getStartDate());
			startActivityForResult(intent, 1);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.lay_order_person:
			intent.setClass(this, PersonActivity.class);
			intent.putExtra("orderAll", orderAll);
			startActivityForResult(intent, 2);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.lay_order_addser:
			intent.setClass(this, AddSerActivity.class);
			intent.putExtra("orderAll", orderAll);
			startActivityForResult(intent, 3);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.lay_order_contact_add:
			intent.setClass(this, ContactActivity.class);
			startActivityForResult(intent, 4);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.lay_order_traveller_add:
			intent.setClass(this, TravellerActivity.class);
			intent.putExtra("count", orderAll.getAdultCount()+orderAll.getChildCount()-orderAll.getTravellers().size());
			startActivityForResult(intent, 5);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.lay_order_coupon:
			intent.setClass(this, CouponActivity.class);
			intent.putExtra("coupons", orderAll.getCoupons());
			startActivityForResult(intent, 6);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.lay_order_ccbean:
			orderAll.setUserbean(orderAll.isUserbean()?false:true);
			setCcBean();
			/////////////////更新明细
			setDetail();
			break;
		case R.id.lay_order_referrer:
			intent.setClass(this, ReferrerActivity.class);
			intent.putExtra("referrerPhone", orderAll.getReferrerPhone());
			intent.putExtra("referrerPrice", orderAll.getReferrerPrice());
			startActivityForResult(intent, 7);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.lay_order_note_add:
			intent.setClass(this, NoteActivity.class);
			intent.putExtra("note", orderAll.getNote());
			startActivityForResult(intent, 8);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.text_order_clause:
			intent.setClass(this, ClauseActivity.class);
			startActivity(intent);
			break;
		case R.id.img_action_back:
			finish();
			break;
		case R.id.act_next:
			dialog = new LoadingDialog(this,"正在提交…");
			dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						mHander.removeCallbacks(run);
						dialog.dismiss();
						return true;
					}
					return false;
				}
			});
			submitData();
			break;
		default:
			break;
		}		
	}
	
	private void loadData() {
		mHander.postDelayed(new Runnable() {
			@Override
			public void run() {
				/////////////////异步加载增值服务
				list_order_addser.setVisibility(View.GONE);
				OrderAddSer addser = new OrderAddSer("10000", "【必选】床单被套", 98, "个",1, "http://img14.360buyimg.com/n1/g15/M04/11/00/rBEhWlJmspAIAAAAAAJCBySqmvEAAEdTwHUzt8AAkIf756.jpg", 1, 1, 1);
				orderAll.getAddSers().add(addser);
				adapter_addser.notifyDataSetChanged();
				list_order_addser.autoHeight(true);
				/////////////////异步加载cc豆
				int ccbean = 1056;
				orderAll.setCcbean(ccbean);
				setCcBean();
				/////////////////更新明细
				setDetail();
			}
		}, 500);
	}
	
	private Runnable run = new Runnable() {
		@Override
		public void run() {
			dialog.dismiss();
			doFinish();
			Intent intent = new Intent(OrderActivity.this, PayWayActivity.class);
			intent.putExtra("orderAll", orderAll);
			startActivity(intent);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
		}
	};
	
	//模拟异步提交数据
	private void submitData() {
		dialog.show();
		mHander.postDelayed(run, 2000);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {
		Intent intent = new Intent();
		switch (parent.getId()) {
		case R.id.list_order_addser:
			intent.setClass(this, AddSerActivity.class);
			intent.putExtra("orderAll", orderAll);
			intent.putExtra("position", position);
			startActivityForResult(intent, 3);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		case R.id.list_order_contact:
			intent.setClass(this, ContactActivity.class);
			intent.putExtra("orderContact", orderAll.getContacts().get(position));
			startActivityForResult(intent, 4);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;	
		case R.id.list_order_traveller:
			intent.setClass(this, TravellerActivity.class);
			intent.putExtra("orderTraveller", orderAll.getTravellers().get(position));
			intent.putExtra("position", position);
			startActivityForResult(intent, 5);
			overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left); 
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:		//选择日期
			if (resultCode == RESULT_OK) {
				orderAll.setStartDate((Date)data.getSerializableExtra("startDate"));
				setStartDate();
			}
			break;
		case 2:		//选择人数
			if (resultCode == RESULT_OK) {
				orderAll.setAdultCount(data.getIntExtra("adultCount", 0));
				orderAll.setChildCount(data.getIntExtra("childCount", 0));
				////////////////当减少出行人数后，从旅客列表从后往前移除多余的人
				int size = orderAll.getTravellers().size();		//当前旅客人数
				int count = orderAll.getAdultCount()+orderAll.getChildCount();	//旅客总人数
				if (size>count) {
					for (int i = count; i < size; i++) {
						orderAll.getTravellers().remove(orderAll.getTravellers().size()-1);
					}
				}
				adapter_traveller.notifyDataSetChanged();
				list_order_traveller.autoHeight(false);
				////////////////
				setPerson();
				setTraveller();
				setGoodsPrice();
				/////////////////更新明细
				setDetail();
			}
			break;
		case 3:		//选择增值服务
			if (resultCode == RESULT_OK) {
				int position = data.getIntExtra("position", -1);
				if (position!=-1) {
					orderAll.getAddSers().remove(position);
				}else {
					orderAll.getAddSers().clear();
				}
				List<OrderAddSer> addsers = (List<OrderAddSer>)data.getSerializableExtra("addSers");
				orderAll.getAddSers().addAll(addsers);
				adapter_addser.notifyDataSetChanged();
				list_order_addser.autoHeight(false);
				/////////////////更新明细
				setDetail();
			}
			break;
		case 4:		//选择联系人
			if (resultCode == RESULT_OK) {
				orderAll.getContacts().clear();
				orderAll.getContacts().add((OrderContact)data.getSerializableExtra("orderContact"));
				adapter_contact.notifyDataSetChanged();
				setContact();
			}
			break;
		case 5:		//选择游客
			if (resultCode == RESULT_OK) {
				int position = data.getIntExtra("position", -1);
				ArrayList<OrderTraveller> tempT = (ArrayList<OrderTraveller>)data.getSerializableExtra("orderTravellers");
				if (position!=-1) {
					orderAll.getTravellers().remove(position);
					orderAll.getTravellers().addAll(position, tempT);
				}else{
					orderAll.getTravellers().addAll(tempT);
				}
				adapter_traveller.notifyDataSetChanged();
				list_order_traveller.autoHeight(false);
				setTraveller();
			}
			break;
		case 6:		//选择优惠券
			if (resultCode == RESULT_OK) {
				List<OrderCoupon> coupons = (List<OrderCoupon>)data.getSerializableExtra("coupons");
				orderAll.getCoupons().clear();
				orderAll.getCoupons().addAll(coupons);
				setCoupon();
				/////////////////更新明细
				setDetail();
			}
			break;
		case 7:		//选择推荐号
			if (resultCode == RESULT_OK) {
				String referrerPhone = data.getStringExtra("referrerPhone");
				float referrerPrice = data.getFloatExtra("referrerPrice", 0);
				orderAll.setReferrerPhone(referrerPhone);
				orderAll.setReferrerPrice(referrerPrice);
				setReferrer();
				/////////////////更新明细
				setDetail();
			}
			break;
		case 8:		//填写留言
			if (resultCode == RESULT_OK) {
				String note = data.getStringExtra("note");
				orderAll.setNote(note);
				setNote();
			}
			break;
		default:
			break;
		}
	}
	
	private float getCouponValue(List<OrderCoupon> coupons) {
		float value = 0;
		for (OrderCoupon coupon : coupons) {
			value += coupon.getPrice();
		}
		return value;
	}

	private float getAdderValue(List<OrderAddSer> addsers) {
		float value = 0;
		for (OrderAddSer addser : addsers) {
			value += addser.getPrice()*addser.getCount()*(addser.getPer()==1?1:orderAll.getDayCount());
		}
		return value;
	}
	
	public void doFinish(){
		super.finish();
		overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right); 
	}

	@Override
	public void finish() {
		final MsgDialog dialog = new MsgDialog(this);
		dialog.setOnNegativeListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	dialog.dismiss();
	        	doFinish();
	        }
	    });
		dialog.show();
	}
}
