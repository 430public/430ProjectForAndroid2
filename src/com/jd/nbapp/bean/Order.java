package com.jd.nbapp.bean;

import java.util.Date;
import java.util.List;


public class Order {
	private String order_sn;	//订单号
	private String order_amount;	//订单总额
	private String order_desc; 	//订单备注
	private String address;		//送货地址
	private String zipcode;		//邮编
	private String best_time;	//最佳送货时间
	private String pay_id;		//支付方式ID
	private String pay_name;	//支付方式
	private String shipping_id;		//送货方式ID
	private String shipping_name;	//送货方式
	private String shipping_fee;	//邮费
	private String bonus;		//总积分
	private String add_time;		//下单时间
	private String order_status;	//订单状态
	private String pay_status;		//支付状态
	private Date pay_time;		//支付时间
	private String consignee;		//收件人
	private String tel_mobile;		//电话
	private String email;		//邮箱
	private String from_ad;		//寄件地址
	private String confirm_time;	//确认收货时间
	private List<OrderGoods> order_goods;  //订单商品
	
	
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getShipping_fee() {
		return shipping_fee;
	}
	public void setShipping_fee(String shipping_fee) {
		this.shipping_fee = shipping_fee;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public List<OrderGoods> getOrder_goods() {
		return order_goods;
	}
	public void setOrder_goods(List<OrderGoods> order_goods) {
		this.order_goods = order_goods;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getOrder_desc() {
		return order_desc;
	}
	public void setOrder_desc(String order_desc) {
		this.order_desc = order_desc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getBest_time() {
		return best_time;
	}
	public void setBest_time(String best_time) {
		this.best_time = best_time;
	}
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	public String getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(String shipping_id) {
		this.shipping_id = shipping_id;
	}
	public String getShipping_name() {
		return shipping_name;
	}
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getTel_mobile() {
		return tel_mobile;
	}
	public void setTel_mobile(String tel_mobile) {
		this.tel_mobile = tel_mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFrom_ad() {
		return from_ad;
	}
	public void setFrom_ad(String from_ad) {
		this.from_ad = from_ad;
	}
	public String getConfirm_time() {
		return confirm_time;
	}
	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	
}
