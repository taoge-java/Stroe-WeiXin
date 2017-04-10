package com.stroe.weixin.lisenter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONObject;

import com.jfinal.log.Logger;
import com.stroe.weixin.controller.MsgController;
import com.stroe.weixin.util.HttpClientUtil;
import com.stroe.weixin.util.WeiXinUtil;
/**
 * 监听器，初始化系统菜单
 * @author zengjintao
 * 
 */
public class WebContextListener implements ServletContextListener{
	
	private static Logger LOG=Logger.getLogger(MsgController.class);
	
	public void contextInitialized(ServletContextEvent sce) {
		
        new Thread(new Runnable() {
			public void run() {
			   try{
				   String menu=JSONObject.fromObject(WeiXinUtil.generateMenu()).toString();
				   WeiXinUtil.createMenu(HttpClientUtil.getAccesstoken().getAccessToken(),menu);
				   LOG.info("菜单创建成功");
			   }catch(Exception e){
				   e.printStackTrace();
				   LOG.error("菜单创建异常"); 
			   }	
			}
		});
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
