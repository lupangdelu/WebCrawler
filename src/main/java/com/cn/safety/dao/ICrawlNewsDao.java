package com.cn.safety.dao;

import java.util.List;
import java.util.Map;

import com.cn.safety.pojo.CrawlNews;

/**
 * 抓取到的新闻
 * @author Tech
 *
 */
public interface ICrawlNewsDao {

	int insert(CrawlNews record);
	List<String> selectSeedUrls();
	
	List<Map<String,Object>> getNews(Map<String,Object> map);
}
