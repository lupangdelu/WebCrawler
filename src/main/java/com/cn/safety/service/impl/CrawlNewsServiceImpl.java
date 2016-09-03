package com.cn.safety.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.safety.dao.ICrawlNewsDao;
import com.cn.safety.pojo.CrawlNews;
import com.cn.safety.service.ICrawlNewsService;
import com.cn.safety.utils.Pagination;
@Service("crawlNewsService")
public class CrawlNewsServiceImpl  implements ICrawlNewsService{

	@Resource
	private ICrawlNewsDao crawlNewsDao;
	@Override
	public int insert(CrawlNews news) {
		int i = crawlNewsDao.insert(news);
		return i;
	}
	@Override
	public List<Map<String, Object>> getNews(String region, Integer page) {
		int startRow = Pagination.getStartRow(page) - 1;//起始行偏移量
		//参数
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startRow", startRow);
		param.put("pageRows", Pagination.getPageRows());//每页行数
		param.put("region", region);
		List<Map<String,Object>> contacts = this.crawlNewsDao.getNews(param);
		return contacts;
	}

}
