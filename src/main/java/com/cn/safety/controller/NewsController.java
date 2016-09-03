package com.cn.safety.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.safety.model.ResultData;
import com.cn.safety.model.UserRequest;
import com.cn.safety.pojo.User;
import com.cn.safety.service.ICrawlNewsService;
import com.cn.safety.utils.Constant;
import com.cn.safety.webMagic.NewsCrawler;
/**
 * 新闻控制器
 * @author tech
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	private ICrawlNewsService newsService;
	@Resource
	private NewsCrawler newsCrawler;
	
	@RequestMapping(value = "/getdate", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getDate(HttpServletResponse response)throws IOException {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		newsCrawler.crawl();
		String datetime = tempDate.format(new java.util.Date());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", datetime);
		return map;  
	}
	/**
	 * 得到新闻列表
	 * @param region 地区
	 * @param page 页码
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)  
    @ResponseBody  
    public ResultData<List<Map<String,Object>>> getNews(@RequestParam("region") String region,@RequestParam("page") Integer page) throws IOException {        
        ResultData<List<Map<String,Object>>> resultData =new ResultData<List<Map<String,Object>>>();  
        resultData.setStatus(0);  
        resultData.setData(null);  
        if (region == null || page == null) {
            resultData.setMessage(Constant.Null_Param_Error);  
        } else {
            try {
            	List<Map<String,Object>> news = newsService.getNews(region,page);
            	if(CollectionUtils.isEmpty(news)){
            		resultData.setMessage("无记录");  
            	}else{
            		resultData.setStatus(1);
            		resultData.setData(news);
            	}
            } catch (Exception e) {
                resultData.setMessage("获取新闻列表出错:"+e.getMessage());  
            }
        }
        return resultData;
    }
}
