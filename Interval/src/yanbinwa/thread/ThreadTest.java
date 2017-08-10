package yanbinwa.thread;

class CustomThread1 extends Thread {
    public CustomThread1() {
        super("T1");
    }
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "start.");
        try {
            for (int i = 0; i < 5; i ++)
            {
                System.out.println(threadName + " loop at " + i);
                Thread.sleep(1000);
            }
            System.out.println(threadName + "end.");            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

class CustomThread extends Thread {
    CustomThread1 t1;
    public CustomThread(CustomThread1 t1)
    {
        super("T2");
        this.t1 = t1;
    }
    public void run()
    {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "start.");
        try
        {
            t1.join();
            System.out.println(threadName + "stop.");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

public class ThreadTest
{
    public static void main(String[] args) throws InterruptedException
    {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "start.");
        CustomThread1 thread1 = new CustomThread1();
        CustomThread thread = new CustomThread(thread1);
        thread1.start();
        Thread.sleep(2000);
        thread.start();
        thread.join();
        System.out.println(threadName + "stop.");
    }
}
