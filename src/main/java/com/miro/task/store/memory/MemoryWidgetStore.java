package com.miro.task.store.memory;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.miro.task.models.CreateWidgetRequest;
import com.miro.task.models.IWidget;
import com.miro.task.models.Page;
import com.miro.task.models.UpdateWidgetRequest;
import com.miro.task.store.IWidgetStore;

@Component("memory")
public class MemoryWidgetStore implements IWidgetStore {
	
	private int lastId = 3;
	
	MemoryRepository<Integer, Widget> widgets = new MemoryRepository<Integer, Widget>();
	
	public MemoryWidgetStore() {		
		widgets.add(1, new Widget(50, 50, 3, 100, 100));
		widgets.add(2, new Widget(50, 100, 1, 100, 100));
		widgets.add(3, new Widget(100, 100, 4, 100, 100));
		
		widgets.updateAll((oldValue) -> {
			for (Map.Entry<Integer, Widget> entry : oldValue.entrySet() ) {		    
			    Widget value = entry.getValue();
			    value.setId(entry.getKey());
			}
			return oldValue;
		});
	}
	
	@Override
	public IWidget create(CreateWidgetRequest request) {
		int zIndex = 0;
		if (request.getzIndex() == null) {
			zIndex = getMaxZIndex() + 1;
		} else {
			zIndex = request.getzIndex();
		}
    	
		Widget newWidget = new Widget(
				request.getX(),
				request.getY(),
				zIndex,
				request.getWidth(),
				request.getHeight());
		newWidget.setId(++lastId);
		
		widgets.updateAll((oldList) -> {
			widgets.add(lastId, newWidget);
			
			Optional<Widget> sameZIndexWidget =  widgets.getAll()
	    			.stream()
	    			.filter(w -> w.getzIndex() == newWidget.getzIndex() && w.getId() != lastId)
	    			.findFirst();
			
			if (!sameZIndexWidget.isEmpty()) {
				for (Map.Entry<Integer, Widget> entry : oldList.entrySet() ) {		    
				    Widget value = entry.getValue();
				    if (value.getzIndex() >= newWidget.getzIndex() && value.getId() != lastId) {
				    	value.setzIndex(value.getzIndex() +1);
						value.setModifyDate(new Date());
					 }
				}
	    	}
			return oldList;
		});
		
		return newWidget;
	}

	@Override
	public IWidget update(int id, UpdateWidgetRequest updateRequest) {
		
		return widgets.update(id, (oldValue) -> {
			
			if (updateRequest.getX() != null) {
				oldValue.setX(updateRequest.getX());
	    	}
	    	
	    	if (updateRequest.getY() != null) {
	    		oldValue.setY(updateRequest.getY());
	    	}
	    	
	    	if (updateRequest.getzIndex() != null) {
	    		oldValue.setzIndex(updateRequest.getzIndex());
	    	}
	    	
	    	if (updateRequest.getWidth() != null) {
	    		oldValue.setWidth(updateRequest.getWidth());
	    	}
	    	
	    	if (updateRequest.getHeight() != null) {
	    		oldValue.setHeight(updateRequest.getHeight());
	    	}
	    	
	    	oldValue.setModifyDate(new Date());
	    	
	    	Optional<Widget> sameZIndexWidget = widgets.getAll()
	    			.stream()
	    			.filter(w -> w.getzIndex() == oldValue.getzIndex() && w.getId() != id)
	    			.findFirst();
	    	
	    	if (!sameZIndexWidget.isEmpty()) {
	    		widgets.updateAll((oldList) -> {
	    			
	    			for (Map.Entry<Integer, Widget> entry : oldList.entrySet() ) {		    
	    			    Widget value = entry.getValue();
	    			    
	    			    if (value.getzIndex() >= oldValue.getzIndex() && value.getId() != id) {
	    			    	value.setzIndex(value.getzIndex() +1);
							value.setModifyDate(new Date());
						 }
	    			}
	    			return oldList;
				});
	    	}
	    	
			return oldValue;
		});
	}

	@Override
	public void delete(int id) {
		widgets.delete(id);
	}

	@Override
	public IWidget get(int id) {
		return widgets.get(id);
	}

	@Override
	public Page<IWidget> list(int pageNumber, int pageSize) {
		Collection<Widget> collection =  widgets.getAll();
		int count = collection.size();
		List<? extends IWidget> items = collection
				.stream()
				.sorted(Comparator.comparingInt(Widget::getzIndex))
				.skip((pageNumber -1) * pageSize)
				.limit(pageSize)
				.collect(Collectors.toList());
		
		return new Page<IWidget>((List<IWidget>) items, pageNumber, pageSize, count);
	}
	
	private int getMaxZIndex() {
		Widget maxZIndexByAge = widgets.getAll()
			      .stream()
			      .min(Comparator.comparing(Widget::getzIndex))
			      .orElseThrow(NoSuchElementException::new);
		return maxZIndexByAge.getzIndex();
	}

}
