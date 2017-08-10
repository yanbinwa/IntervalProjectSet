package yanbinwa.designPattern.ActiveObject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 实现了方法调用与执行的解耦，实现了异步调用。
 * 
 * 通过该方法，可以支持异步的调用
 * 
 * @author yanbinwa
 *
 */


public class ActiveObject
{
    interface ActiveObjectCallBack
    {
        public void callBack(String str);
    }
    
    class IActiveObject
    {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(100);
        Thread invokerThread;
        AtomicBoolean isRunning = new AtomicBoolean(false);
        
        public IActiveObject()
        {
            invokerThread = new Thread(new Runnable() {

                @Override
                public void run()
                {
                    isRunning.set(true);
                    while(isRunning.get())
                    {
                        try
                        {
                            Runnable runnable = queue.poll(1000, TimeUnit.MILLISECONDS);
                            if (runnable != null)
                            {
                                runnable.run();
                            }
                        } 
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                
            });
            invokerThread.start();
        }
        
        public void stop()
        {
            if (queue.size() > 0)
            {
                try
                {
                    Thread.sleep(1000);
                } 
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            isRunning.set(false);
        }
        
        public String doSomeThing1(String name)
        {
            return "doSomeThing1" + name;
        }
        
        public String doSomeThing2(String name)
        {
            return "doSomeThing2" + name;
        }
        
        public void doSomeThing1(String name, ActiveObjectCallBack callBack)
        {
            Runnable runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    String ret = doSomeThing1(name);
                    callBack.callBack(ret);
                }
            };
            queue.offer(runnable);
        }
        
        public void doSomeThing2(String name, ActiveObjectCallBack callBack)
        {
            Runnable runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    String ret = doSomeThing2(name);
                    callBack.callBack(ret);
                }
            };
            queue.offer(runnable);
        }
    }
    
    class CallBackImpl implements ActiveObjectCallBack
    {

        @Override
        public void callBack(String str)
        {
            System.out.println("InvokeClass:" + str);
        }
        
    }
    
    
    public void test()
    {
        IActiveObject activeObject = new IActiveObject();
        System.out.println(activeObject.doSomeThing1("Wyb"));
        System.out.println(activeObject.doSomeThing2("HelloWorld"));
        
        activeObject.doSomeThing1("Wjy", new CallBackImpl());
        activeObject.doSomeThing2("HelloWorld", new CallBackImpl());
        activeObject.stop();
        
    }
    
    public static void main(String[] args)
    {
        new ActiveObject().test();
    }
}
