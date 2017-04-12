package com.stroe.weixin.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.stroe.weixin.config.SysConfig;

@ControllerBind(controllerKey="/weixin")
public class WeiXinController extends Controller{

	public void index(){
		
	}
	
	public void user(){
		render(SysConfig.BASE_VIEW_PATH+"index.vm");
	}
}
