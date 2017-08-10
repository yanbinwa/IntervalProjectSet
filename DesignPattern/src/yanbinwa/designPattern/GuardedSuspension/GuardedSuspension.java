package yanbinwa.designPattern.GuardedSuspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * 将非Blocking Queue转换为Block Queue. 
 * 
 * @author yanbinwa
 *
 */

public class GuardedSuspension
{
    class IBlockQueue<T> 
    {
        Queue<T> queue = new LinkedList<T>();
        final int maxLenght;
        Object offerObj = new Object();
        Object pollObj = new Object();
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        
        public IBlockQueue()
        {
            maxLenght = Integer.MAX_VALUE;
        }
        
        public IBlockQueue(int lenght)
        {
            maxLenght = lenght;
        }
        
        public boolean offer(T t)
        {
            int size = getSize();
            while(size >= maxLenght)
            {
                _waitOffer();
                size = getSize();
            }
            
            return _offer(t);
        }
        
        public boolean offer(T t, int timeout)
        {
            int size = getSize();
            if(size >= maxLenght)
            {
                _waitOffer();
            }
            
            return _offer(t);
        }
        
        public T poll()
        {
            int size = getSize();
            while(size == 0)
            {
                _waitPoll();
                size = getSize();
            }
            
            return _poll();
        }
        
        public T poll(int timeout)
        {
            int size = getSize();
            if(size == 0)
            {
                _waitPoll();
            }
            
            return _poll();
        }
        
        public int getSize()
        {
            int size = 0;
            lock.readLock().lock();
            try
            {
                size = queue.size();
            }
            finally
            {
                lock.readLock().unlock();
            }
            return size;
        }
        
        private boolean _offer(T t)
        {
            lock.writeLock().lock();
            try
            {
                if (queue.size() >= maxLenght)
                {
                    System.out.println("Offer failed");
                    return false;
                }
                queue.add(t);
            }
            finally
            {
                lock.writeLock().unlock();
            }
            System.out.println("Offer success");
            _notifyPoll();
            return true;
        }
        
        private T _poll()
        {
            T ret = null;
            lock.writeLock().lock();
            try
            {
                if (queue.size() == 0)
                {
                    System.out.println("Offer failed");
                    return null;
                }
                ret = queue.poll();
            }
            finally
            {
                lock.writeLock().unlock();
            }   
            System.out.println("Poll success");
            _notifyOffer();
            return ret;
        }
        
        private void _waitOffer()
        {
            synchronized(offerObj)
            {
                try
                {
                    offerObj.wait();
                } 
                catch (InterruptedException e)
                {
                    System.out.println("Wait offer interruptedException");
                }
            }
        }
        
        private void _notifyOffer()
        {
            synchronized(offerObj)
            {
                offerObj.notifyAll();
            }
        }
        
        private void _waitPoll()
        {
            synchronized(pollObj)
            {
                try
                {
                    pollObj.wait();
                } 
                catch (InterruptedException e)
                {
                    System.out.println("Wait poll interruptedException");
                }
            }
        }
        
        private void _notifyPoll()
        {
            synchronized(pollObj)
            {
                pollObj.notifyAll();
            }
        }
    }
    
    public void test()
    {
        IBlockQueue<String> queue = new IBlockQueue<String>(3);
        
        Thread producer = new Thread(new Runnable(){

            @Override
            public void run()
            {
                for(int i = 0; i < 10; i ++)
                {
                    String msg = "HelloWorld " + i;
                    queue.offer(msg);
                    try
                    {
                        Thread.sleep(1000);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            
        });
        
        producer.start();
        
        try
        {
            Thread.sleep(5000);
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        Thread comsumer = new Thread(new Runnable(){

            @Override
            public void run()
            {
                for(int i = 0; i < 10; i ++)
                {
                    String msg = queue.poll();
                    System.out.println(msg);
                    try
                    {
                        Thread.sleep(100);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            
        });
        
        comsumer.start();
        
        try
        {
            producer.join();
            comsumer.join();
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }        
    }
    
    
    public static void main(String[] args)
    {
        new GuardedSuspension().test();
    }
}
