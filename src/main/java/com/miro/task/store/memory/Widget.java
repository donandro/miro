package com.miro.task.store.memory;

import java.util.Date;
import com.miro.task.models.IWidget;

public class Widget implements IWidget {
	
	public Widget(int x, int y, int zIndex, int width, int height) {
		setX(x);
		setY(y);
		setzIndex(zIndex);
		setWidth(width);
		setHeight(height);
	}
	
	private Integer id;
	
	private int x;
	
    private int y;
    
    private int zIndex;
    
    private int width;
    
    private int height;
    
    private Date modifyDate;
    
    @Override
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int getzIndex() {
		return zIndex;
	}
	
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	@Override
	public Date getModifyDate() {
		return modifyDate;
	}
	
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
