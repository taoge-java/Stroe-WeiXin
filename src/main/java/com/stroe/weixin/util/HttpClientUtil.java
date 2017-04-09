package com.stroe.weixin.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.stroe.weixin.config.SysConfig;
import com.stroe.weixin.constant.Constant;
import com.stroe.weixin.dao.AccessToken;
/**
 * HTTP请求工具类
 * @author zengjintao
 * 2017年4月7日下午21:54
 */
public class HttpClientUtil {
	
	public static String httpGet(String url){
		return httpGet(url,"UTF-8");
	}

	/**
	 * httpGet请求
	 * @param url 
	 * @param code  编码
	 * @return
	 */
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
	/**
	 * httpPost请求
	 * @param url
	 * @param body
	 * @param charset
	 * @return
	 */
	public static String httpPost(String url,String body,String charset){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
	    CloseableHttpClient httpClient=builder.build();
	    HttpPost post=new HttpPost();
	    try {
			post.setEntity(new StringEntity(body, charset));//设置参数
	        HttpResponse response=httpClient.execute(post);
	        if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
	        	HttpEntity entity=response.getEntity();//获取响应信息
	        	content=EntityUtils.toString(entity,charset);
	        	EntityUtils.consume(entity);//关闭流
	        }
	        post.abort();
	        httpClient=null;
	        return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 文件素材上传
	 * @param file_path  文件路径
	 * @param access_token 
	 * @param type  素材
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	public static String upload(String file_path,String access_token,String type) throws IOException{
		File file=new File(file_path);
		if(!file.exists()||!file.isFile()){
			throw new IOException("file is not to exist");
		}
		String upload_url=Constant.UPLOAD_URL.replace("ACCESS_TOKEN", access_token).replace("TYPE", type);
		URL url=new URL(upload_url);
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
	    connection.setUseCaches(false);//设置不要缓存
	    connection.setRequestProperty("Connection", "Keep-Alive");
	    connection.setRequestProperty("charset", "utf-8");
	    String  BOUNDARY="--------------"+System.currentTimeMillis();
	    //boundary就是request头和上传文件内容的分隔符  
	    connection.setRequestProperty("Content-type", "multipart/form-data;boundary="+BOUNDARY);
	    StringBuilder sb=new StringBuilder();
	    sb.append("--");
	    sb.append(BOUNDARY);
	    sb.append("\r\n");
	    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""  
        + file.getName() + "\"\r\n");  
        sb.append("Content-Type:application/octet-stream\r\n\r\n");  
        System.out.println(sb.toString());
        byte[] by= sb.toString().getBytes("UTF-8");
      //获取输出流
	    OutputStream out=connection.getOutputStream();
	    //输出表头
	    out.write(by);
	    /**
	     * 文件正文部分
	     */
	    DataInputStream data=new DataInputStream(new FileInputStream(file));
	    int i=0;
	    byte[] bytes=new byte[1024];
	    while((i=data.read(bytes, 0, 1024))!=-1){
	    	out.write(bytes, 0, i);
	    }
	    if(data!=null){
	    	data.close();
	    }
	    /**
	     * 文件结尾部分
	     */
	    byte[] foot = ("\r\n--" +BOUNDARY+ "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
	    out.write(foot);
	    out.flush();
	    out.close();
	    StringBuffer buffer=new StringBuffer();
	    BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String line=null;
	    if((line=reader.readLine())!=null){
	    	buffer.append(line);
	    }
	    reader.close();
	    if(buffer!=null)
		   return buffer.toString();
	    return null;
	}
	
	public static AccessToken  getAccesstoken(){
		AccessToken accessToken=new AccessToken();
		String url=Constant.URL.replace("APPID",SysConfig.WEIXIN_APPID).replace("APPSECRET", SysConfig.WEIXIN_APPSECRET);
		String result=httpGet(url);
		JSONObject json=JSONObject.fromObject(result);
		if(json!=null){
			accessToken.setAccessToken(json.getString("access_token"));
			accessToken.setTime_out(json.getInt("expires_in"));
		}
		return accessToken;
	}
	
}
