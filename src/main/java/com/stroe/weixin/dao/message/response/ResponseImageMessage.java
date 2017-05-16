package com.stroe.weixin.dao.message.response;

import com.stroe.weixin.dao.base.BaseMessae;
import com.stroe.weixin.dao.message.Image;

/**
 * 回复图片消息
 * @author zengjintao
 * @version 1.0 
 * 2017年4月8日9:21
 */
public class ResponseImageMessage extends BaseMessae {

	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		this.Image = image;
	}
}
