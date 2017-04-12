package com.stroe.weixin.config;

import net.sf.json.JSONObject;

import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import com.stroe.weixin.util.HttpClientUtil;
import com.stroe.weixin.util.WeiXinUtil;

public class ServerInitPlug implements IPlugin,Runnable{
	private static Logger LOG=Logger.getLogger(ServerInitPlug.class);
	
	public boolean start() {
		Thread  thread=new Thread(new ServerInitPlug());
		thread.start();
		return true;
	}

	public boolean stop() {
		return false;
	}

	public void run() {
	     try{
		    String menu=JSONObject.fromObject(WeiXinUtil.generateMenu()).toString();
		    int result=WeiXinUtil.createMenu(HttpClientUtil.getAccesstoken().getAccessToken(),menu);
		    if(result==0){
			   LOG.info("菜单创建成功");
		    }else{
			   LOG.error("菜单创建异常"); 
		    }
	     }catch(Exception e){
		     e.printStackTrace();
	         LOG.error("菜单创建异常"); 
	     }	
	}

}
