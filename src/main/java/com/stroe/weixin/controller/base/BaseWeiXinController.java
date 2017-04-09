package com.stroe.weixin.controller.base;

import java.io.IOException;

import com.jfinal.core.Controller;
import com.stroe.weixin.dao.message.request.ImageMessage;
import com.stroe.weixin.dao.message.request.TextMessage;

public abstract class BaseWeiXinController extends Controller{

	/**
	 * 发送文本信息
	 */
	public abstract void sendTextMessge(TextMessage text,String fromUser,String toUser,String content);
	
	/**
	 * 发送图片信息
	 * @throws IOException 
	 */
	public abstract void sendImageMessage(ImageMessage image,String fromUser,String toUser) throws IOException;
}
