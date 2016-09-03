package com.cn.safety.timer;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cn.safety.pojo.CrawlNews;
import com.cn.safety.service.ICrawlNewsService;
/**
 * spring定时任务
 * @author tech
 *
 */
@Service
public class TaskJob {

	@Resource
	private ICrawlNewsService crawlNewsService;
	//cron表达式：秒，分，时，日，月，星期，年
	//@Scheduled(cron = "0 39 * * * ?")  
	public void job() {
		System.out.println("任务执行中。。。");
		CrawlNews news = new CrawlNews();
		news.setUrl("http://news.xinhuanet.com/legal/2016-03/29/c_1118476245.htm");
		try{
			int i = crawlNewsService.insert(news);
		}catch(DuplicateKeyException e){
			System.out.println("------------唯一索引");
		}
	}
}
