package com.stroe.weixin.test;

import com.stroe.weixin.util.HttpClientUtil;
import com.stroe.weixin.util.WeiXinUtil;

import net.sf.json.JSONObject;
import junit.framework.TestCase;

public class WeiXinTest extends TestCase {

	public void testCreateMenu(){
		String menu=JSONObject.fromObject(WeiXinUtil.generateMenu()).toString();
		int count=WeiXinUtil.createMenu(HttpClientUtil.getAccesstoken().getAccessToken(), menu);
		if(count==0){
			System.out.println("创建成功");
		}else{
			System.out.println("创建失败");
		}
	}

}
