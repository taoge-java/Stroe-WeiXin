package com.stroe.weixin.dao.message;

import com.stroe.weixin.dao.base.BaseMessae;

/**
 * 图片消息
 * 
 * @author zengjintao
 * @version 1.0 
 * 2017年4月8日9:21
 */
public class ImageMessage extends BaseMessae {

	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	

}
