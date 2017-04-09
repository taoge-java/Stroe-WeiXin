package com.stroe.weixin.util;

import net.sf.json.JSONObject;

import com.stroe.weixin.constant.Constant;
import com.stroe.weixin.dao.Menu;
import com.stroe.weixin.dao.base.BaseButton;
import com.stroe.weixin.dao.event.ClickButton;

/**
 * 微信公众号操作工具类
 * @author zengjintao
 * @version 1.0
 * 2017年4月9日上午19:51
 */
public class WeiXinUtil {

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
		
		ClickButton sub_click1=new ClickButton();
		sub_click1.setName("个人中心");
		sub_click1.setType("scancode_push");
		sub_click1.setKey("28");
		
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
	 * 创建菜单
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
}
