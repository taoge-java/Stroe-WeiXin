package com.stroe.weixin.dao.message.request;

import com.stroe.weixin.dao.base.BaseMessae;

/**
 * 地理位置消息
 * 
 * @author zengjintao
 * @version 1.0 2017年4月8日9:27 <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
 *          <FromUserName><![CDATA[fromUser]]></FromUserName>
 *          <CreateTime>1351776360</CreateTime>
 *          <MsgType><![CDATA[location]]></MsgType>
 *          <Location_X>23.134521</Location_X>
 *          <Location_Y>113.358803</Location_Y> <Scale>20</Scale>
 *          <Label><![CDATA[位置信息]]></Label> <MsgId>1234567890123456</MsgId>
 *          </xml>
 */
public class RequestLocationMessage extends BaseMessae {

	private String Location_X;// 地理位置维度

	private String Location_Y;// 地理位置经度

	private String Label;// 地理位置信息

	private String Scale;// 地图缩放大小

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

}
