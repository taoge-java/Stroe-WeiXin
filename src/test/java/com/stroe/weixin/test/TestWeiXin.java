package com.stroe.weixin.test;


import java.util.Arrays;
import java.util.stream.Stream;

import com.stroe.weixin.controller.MsgController;
import com.stroe.weixin.dao.AccessToken;
import com.stroe.weixin.dao.message.response.ResponseNewsMessage;
import com.stroe.weixin.util.HttpClientUtil;

import org.junit.Test;
public class TestWeiXin {
	
	 /**
	  * 微信图片上传接口
	  */
	   @Test
	   public  void testImage() {
			try {
				AccessToken token = HttpClientUtil.getAccesstoken();
				String path = "C:/Users/Administrator/git/Stroe-WeiXin1/src/main/webapp/resources/image/1.jpg";
				String mediaId = HttpClientUtil.upload(path, token.getAccessToken(), "image");
				System.out.println(mediaId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   @Test
	   public void test(){
		    StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(String.format("\"touser\":\"%s\"","java")).append(",");
			buffer.append(String.format("\"template_id\":\"%s\"", "php")).append(",");
			buffer.append(String.format("\"url\":\"%s\"", "http://www.baidu.com")).append(",");
			buffer.append(String.format("\"topcolor\":\"%s\"","#000f")).append(",");
			buffer.append("\"data\":{}");
			System.err.println(buffer.toString());
			MsgController msg=new MsgController();
			msg.sendGraphicMessage(new ResponseNewsMessage(),"dsdds","dssdds");
	   }
	   /**
	    * java8 新特性
	    */
	   @Test(timeout=2000)
	   public void testForEach(){
		   String str[]={"aaa","bbb","ccc","aaa"};
		   Arrays.asList(str).stream().forEach(s->{
			   System.out.println(s);
		   });;
		  Stream<String> stream= Arrays.asList(str).stream().filter(s->s!="aaa");
		  System.out.println(stream.toArray().length);
		  int value = Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum - item);
		  System.out.println(value);
	   }
}
