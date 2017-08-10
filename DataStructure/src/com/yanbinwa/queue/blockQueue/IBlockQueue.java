package com.yanbinwa.queue.blockQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用循环数组来实现
 * 
 * @author yanbinwa
 *
 * @param <T>
 */

public class IBlockQueue
{
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private Object[] array;
    private int readIndex;
    private int writeIndex;
    private int count;
    private final int size;
    
    public IBlockQueue(int size)
    {
        
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();  
        notFull = lock.newCondition();
        array = new Object[size];       //要空出一个槽位
        readIndex = 0;     //将要读
        writeIndex = 0;    //将要写，这里要有一个位置，保证除了第一次外，readIndex和writeIndex不重叠
        count = 0;
        this.size = size;
    }
    
    public Object poll() throws InterruptedException
    {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try
        {
            try
            {
                while(count == 0)
                {
                    notEmpty.await(); 
                }
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return null;
            }
            
            return get();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public boolean offer(Object obj) throws InterruptedException
    {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try
        {
            try
            {
                while(count == array.length)
                {
                    notFull.await();
                }
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return false;
            }
            
            insert(obj);
            return true;
        }
        finally
        {
            lock.unlock();
        }
    }
    
    private Object get()
    {
        System.out.println("readIndex: " + readIndex);
        Object ret = array[readIndex ++];
        if (readIndex >= size)
        {
            readIndex = 0;
        }
        count --;
        notFull.signal();
        return ret;
    }
    
    private void insert(Object obj)
    {
        System.out.println("writeIndex: " + writeIndex);
        array[writeIndex ++] = obj;
        if (writeIndex >= size)
        {
            writeIndex = 0;
        }
        count ++;
        notEmpty.signal();
    }
    
    public static void main(String[] args)
    {
        IBlockQueue bq = new IBlockQueue(5);
        Thread producer = new Thread(new Runnable(){

            @Override
            public void run()
            {
                for(int i = 0; i < 100; i ++)
                {
                    String msg = "HelloWorld " + i;
                    try
                    {
                        bq.offer(msg);
                        System.out.println("Input: " + msg);
                        Thread.sleep(10);
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
                for(int i = 0; i < 100; i ++)
                {
                    try
                    {
                        String msg = (String)bq.poll();
                        System.out.println("Output: " + msg);
                        Thread.sleep(1);
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
}
