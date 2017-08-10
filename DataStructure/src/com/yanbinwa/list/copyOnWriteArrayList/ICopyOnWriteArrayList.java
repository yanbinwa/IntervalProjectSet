package com.yanbinwa.list.copyOnWriteArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ICopyOnWriteArrayList <T>
{
    List<T> lists;
    
    final ReentrantLock lock;
    
    public ICopyOnWriteArrayList(int lenght)
    {
        lists = new ArrayList<T>(lenght);
        
        lock = new ReentrantLock();
    }
    
    public boolean add(T element)       //只有在修改时才会上锁，避免多个线程同时修改
    {
        lock.lock();
        try
        {
            List<T> tmpLists = new ArrayList<T>(lists);     //这里确保了tmpLists.add(element)不会block读操作
            tmpLists.add(element);
            lists = tmpLists;
            return true;
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public T get(int index)
    {
        return lists.get(index);
    }
}
