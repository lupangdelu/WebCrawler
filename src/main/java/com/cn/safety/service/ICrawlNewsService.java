package com.cn.safety.service;

import java.util.List;
import java.util.Map;

import com.cn.safety.pojo.CrawlNews;

public interface ICrawlNewsService {

	public int insert(CrawlNews news);
	
	public List<Map<String, Object>> getNews(String region,Integer page);
}
