package com.stroe.weixin.controller;

import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;
import com.stroe.weixin.controller.base.BaseWeiXinController;
import com.stroe.weixin.interceptor.WeiXinInterceptor;

@ControllerBind(controllerKey="/")
public class MsgController extends BaseWeiXinController{

	@Before(WeiXinInterceptor.class)
	public void index(){
		
	}

	@Override
	public void sendTextMessge() {
		
		
	}
}
