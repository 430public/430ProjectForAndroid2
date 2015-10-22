package com.jd.nbapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jd.nbapp.widgets.PopupMenuWindow;
import com.jd.nbapp.widgets.PopupMenuWindow.OnInnerButtonClickListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.R.anim;
import android.R.integer;
import android.R.raw;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.jd.nbapp.R;
import com.jd.nbapp.bean.CartInfo;
import com.jd.nbapp.bean.GoodInfos;
import com.jd.nbapp.config.Constants;
import com.jd.nbapp.dao.MyLoveDao;
import com.jd.nbapp.impl.MyLoveDaoImpl;
import com.jd.nbapp.ui.base.BaseActivity;
import com.jd.nbapp.widgets.PopupMenuWindow;
import com.jd.nbapp.widgets.PopupMenuWindow.OnInnerButtonClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetailActivity extends BaseActivity implements OnClickListener {

	private String id;
	private String URL;
	private TextView tv_name;
	private ImageView iv_goods;
	private TextView tv_price;
	private TextView tv_gNumber;
	private TextView tv_shipping;
	private TextView tv_click;
	private String goods_name;
	private Double goods_price;
	private String imageUrl;
	private ImageView imageLove;
	private MyLoveDao myLoveDao;
	//商品详情
	private TextView detail_1;
	private TextView detail_2;
	private TextView detail_3;
	private TextView detail_4;
	// 判断点击了购物车或立即购买
	private int isBuy;
	// 判断是否收藏
	private static boolean  isLove ;
	//装载购买商品的List
	List<CartInfo> cartInfoList = new ArrayList<CartInfo>();
	private Button ic_add_cart;
	

	ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_main);
		new FirstAdAsyncTask().execute("http://192.168.4.104:8080/xd/address.do?username="+Constants.username);
		id = this.getIntent().getStringExtra("id");
		URL = "http://192.168.4.104:8080/xd/goods.do?" + "id=" + id;
		initEvent();

		
		 if (isLove) {
				imageLove.setImageDrawable(getResources().getDrawable(R.drawable.shortcuts_icon_collect2));
			}else {
				imageLove.setImageDrawable(getResources().getDrawable(R.drawable.shortcuts_icon_collect));
			}
		new MyAsyncTask().execute(URL);
	}

	/** 初始化点击事件 */
	private void initEvent() {
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_price = (TextView) findViewById(R.id.tv_price);
		tv_gNumber = (TextView) findViewById(R.id.tv_gNumber);
		tv_shipping = (TextView) findViewById(R.id.tv_shipping);
		tv_click = (TextView) findViewById(R.id.tv_click);
		iv_goods = (ImageView) findViewById(R.id.indicate_view);
		imageLove=(ImageView)findViewById(R.id.isLove);
		
		detail_1=(TextView)findViewById(R.id.tv_detail_1);
		detail_2=(TextView)findViewById(R.id.tv_detail_2);
		detail_3=(TextView)findViewById(R.id.tv_detail_3);
		detail_4=(TextView)findViewById(R.id.tv_detail_4);

		myLoveDao = new MyLoveDaoImpl(getApplicationContext());
        isLove=myLoveDao.isMyLove(Constants.username, Integer.valueOf(id));
       

        findViewById(R.id.ic_add_cart).setOnClickListener(this);
		findViewById(R.id.btn_share).setOnClickListener(this);;
		findViewById(R.id.imageButton1).setOnClickListener(this);
		findViewById(R.id.imageButton2).setOnClickListener(this);
		findViewById(R.id.goods_size).setOnClickListener(this);
		findViewById(R.id.isLove).setOnClickListener(this);
		
		

	}

	// ------------------点击事件-------------
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ic_add_cart:
			//跳转到购物车
			Intent intent=new Intent(DetailActivity.this, CartActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_share:
			//分享到新浪微博
			OnekeyShare onekeyShare = new OnekeyShare();
			//设置标题
			onekeyShare.setTitleUrl("宝贝分享");
			//设置信息
			onekeyShare.setText("[新豆商品]"+goods_name);
			//设置图标
			onekeyShare.setImageUrl(imageUrl);
			//显示分享列表
			onekeyShare.show(DetailActivity.this);
			break;
		case R.id.imageButton1:
			// 立即购买
			isBuy = 1;
			showPopu();
			break;
		case R.id.imageButton2:
			// 加入购物车
			isBuy = 0;
			showPopu();
			break;
		case R.id.goods_size:
			showPopu();
			break;
		case R.id.isLove:
			if (!isLove) {
//				if(Constants.username.equals("")){
//					Toast.makeText(DetailActivity.this, "请登录后再操作", 500).show();
//					return;}
				myLoveDao.pushToMyLove(Constants.username, Integer.parseInt(id));
				Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_LONG).show();
				imageLove.setImageDrawable(getResources().getDrawable(R.drawable.shortcuts_icon_collect2));
				isLove=true;
			} else {
//				if(Constants.username.equals("")){
//					Toast.makeText(DetailActivity.this, "请登录后再操作", 500).show();
//					return;}
				myLoveDao.pullFromMyLove(Constants.username, Integer.parseInt(id));
				Toast.makeText(getApplicationContext(), "不收藏了", Toast.LENGTH_LONG).show();
				imageLove.setImageDrawable(getResources().getDrawable(R.drawable.shortcuts_icon_collect));
				isLove=false;
			}
		default:
			break;
		}
	}

	String shopCount;

	EditText editText;
	PopupMenuWindow menu;

	private void showPopu() {
		menu = new PopupMenuWindow(DetailActivity.this);
		menu.showPopupWindow(android.R.id.content);
		View view = getLayoutInflater().inflate(R.layout.popup_menu, null);

		menu.setOnInnerButtonClickListener(new OnInnerButtonClickListener() {
			@Override
			public void OnClick(View v) {
				CartInfo cartInfo = new CartInfo();
				cartInfo.setGoods_id(Integer.parseInt(id));
				cartInfo.setUsername(Constants.username);
				cartInfo.setGoods_name(goods_name);
				if(shopCount==null){
					shopCount="1";
					cartInfo.setGoods_number(Integer.parseInt(shopCount));
				}else{
					cartInfo.setGoods_number(Integer.parseInt(shopCount));
				}
				cartInfo.setGoods_price(goods_price);
				cartInfo.setImageUrl(imageUrl);
				if (isBuy == 0) {
					if(Constants.username.equals("")){
						Toast.makeText(DetailActivity.this, "请登录后再操作", 500).show();
						return;}
					 addToCart(cartInfo);
				} else {
					if(Constants.username.equals("")){
						Toast.makeText(DetailActivity.this, "请登录后再操作", 500).show();
						return;}
					cartInfoList.clear();
					cartInfoList.add(cartInfo);
					Bundle bundle = new Bundle();
					bundle.putSerializable("carInfo", (Serializable) cartInfoList);
					Intent i = new Intent(DetailActivity.this, OrderActivity.class);
					i.putExtras(bundle);
					startActivity(i);

				}
				Toast.makeText(DetailActivity.this, "商品数量： " + shopCount, Toast.LENGTH_SHORT).show();
				menu.dismiss();
			}
		});
	}

	public static void addToCart(CartInfo cartInfo) {
		String goodsName = null;
		try {
			goodsName=URLEncoder.encode(cartInfo.getGoods_name(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "http://192.168.4.104:8080/xd/front/shopCart/add.do?"
				 + "id=" + cartInfo.getGoods_id()
				 + "&name=" + goodsName
				 +"&goodsImg="+cartInfo.getImageUrl()
				+ "&price=" + cartInfo.getGoods_price()
				 + "&username=" + cartInfo.getUsername() ;
		
//		url = url.replaceAll("&", "%26");
//	    url = url.replaceAll(" ", "%20");
		

		AsyncHttpClient client = new AsyncHttpClient();
		client.post(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				Log.i("2222222222222222222222222222222222222222222222", content);
			}

			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
			}
		});
	}


	public void onEtChange(EditText et) {
		shopCount = "" + et.getText();
		Log.e("", "======" + shopCount);
	}

	class MyAsyncTask extends AsyncTask<String, Void, List<GoodInfos>> {

		@Override
		protected List<GoodInfos> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return getJson(arg0[0]);
		}

		@Override
		protected void onPostExecute(List<GoodInfos> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			goods_name = result.get(0).getName();
			tv_name.setText(goods_name);
			goods_price = Double.valueOf(result.get(0).getPromote()).doubleValue();
			tv_price.setText(result.get(0).getPromote());
			tv_gNumber.setText("库存 " + result.get(0).getgNumber());
			if (result.get(0).getShipping().equals("1")) {
				tv_shipping.setText("包邮");
			} else {
				tv_shipping.setText("不包邮");
			}
			tv_click.setText("点击量 " + result.get(0).getClick());
			imageUrl = result.get(0).getImg();
			imageLoader.displayImage(imageUrl, iv_goods);
			
			detail_1.setText("商品：  "+goods_name);
			detail_2.setText("点击量：  "+result.get(0).getClick());
			detail_3.setText("销量：    "+result.get(0).getSales());
			detail_4.setText("库存：    "+result.get(0).getgNumber());
			
		}
	}

	private List<GoodInfos> getJson(String string) {
		List<GoodInfos> list = new ArrayList<GoodInfos>();
		try {
			String jsonString = readStream(new java.net.URL(string).openStream());
			JSONArray jsonArray = new JSONArray(jsonString);
			GoodInfos goodInfos;
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				goodInfos = new GoodInfos();
				goodInfos.setBrand(jsonObject.getString("brand"));
				goodInfos.setCat(jsonObject.getString("catId"));
				goodInfos.setId(jsonObject.getString("id"));
				goodInfos.setImg(jsonObject.getString("img"));
				goodInfos.setName(jsonObject.getString("name"));
				goodInfos.setPromote(jsonObject.getString("promote"));
				goodInfos.setClick(jsonObject.getString("click"));
				goodInfos.setDesc(jsonObject.getString("desc"));
				goodInfos.setgNumber(jsonObject.getString("gNumber"));
				goodInfos.setShipping(jsonObject.getString("shipping"));
				goodInfos.setSales(jsonObject.getString("sales"));
				list.add(goodInfos);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	private String readStream(InputStream is) {
		String result = "";
		String line = "";
		InputStreamReader isr;
		BufferedReader br = null;

		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		try {
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	@Override
	protected void findViewById() {

	}

	@Override
	protected void initView() {

	}
}
