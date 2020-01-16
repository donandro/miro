package com.miro.task.models;

import com.miro.task.paging.IPageble;

public class GetWidgetListRequest implements IPageble {

	private Integer pageNumber;
	
	private Integer pageSize;
	
	private final int DEFAULT_PAGE_SIZE = 10;
	
	private final int MIN_PAGE_SIZE = 1;
	
	private final int MAX_PAGE_SIZE = 500;
	
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public int getPageNumber() {
		
		if (pageNumber == null || pageNumber < 0) {
			return 1;
		}
		
		return pageNumber;
	}

	@Override
	public int getSize() {
		
		if (pageSize == null) {
			return DEFAULT_PAGE_SIZE;
		}
		
		if (pageSize < MIN_PAGE_SIZE) {
			return MIN_PAGE_SIZE;
		}
		
		if (pageSize > MAX_PAGE_SIZE) {
			return MAX_PAGE_SIZE;
		}
		
		return pageSize;
	}

}
