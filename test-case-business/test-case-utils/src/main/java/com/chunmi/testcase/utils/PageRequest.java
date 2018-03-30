package com.chunmi.testcase.utils;

public class PageRequest {
	private int page;
	private int pageSize;

	public PageRequest(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getOffset() {
		if (page > 0) {
			return (page - 1) * pageSize;
		}
		return 0;
	}
}
