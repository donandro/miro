package com.miro.task.store.memory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

public class MemoryRepository<TKey, TValue> {
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    private final Lock readLock = readWriteLock.readLock();
 
    private final Lock writeLock = readWriteLock.writeLock();
 
    private Map<TKey, TValue> items = new HashMap<>();
    
    public void add(TKey key, TValue value)
    {
        writeLock.lock();
        try
        {
        	items.put(key, value);
        }
        finally
        {
            writeLock.unlock();
        }
    }
     
    public TValue get(TKey key)
    {
        readLock.lock();
        try
        {
            return items.get(key);
        }
        finally
        {
            readLock.unlock();
        }
    }
    
    public Collection<TValue> getAll()
    {
        readLock.lock();
        try
        {
            return items.values();
        }
        finally
        {
            readLock.unlock();
        }
    }
    
    public TValue update(TKey key, Function<TValue, TValue> updater)
    {
    	writeLock.lock();
        try
        {
        	TValue oldValue = items.get(key);
        	TValue newValue = updater.apply(oldValue);
        	items.put(key, newValue);
            return items.get(key);
        }
        finally
        {
        	writeLock.unlock();
        }
    }
    
    public Collection<TValue> updateAll(Function<Map<TKey, TValue>, Map<TKey, TValue>> updater)
    {
    	writeLock.lock();
        try
        {
        	Map<TKey, TValue> oldValue = items;
        	Map<TKey, TValue> newValue = updater.apply(oldValue);
        	items = newValue;
            return items.values();
        }
        finally
        {
        	writeLock.unlock();
        }
    }
    
    public void delete(TKey key)
    {
        writeLock.lock();
        try
        {
        	items.remove(key);
        }
        finally
        {
            writeLock.unlock();
        }
    }
}
