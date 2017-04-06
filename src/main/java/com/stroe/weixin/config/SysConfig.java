package com.stroe.weixin.config;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.render.ViewType;

public class SysConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants constant) {
		constant.setDevMode(true);
		constant.setViewType(ViewType.VELOCITY);
	}

	@Override
	public void configRoute(Routes route) {
		
	}

	@Override
	public void configPlugin(Plugins plugins) {
		
	}

	@Override
	public void configInterceptor(Interceptors interceptors) {
		
	}

	@Override
	public void configHandler(Handlers handlers) {
		
	}

}
