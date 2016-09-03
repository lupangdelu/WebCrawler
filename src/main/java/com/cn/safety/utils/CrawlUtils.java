package com.cn.safety.utils;

public class CrawlUtils {

	//要匹配的关键词
	private static final String[] keys = {"抢劫","袭击","枪","杀","偷","死","吸毒","爆炸"};
	/**
	 * 检测是否包含关键字
	 * 例如抢劫，袭击，枪，杀
	 */
	public static boolean checkKeywords(String str){
		for(int i=0;i<keys.length;i++){
			if(str.indexOf(keys[i]) != -1 ){
				return true;
			}
		}
		return false;
	}
}
