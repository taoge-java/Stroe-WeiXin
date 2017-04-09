package com.stroe.weixin.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.stroe.weixin.controller.base.BaseWeiXinController;
import com.stroe.weixin.util.EncryptUtil;
/**
 * @author zenbgjintao
 * @version 1.0
 * 2017年4月7日上午8:30
 */
public class WeiXinInterceptor implements Interceptor{

	public void intercept(Invocation inv) {
		Controller controller=inv.getController();
		if(!(controller instanceof BaseWeiXinController))
			throw new RuntimeException("控制器需要继承BaseWeiXinController");
		if(checkSignature(controller)){
			inv.invoke();
		}else{
			controller.renderText("签名校验失败,请检查参数...");
		}
	}
    /**
     * 签名校验
     * @param controller
     * @return
     */
	private boolean checkSignature(Controller controller){
		String signature=controller.getPara("signature");
		String timestamp=controller.getPara("timestamp");
		String nonce=controller.getPara("nonce");
		String echostr=controller.getPara("echostr");
	    String sha=EncryptUtil.checkSignature(timestamp, nonce);
	    if(sha.equals(signature)){
	    	controller.renderText(echostr);
	    	return true;
	    }else{
	    	controller.renderText("签名校验失败");
	    }
		return false;
	}
}
