package com.miro.task.store.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.miro.task.models.IWidget;

@Entity
@Table(name="WIDGETS")
public class Widget implements IWidget {
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    @Column(name="x")
	private int x;
	
    @Column(name="y")
    private int y;
    
    @Column(name="z_index")
    private int zIndex;
    
    @Column(name="width")
    private int width;
    
    @Column(name="height")
    private int height;
    
    @Column(name="modify_date", nullable=true)
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