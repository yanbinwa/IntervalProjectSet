package com.yanbinwa.map.concurrentHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentHashMap <T1, T2>
{
    class Element
    {
        Element next;
        T1 key;
        T2 value;
        
        public Element(T1 key, T2 value)
        {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        
        public void setNext(Element next)
        {
            this.next = next;
        }
        
        public T1 getKey()
        {
            return this.key;
        }
        
        public void setKey(T1 key)
        {
            this.key = key;
        }
        
        public T2 getValue()
        {
            return this.value;
        }
        
        public void setValue(T2 value)
        {
            this.value = value;
        }
    }
    
    final List<Element> buckets;
    int size;
    final List<ReadWriteLock> locks;
    
    public ConcurrentHashMap(int size)
    {
        this.size = size;
        buckets = new ArrayList<Element>(size);
        for(int i = 0; i < size; i ++)
        {
            buckets.add(i, null);       //这里需要使用add，而非set
        }
        locks = new ArrayList<ReadWriteLock>(size);
        for(int i = 0; i < size; i ++)
        {
            locks.add(new ReentrantReadWriteLock(false));
        }
    }
    
    public void put(T1 key, T2 value)
    {
        int hash = key.hashCode();
        int index = hash % size;
        ReadWriteLock lock = locks.get(index);
        lock.writeLock().lock();
        try
        {
            Element bucket = buckets.get(index);
            if (bucket == null)
            {
                Element element = new Element(key, value);
                buckets.set(index, element);
                return;
            }
            while(bucket != null)
            {
                if(bucket.getKey().hashCode() == hash)
                {
                    bucket.setValue(value);
                    break;
                }
                if(bucket.next == null)
                {
                    Element element = new Element(key, value);
                    bucket.setNext(element);
                    break;
                }
                bucket = bucket.next;
            }
        }
        finally
        {
            lock.writeLock().unlock();
        }
        
    }
    
    public T2 get(T1 key)
    {
        int hash = key.hashCode();
        int index = hash % size;
        ReadWriteLock lock = locks.get(index);
        lock.readLock().lock();
        try
        {
            Element bucket = buckets.get(index);
            if (bucket == null)
            {
                return null;
            }
            T2 ret = null;
            while(bucket != null)
            {
                if(bucket.getKey().hashCode() == hash)
                {
                    ret= bucket.getValue();
                    break;
                }
                bucket = bucket.next;
            }
            return ret;
        }
        finally
        {
            lock.readLock().unlock();
        }
        
    }
    
    public static void main(String[] args)
    {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(10);
        map.put("wyb", "123");
        map.put("wjy", "456");
        map.put("wyb", "789");
        
        System.out.println(map.get("wyb"));
        System.out.println(map.get("wjy"));
    }
}
