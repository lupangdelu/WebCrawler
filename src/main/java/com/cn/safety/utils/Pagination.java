package com.cn.safety.utils;
/**
 * 分页bean
 * @author tech
 *
 */
public class Pagination {
	private static int pageRows = 10; //每页显示数

	private static int rows = 35;//用于非静态的每页显示数
	
	public static int getPageRows() {
		return pageRows;
	}

	public static void setPageRows(int pageRow) {
		pageRows = pageRow;
	}
	
	public static void setPageRows2(int pageRow) {
		rows = pageRow;
	}

	/**
	 * 获取起始行
	 * @return
	 */
	public static int getStartRow(int curPage) {
		return ((curPage - 1) * pageRows + 1);
	}
	
	public int getStartRow2(int curPage) {
		return ((curPage - 1) * rows + 1);
	}
	
	/**
	 * 获取终止行
	 * @return
	 */
	public static int getEndRow(int curPage) {
		return curPage * pageRows;
	}
	
	public int getEndRow2(int curPage) {
		return curPage * rows;
	}

	/**
	 * 返回总页数
	 * @param count 数据总行数
	 * @return
	 */
	public static int getPages(int count){
		if((count % pageRows) == 0){
			return (count / pageRows);
		}
		else{
			return (count / pageRows + 1);
		}
	}
	
	public int getPages2(int count){
		if((count % rows) == 0){
			return (count / rows);
		}
		else{
			return (count / rows + 1);
		}
	}
	
	public static void main(String[] args) {
		int r = getStartRow(2);
		int e = getEndRow(2);
		System.out.println(r+"--"+e);
	}
}
















