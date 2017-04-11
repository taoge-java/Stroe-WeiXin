package com.stroe.weixin.dao.kefu;

import com.stroe.weixin.dao.base.BaseServiceMessage;

/**
 * 客服文本消息
 * @author zengjintao
 * @version 1.0
 * 
 */
public class TextService extends BaseServiceMessage{

	private String media_id;//发送的语音的媒体ID
	
	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	@Override
	public String execute(BaseServiceMessage service) {
		return null;
	}

}
