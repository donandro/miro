package com.miro.task.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miro.task.store.IWidgetStore;
import com.miro.task.models.CreateWidgetRequest;
import com.miro.task.models.GetWidgetListRequest;
import com.miro.task.models.IWidget;
import com.miro.task.models.Page;
import com.miro.task.models.UpdateWidgetRequest;

@RestController
@RequestMapping("/api/widgets")
public class WidgetController {

	private IWidgetStore widgetStore;
	
    public WidgetController(@Qualifier("WidgetStore") IWidgetStore widgetStore) {
        this.widgetStore = widgetStore;
    }
	
	@PostMapping
    public ResponseEntity<IWidget> create(@RequestBody CreateWidgetRequest request) {
		IWidget widget = widgetStore.create(request);
        return new ResponseEntity<>(widget, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<IWidget> get(@PathVariable int id) {
		IWidget widget = widgetStore.get(id);
		if (widget == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<>(widget, HttpStatus.OK);
    }
	
	@GetMapping
    public ResponseEntity<Page<IWidget>> getList(GetWidgetListRequest request) {
		Page<IWidget> widgets = widgetStore.list(request.getPageNumber(), request.getSize());
        return new ResponseEntity<>(widgets, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
    	IWidget widget = widgetStore.get(id);
		
		if (widget == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
    	widgetStore.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<IWidget> patchWidget(@PathVariable int id, @RequestBody UpdateWidgetRequest request) {
    	IWidget widget = widgetStore.get(id);
		
		if (widget == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		IWidget responseWidget = widgetStore.update(id, request);
    	return new ResponseEntity<>(responseWidget, HttpStatus.OK);
    }
}
