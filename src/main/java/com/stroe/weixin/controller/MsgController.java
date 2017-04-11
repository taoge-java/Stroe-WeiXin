package com.stroe.weixin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.stroe.weixin.constant.Constant;
import com.stroe.weixin.controller.base.BaseWeiXinController;
import com.stroe.weixin.dao.message.Articles;
import com.stroe.weixin.dao.message.Image;
import com.stroe.weixin.dao.message.TextMessage;
import com.stroe.weixin.dao.message.response.ResponseNewsMessage;
import com.stroe.weixin.dao.message.response.ResponseImageMessage;
import com.stroe.weixin.interceptor.WeiXinInterceptor;
import com.stroe.weixin.util.HttpClientUtil;
import com.stroe.weixin.util.XMLUtil;

@ControllerBind(controllerKey="/")
public class MsgController extends BaseWeiXinController{
	private static Logger LOG=Logger.getLogger(MsgController.class);
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
		   sendGraphicMessage(new ResponseNewsMessage(),fromUser,toUserName);
		     // sendTextMessge(new TextMessage(),fromUser,toUserName,"亲,感谢您的关注！赶快开启您的购物之旅吧");
	   }else if(Constant.MESSAGE_IMAGE.equals(msgType)){//回复图片
		      sendImageMessage(new ResponseImageMessage(), fromUser, toUserName);
	   }else if(Constant.MESSAGE_EVENT.equals(msgType)){//微信事件推送
		  String eventType= map.get("Event");//获取事件推送类型
		  if(eventType.equals(Constant.MESSAGE_SUBSCRIBE)){//如果是关注事件
			  sendTextMessge(new TextMessage(),fromUser,toUserName,"尊敬的用户您好,欢迎关注天下淘网络商城!更多便宜好货竟在天下淘商城!/n发送  “人工服务” 由美女客服为你服务!");
		  }else if(eventType.equals(Constant.MESSAGE_UNSUBSCRIBE)){
			  LOG.info("用户取消了关注");
		  }else if(eventType.equals(Constant.MESSAGE_CLICK)){
			  String key=map.get("EventKey");
			  if(key.equals("15")){
				  
			  }
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
		String result=HttpClientUtil.upload("C:/Users/Administrator/git/Stroe-WeiXin1/src/main/webapp/resources/image/1.jpg",HttpClientUtil.getAccesstoken().getAccessToken(),"image");
		String MediaId=JSONObject.fromObject(result).getString("media_id");
		img.setMediaId(MediaId);
		image.setImage(img);
		System.err.println(XMLUtil.messageToXml(image));
		renderText(XMLUtil.messageToXml(image));
	}
	/**
	 * 回复图文消息
	 */
	@Override
	public void sendGraphicMessage(ResponseNewsMessage graphic, String fromUser,
			String toUser) {
		List<Articles> list=new ArrayList<Articles>();
		Articles articles=new Articles();
		articles.setDescription("1、绑定帐户，进入公众号，点击【我的帐户】菜单，进入绑定页面。\n2、开单通知，绑定物流公司帐户后，在物流公司开单，微信会接收开单通知信息。\n3、我的运单，点击【我的运单】菜单进入查看自己在物流公司的托运单，以及跟踪托运单的状态。");
		articles.setPicUrl("http://www.tianxiatao.com.server.ngrok.cc/Stroe-WeiXin/resources/image/1.jpg");
		articles.setTitle("家具物流管家使用帮助");
		articles.setUrl("http://mp.weixin.qq.com/s/VTPv9zF3AlS4DnlXYo4QeA");
		list.add(articles);
		Articles articles1=new Articles();
		articles1.setDescription("1、绑定帐户，进入公众号，点击【我的帐户】菜单，进入绑定页面。\n2、开单通知，绑定物流公司帐户后，在物流公司开单，微信会接收开单通知信息。\n3、我的运单，点击【我的运单】菜单进入查看自己在物流公司的托运单，以及跟踪托运单的状态。");
		articles1.setPicUrl("http://www.tianxiatao.com.server.ngrok.cc/Stroe-WeiXin/resources/image/1.jpg");
		articles1.setTitle("家具物流管家使用帮助");
		articles1.setUrl("http://mp.weixin.qq.com/s/VTPv9zF3AlS4DnlXYo4QeA");
		list.add(articles);
		list.add(articles1);
		graphic.setCreateTime(System.currentTimeMillis()+"");
		graphic.setFromUserName(toUser);
		graphic.setToUserName(fromUser);
		graphic.setMsgType(Constant.MESAGE_NEWS);
		graphic.setArticleCount(list.size());
		graphic.setArticles(list);
		System.err.println(XMLUtil.messageToXml(graphic));
		renderText(XMLUtil.messageToXml(graphic));
	}
}
