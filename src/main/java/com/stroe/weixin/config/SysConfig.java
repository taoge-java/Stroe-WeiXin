package com.stroe.weixin.config;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.stroe.weixin.model.BaseModel;

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

	@SuppressWarnings("unchecked")
	@Override
	public void configPlugin(Plugins plugins) {
		plugins.add(new ServerInitPlug());
		DruidPlugin druid=new DruidPlugin(PropKit.get("mysql.jdbcUrl"), PropKit.get("mysql.jdbc.user"), PropKit.get("mysql.jdbc.password"));
		plugins.add(druid);
		AutoTableBindPlugin table=new AutoTableBindPlugin(druid);
		table.autoScan(true);
		table.setShowSql(true);
		table.addExcludeClasses(BaseModel.class);
		plugins.add(table);
	}

	@Override
	public void configInterceptor(Interceptors interceptors) {
		
	}

	@Override
	public void configHandler(Handlers handlers) {
		handlers.add(new ContextPathHandler("BASE_PATH"));
	}
}
