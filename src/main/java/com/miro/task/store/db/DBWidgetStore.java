package com.miro.task.store.db;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.miro.task.models.CreateWidgetRequest;
import com.miro.task.models.IWidget;
import com.miro.task.models.Page;
import com.miro.task.models.UpdateWidgetRequest;
import com.miro.task.store.IWidgetStore;

@Component("DB")
public class DBWidgetStore implements IWidgetStore {

	@Autowired
    WidgetRepository repository;
	
	@Override
	@Transactional
	public IWidget create(CreateWidgetRequest request) {
		
		Widget widget = new Widget();
		
		widget.setHeight(request.getHeight());
		widget.setX(request.getX());
		widget.setY(request.getY());
		widget.setWidth(request.getWidth());
		
		int zIndex = 0;
		if (request.getzIndex() == null) {
			zIndex = repository.findMaxZIndex();
		} else {
			zIndex = request.getzIndex();
			Optional<Widget> samezIndex = repository.findByZIndex(zIndex);
			if (samezIndex.isPresent()) {
				repository.moveWidgets(zIndex);
			}
		}
		
		widget.setzIndex(zIndex);
		widget = repository.save(widget);
		return widget;
	}
	
	@Transactional
	@Override
	public IWidget update(int id, UpdateWidgetRequest updateRequest) {
		Optional<Widget> widget = repository.findById(id);
		
		if (widget.isPresent()) {
			Optional<Widget> samezIndex = repository.findByZIndex(updateRequest.getzIndex());
			if (samezIndex.isPresent()) {
				repository.moveWidgets(updateRequest.getzIndex());
			}
			
			Widget existsWidget = widget.get();
			
			if (updateRequest.getX() != null) {
				existsWidget.setX(updateRequest.getX());
	    	}
	    	
	    	if (updateRequest.getY() != null) {
	    		existsWidget.setY(updateRequest.getY());
	    	}
	    	
	    	if (updateRequest.getzIndex() != null) {
	    		existsWidget.setzIndex(updateRequest.getzIndex());
	    	}
	    	
	    	if (updateRequest.getWidth() != null) {
	    		existsWidget.setWidth(updateRequest.getWidth());
	    	}
	    	
	    	if (updateRequest.getHeight() != null) {
	    		existsWidget.setHeight(updateRequest.getHeight());
	    	}
	    	
	    	existsWidget.setModifyDate(new Date());
	    	existsWidget = repository.save(existsWidget);
	    	
	    	return existsWidget;
		}
		return null;
	}

	@Override
	public void delete(int id) {
		Optional<Widget> widget = repository.findById(id);
        if(widget.isPresent()) {
            repository.delete(widget.get());
        }
	}

	@Override
	public IWidget get(int id) {
		Optional<Widget> widget = repository.findById(id);
        if(widget.isPresent()) {
            return widget.get();
        } else {
            return null;
        }
	}

	@Override
	public Page<IWidget> list(int pageNumber, int pageSize) {
		int count = (int)repository.count();
		List<? extends IWidget> items = repository.findAllOrderByIdAsc(PageRequest.of(pageNumber -1, pageSize));
		return new Page<IWidget>((List<IWidget>) items, pageNumber, pageSize, count);
	}
}
