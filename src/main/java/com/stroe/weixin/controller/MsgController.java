package com.stroe.weixin.controller;

import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;
import com.stroe.weixin.constant.Constant;
import com.stroe.weixin.controller.base.BaseWeiXinController;
import com.stroe.weixin.dao.message.Image;
import com.stroe.weixin.dao.message.TextMessage;
import com.stroe.weixin.dao.message.response.ResponseImageMessage;
import com.stroe.weixin.interceptor.WeiXinInterceptor;
import com.stroe.weixin.util.HttpClientUtil;
import com.stroe.weixin.util.XMLUtil;

@ControllerBind(controllerKey="/")
public class MsgController extends BaseWeiXinController{

	@SuppressWarnings("unused")
	@Before(WeiXinInterceptor.class)
	public void index() throws IOException{
	   Map<String,String> map=XMLUtil.xmlTomap(getRequest());
	   String fromUser=map.get("FromUserName");
	   String toUserName=map.get("ToUserName");
	   String msgType=map.get("MsgType");
	   String Content=map.get("Content");
	   String CreateTime=map.get("CreateTime");
	   if(Constant.MESSAGE_TEXT.equals(msgType)){//回复文本消息
		   sendTextMessge(new TextMessage(),fromUser,toUserName,"感谢您的关注！");
	   } else if(Constant.MESSAGE_IMAGE.equals(msgType)){//回复图片
		   sendImageMessage(new ResponseImageMessage(), fromUser, toUserName);
	   }else if(Constant.MESSAGE_EVENT.equals(msgType)){//微信事件推送
		  String eventType= map.get("Event");//获取事件推送类型
		  if(eventType.equals(Constant.MESSAGE_SUBSCRIBE)){//如果是关注事件
			   
		  }
	   }
	}
    /**
     * 回复文本信息
     */
	@Override
	public void sendTextMessge(TextMessage text, String fromUser, String toUser,String content) {
		text.setFromUserName(toUser);
		text.setToUserName(fromUser);
		text.setMsgType(Constant.MESSAGE_TEXT);
		text.setContent(content);
		text.setCreateTime(System.currentTimeMillis()+"");
		System.err.println(XMLUtil.messageToXml(text));
		renderText(XMLUtil.messageToXml(text));
	}

	/**
	 * 回复图片消息
	 */
	@Override
	public void sendImageMessage(ResponseImageMessage image, String fromUser,
			String toUser) throws IOException {
		image.setFromUserName(toUser);
		image.setToUserName(fromUser);
		image.setCreateTime(System.currentTimeMillis()+"");
		image.setMsgType(Constant.MESSAGE_IMAGE);
		Image img=new Image();
		String result=HttpClientUtil.upload("F:/JavaWeb/Stroe-WeiXin/src/main/webapp/resources/image/1.jpg",HttpClientUtil.getAccesstoken().getAccessToken(),"image");
		String MediaId=JSONObject.fromObject(result).getString("media_id");
		img.setMediaId(MediaId);
		image.setImage(img);
		System.err.println(XMLUtil.messageToXml(image));
		renderText(XMLUtil.messageToXml(image));
	}
	/**
	 * 获取菜单
	 */
	public void  menu(){
		
	}
}
