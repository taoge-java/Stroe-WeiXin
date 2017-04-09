package com.stroe.weixin.dao.base;
/**
 * 微信普通消息父类
 * @author Administrator
 * @version 1.0
 * 2017年4月7日下午22:53
 */
public class BaseMessae {

	
	private String ToUserName;//	开发者微信号
	
	private String FromUserName;//	发送方帐号（一个OpenID）
	
	private String CreateTime;//	消息创建时间 （整型）
	
	private String MsgType;//消息类型
	
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
}
