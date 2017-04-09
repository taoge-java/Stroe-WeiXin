package com.stroe.weixin.test;


import com.stroe.weixin.dao.AccessToken;
import com.stroe.weixin.util.HttpClientUtil;

public class Test {
	
	public static void main(String[] args) {
			try {
				AccessToken token = HttpClientUtil.getAccesstoken();
				String path = "F:/JavaWeb/Stroe-WeiXin/src/main/webapp/resources/image/1.jpg";
				String mediaId = HttpClientUtil.upload(path, token.getAccessToken(), "image");
				System.out.println(mediaId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
