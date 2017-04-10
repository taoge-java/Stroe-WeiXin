package com.stroe.weixin.config;



import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PropKit;
import com.jfinal.render.ViewType;

public class SysConfig extends JFinalConfig{
	
	
	public static String WEIXIN_APPID;
	
	public static String WEIXIN_APPSECRET;
	
	@Override
	public void configConstant(Constants constant) {
		constant.setDevMode(true);
		constant.setViewType(ViewType.VELOCITY);
		PropKit.use("config.properties");
		WEIXIN_APPID=PropKit.get("weixin.appid");
		WEIXIN_APPSECRET=PropKit.get("weixin.appsecret");
	}

	@Override
	public void configRoute(Routes route) {
		route.add(new AutoBindRoutes());
	}

	@Override
	public void configPlugin(Plugins plugins) {
	}

	@Override
	public void configInterceptor(Interceptors interceptors) {
		
	}

	@Override
	public void configHandler(Handlers handlers) {
		handlers.add(new ContextPathHandler("BASE_PATH"));
	}
}
