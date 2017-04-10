package com.stroe.weixin.dao.message;
/**
 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
 * @author zengjintao
 * @version 1.0
 */
public class Articles {

	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
