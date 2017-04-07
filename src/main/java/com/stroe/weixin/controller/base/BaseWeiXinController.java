package com.stroe.weixin.controller.base;

import com.jfinal.core.Controller;

public abstract class BaseWeiXinController extends Controller{

	/**
	 * 发送文本信息
	 */
	public abstract void sendTextMessge();
}
