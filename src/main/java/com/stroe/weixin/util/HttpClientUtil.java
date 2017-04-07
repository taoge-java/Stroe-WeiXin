package com.stroe.weixin.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static String httpGet(String url){
		return httpGet(url,"UTF-8");
	}

	public static String httpGet(String url, String code) {
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
	    CloseableHttpClient httpClient=builder.build();
	    HttpGet httpget=new HttpGet(url);
	    try {
			HttpResponse response=httpClient.execute(httpget);
			if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
			    HttpEntity entity=response.getEntity();
			    content=EntityUtils.toString(entity, code);
			    EntityUtils.consume(entity);
			}
			httpget.abort();
			httpget=null;
			return content;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
