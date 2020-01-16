package com.miro.task.models;

import java.util.List;

public class Page<T> {
	
	public Page(List<T> items, int pageNumber, int pageSize, int itemsCount) {
		this.items = items;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.itemsCount = itemsCount;
	}
	
	private List<T> items;
	
	private int pageNumber;
	
	private int pageSize;
	
	private int itemsCount;

	public List<T> getItems() {
		return items;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getItemsCount() {
		return itemsCount;
	}
}
