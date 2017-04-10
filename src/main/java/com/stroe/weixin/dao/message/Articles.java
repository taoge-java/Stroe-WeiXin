package com.stroe.weixin.dao.message;

import java.util.List;

/**
 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
 * @author zengjintao
 * @version 1.0
 */
public class Articles {

	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
