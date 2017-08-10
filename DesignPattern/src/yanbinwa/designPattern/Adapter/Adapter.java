package yanbinwa.designPattern.Adapter;

public class Adapter
{
    interface Target
    {
        public String getName();
    }
    
    interface Adaptee
    {
        public String getStr();
    }
    
    class AdapteeImpl implements Adaptee
    {

        @Override
        public String getStr()
        {
            return "AdapteeImpl";
        }
        
    }
    
    class AdapterImpl implements Target
    {
        
        Adaptee adaptee = null;
        
        public AdapterImpl(Adaptee adaptee)
        {
            this.adaptee = adaptee;
        }

        @Override
        public String getName()
        {
            return adaptee.getStr();
        }
        
    }
    
    public void test()
    {
        Adaptee adaptee = new AdapteeImpl();
        Target adapter = new AdapterImpl(adaptee);
        System.out.println(adapter.getName());
        
    }
    
    public static void main(String[] args)
    {
        Adapter adapter = new Adapter();
        adapter.test();
    }
}
