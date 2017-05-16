package com.stroe.weixin.util;

import net.sf.json.JSONObject;

import com.jfinal.log.Logger;
import com.stroe.weixin.constant.Constant;
import com.stroe.weixin.dao.Menu;
import com.stroe.weixin.dao.base.BaseButton;
import com.stroe.weixin.dao.event.ClickButton;
import com.stroe.weixin.dao.event.ViewButton;
import com.stroe.weixin.dao.template.TemplateMsg;

/**
 * 微信公众号操作工具类
 * @author zengjintao
 * @version 1.0
 * 2017年4月9日上午19:51
 */
public class WeiXinUtil {
	
	private static final Logger LOG=Logger.getLogger(WeiXinUtil.class);
	
	/**
	 * 生成微信公众号菜单
	 */
	public static Menu generateMenu(){
		ClickButton click1=new ClickButton();
		click1.setName("新品上市");
		click1.setType("click");
		click1.setKey("15");
		
		ClickButton click2=new ClickButton();
		click2.setName("天下淘商城");
		click2.setType("click");
		click2.setKey("2");
		
		ViewButton sub_click1=new ViewButton();
		sub_click1.setName("个人中心");
		sub_click1.setType("view");
		sub_click1.setUrl("http://www.tianxiatao.com.ngrok.cc.ngrok.cc/Stroe-WeiXin/auth");
		
		ClickButton sub_click2=new ClickButton();
		sub_click2.setName("已支付订单");
		sub_click2.setType("location_select");
		sub_click2.setKey("32");
		
		
		BaseButton base=new BaseButton();
		base.setName("我的账户");
		base.setSub_button(new BaseButton[]{sub_click1,sub_click2});
		Menu menu=new Menu();
		menu.setButton(new BaseButton[]{click1,click2,base});
		return menu;
	}
	/**
	 * 创建菜单     调用微信创建菜单接口
	 * @param access_token
	 * @param menu
	 * @return
	 */
	public static  int createMenu(String  access_token,String menu){
		int count=0;
		String url=Constant.CREATE_MENU.replace("ACCESS_TOKEN", access_token);
	    String result=HttpClientUtil.httpPost(url, menu);
	    JSONObject json=JSONObject.fromObject(result);
	    if(json!=null){
	    	count=json.getInt("errcode");
	    }
	    return count;
	}
	
	/**
	 * 发送模板消息
	 */
	public static void sendMessage(TemplateMsg msg){
		JSONObject json=JSONObject.fromObject(msg);
		String request_url=Constant.TEMPLATE_MESSAGE_URL.replace("ACCESS_TOKEN", HttpClientUtil.getAccesstoken().getAccessToken());
	    String result= HttpClientUtil.httpPost(request_url, json.toString());
	    if(result!=null){
	    	JSONObject jsonObject=JSONObject.fromObject(result);
	    	if(jsonObject.getInt("errcode")==0){
	    		LOG.info("消息发送成功。。。。");
	    	}else{
	    		LOG.error("消息发送异常。。。。");
	    	}
	    }
	}
}
