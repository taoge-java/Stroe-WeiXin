package com.stroe.weixin.controller;

import java.io.IOException;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;
import com.stroe.weixin.constant.Constant;
import com.stroe.weixin.controller.base.BaseWeiXinController;
import com.stroe.weixin.dao.message.request.Image;
import com.stroe.weixin.dao.message.request.ImageMessage;
import com.stroe.weixin.dao.message.request.TextMessage;
import com.stroe.weixin.interceptor.WeiXinInterceptor;
import com.stroe.weixin.util.HttpClientUtil;
import com.stroe.weixin.util.XMLUtil;

@ControllerBind(controllerKey="/")
public class MsgController extends BaseWeiXinController{

	@SuppressWarnings("unused")
	@Before(WeiXinInterceptor.class)
	public void index() throws IOException{
	   Map<String,String> map=XMLUtil.xmlTomap(getRequest());
	   if(map==null)
		   return;
	   String fromUser=map.get("FromUserName");
	   String toUserName=map.get("ToUserName");
	   String msgType=map.get("MsgType");
	   String Content=map.get("Content");
	   String CreateTime=map.get("CreateTime");
	   if(Constant.MESSAGE_TEXT.equals(msgType)){//回复文本消息
		   sendTextMessge(new TextMessage(),fromUser,toUserName,"感谢您的关注！");
	   } else if(Constant.MESSAGE_IMAGE.equals(msgType)){
		   sendImageMessage(new ImageMessage(), fromUser, toUserName);
	   }
	}

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

	@Override
	public void sendImageMessage(ImageMessage image, String fromUser,
			String toUser) throws IOException {
		image.setFromUserName(toUser);
		image.setToUserName(fromUser);
		image.setMsgId("");
		image.setMsgType(Constant.MESSAGE_IMAGE);
		Image img=new Image();
		img.setMediaId(HttpClientUtil.upload("F:/JavaWeb/Stroe-WeiXin/src/main/webapp/resources/image", HttpClientUtil.getAccesstoken().toString(), "image"));
		image.setImage(img);
		System.out.println(image);
		renderText(XMLUtil.messageToXml(image));
	}

	
	
}
