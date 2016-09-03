package com.cn.safety.webMagic.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cn.safety.pojo.CrawlNews;
import com.cn.safety.service.ICrawlNewsService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * 自定义pipeline
 * @author tech
 *
 */
@Component("crawlNewsPipeline")
public class CrawlNewsPipeline implements Pipeline {

	@Resource
	private ICrawlNewsService crawlNewsService;
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		System.out.println("get page: " + resultItems.getRequest().getUrl());
        //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
		CrawlNews news = (CrawlNews)resultItems.get("news");
		int i = this.crawlNewsService.insert(news);
	}

}








