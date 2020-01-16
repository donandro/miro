package com.miro.task.store;

import com.miro.task.models.CreateWidgetRequest;
import com.miro.task.models.IWidget;
import com.miro.task.models.Page;
import com.miro.task.models.UpdateWidgetRequest;

public interface IWidgetStore {
	public IWidget create(CreateWidgetRequest request);
	public IWidget update(int id, UpdateWidgetRequest updateRequest);
	public void delete(int id);
	public IWidget get(int id);
	public Page<IWidget> list(int pageNumber, int pageSize);
}
