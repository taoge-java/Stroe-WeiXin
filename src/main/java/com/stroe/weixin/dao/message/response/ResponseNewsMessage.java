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
public class ResponseNewsMessage extends BaseMessae{
	
	private int  ArticleCount;
	
	private List<Articles> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Articles> getArticles() {
		return Articles;
	}

	public void setArticles(List<Articles> articles) {
		Articles = articles;
	}

}
