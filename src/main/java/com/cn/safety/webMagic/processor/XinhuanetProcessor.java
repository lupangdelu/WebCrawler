package com.cn.safety.webMagic.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.safety.pojo.CrawlNews;
import com.cn.safety.webMagic.pipeline.CrawlNewsPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 新华网法制频道
 * @author tech
 *
 */
@Service("xinhuanetProcessor")
public class XinhuanetProcessor implements PageProcessor {

	@Autowired
	private CrawlNewsPipeline crawlNewsPipeline;
	public static final String URL_Seed = "http://www.xinhuanet.com/legal/index.htm";//种子url
	public static final String URL_LIST = "(http://www.xinhuanet.com/legal/\\w+.htm)";//包括新闻列表的url
	//内容详情页，注意正则表达式要用（）括起来。
	//http://news.xinhuanet.com/legal/2016-03/27/c_1118455361.htm
	public static final String URL_Content = "(http://news.xinhuanet.com/legal/\\d+-\\d+/(.*))";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    
	@Override
	public void process(Page page) {
		if(page.getUrl().regex(URL_Content).match()){
    		String title = page.getHtml().xpath("//h1[@id='title']/text()").toString();
    		String content = page.getHtml().xpath("//div[@class='article']/html()").toString();
    		if(title != null){
    			CrawlNews news = new CrawlNews();
        		news.setUrl(page.getUrl().toString());
        		news.setTitle(title);
        		news.setContent(content);
        		news.setRegion("中国");
                page.putField("news", news);
                System.out.println("------"+title+"-------");
                System.out.println("------"+content+"-------");
    		}
    	}else if(page.getUrl().regex(URL_LIST).match()){
            page.addTargetRequests(page.getHtml().links().regex(URL_Content).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
    	}
        if(page.getResultItems().get("news") == null){
        	 //设置skip之后，这个页面的结果不会被Pipeline处理
             page.setSkip(true);
        }
		
	}

	@Override
	public Site getSite() {
		return site;
	}

	/*
     * 抓取的入口方法
     */
    public void crawl(){
    	 Spider.create(new XinhuanetProcessor())
     	.addUrl(URL_Seed)
     	.addPipeline(crawlNewsPipeline)
     	.thread(5)
     	.run();
    }
}






