package com.jd.nbapp.bean;

import java.io.Serializable;

/**
 * 商品详情的实体（后面可添加）
 *
 */
public class GoodInfos implements Serializable{

	private String id;
	private String name;
	private String img;
	private String brand;
	private String promote;
	private String cat;
	private String shipping;
	private String sales;
	/**
	 * 商品库存
	 */
	private String gNumber;
	private String click;
	
	/**
	 * 商品备注
	 */
	
	
	
	private String desc;
	
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	public String getgNumber() {
		return gNumber;
	}
	public void setgNumber(String gNumber) {
		this.gNumber = gNumber;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPromote() {
		return promote;
	}
	public void setPromote(String promote) {
		this.promote = promote;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
}
