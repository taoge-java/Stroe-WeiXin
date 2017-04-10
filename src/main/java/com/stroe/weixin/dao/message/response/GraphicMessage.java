package com.stroe.weixin.dao.message.response;

import java.util.List;

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
	
	
	private List<Articles> Articles;


	public String getArticleCount() {
		return ArticleCount;
	}


	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}


	public List<Articles> getArticles() {
		return Articles;
	}


	public void setArticles(List<Articles> articles) {
		Articles = articles;
	}

}
