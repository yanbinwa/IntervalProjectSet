package yanbinwa.designPattern.Observer;

import java.util.HashMap;
import java.util.Map;

public class Observer
{
    interface ObserverIf
    {
        public void notify(String notifyStr);
        public String getName();
    }
    
    class ObserverIfImpl implements ObserverIf
    {

        String name;
        
        public ObserverIfImpl(String name)
        {
            this.name = name;
        }
        
        @Override
        public void notify(String notifyStr)
        {
            System.out.println(getName() + ": " + notifyStr);
        }

        @Override
        public String getName()
        {
            return this.name;
        }
        
    }
    
    class Subject
    {
        Map<String, ObserverIf> observerMap = new HashMap<String, ObserverIf>();
        
        public void register(ObserverIf observerIf)
        {
            observerMap.put(observerIf.getName(), observerIf);
        }
        
        public void unregister(ObserverIf observerIf)
        {
            observerMap.remove(observerIf.getName());
        }
        
        public void notify(String notifyStr)
        {
            for(ObserverIf observer : observerMap.values())
            {
                observer.notify(notifyStr);
            }
        }
    }
    
    public void test()
    {
        Subject subject = new Subject();
        ObserverIf observer1 = new ObserverIfImpl("observer1");
        subject.register(observer1);
        ObserverIf observer2 = new ObserverIfImpl("observer2");
        subject.register(observer2);
        subject.notify("HelloWorld");
        subject.unregister(observer1);
        subject.notify("Yanbin");
    }
    
    public static void main(String[] args)
    {
        new Observer().test();
    }
    
}
