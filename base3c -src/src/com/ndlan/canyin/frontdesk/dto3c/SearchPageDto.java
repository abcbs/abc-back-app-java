package com.ndlan.canyin.frontdesk.dto3c;

/**
 * 
 * @Description:翻页搜索条件类 定义翻页和搜索条件 返回无线端
 * @author: husitong
 * @date: 2016-1-12 下午12:26:16
 */
public class SearchPageDto {
	
	private String searchValue;
	private String page;
	private String pageSize;
	private String pageUpOrDown; //up or down
	
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageUpOrDown() {
		return pageUpOrDown;
	}
	public void setPageUpOrDown(String pageUpOrDown) {
		this.pageUpOrDown = pageUpOrDown;
	}
	
	

}
