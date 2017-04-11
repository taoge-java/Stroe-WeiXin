package com.stroe.weixin.dao.message.response;


import com.stroe.weixin.dao.base.BaseMessae;
import com.stroe.weixin.dao.message.Articles;

/**
 * 回复图文信息
 * @author zengjintao
 * @version
 * 2017年4月9日22:42
 */ 
public class GraphicMessage extends BaseMessae{
	
	private String  ArticleCount;
	
	
	private Articles Articles;


	public String getArticleCount() {
		return ArticleCount;
	}


	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}


	public Articles getArticles() {
		return Articles;
	}


	public void setArticles(Articles articles) {
		Articles = articles;
	}



}
