package com.chunmi.testcase.utils;

import java.util.List;

public class PageBean<T> {
	// 当前页列表数
	private List<T> list;
	// 符合条件的记录条目数
	private int rows;
	// 总页数
	private int pageCount;
	// 每页显示多少条目
	private int pageSize = 10;
	// 当前页码
	private int pageCurrent = 1;
	// 上一页码
	private int prePageNo;
	// 下一页码
	private int nextPageNo;
	// 第一页码
	private int firstPageNo = 1;
	// 最后一页码
	private int lastPageNo;
	//初始页码
	private int startNo = 1;
	// 结束页码
	private int endNo;
	// 分页开始的条数
	private int startRowNum;
	// 分页结束的条数
	private int endRowNum;
	// 通用对象
	private Object objectBean;
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getPrePageNo() {
		return prePageNo;
	}
	public void setPrePageNo(int prePageNo) {
		this.prePageNo = prePageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public int getLastPageNo() {
		return lastPageNo;
	}
	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getEndRowNum() {
		return endRowNum;
	}
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	public Object getObjectBean() {
		return objectBean;
	}
	public void setObjectBean(Object objectBean) {
		this.objectBean = objectBean;
	}



}
