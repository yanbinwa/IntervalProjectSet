package yanbinwa.designPattern.Proxy;

public class Proxy
{
    interface ProxyIf
    {
        public void getName();
    }
    
    class ClientProxy implements ProxyIf
    {

        ProxyIf proxyIf = null;
        
        @Override
        public void getName()
        {
            if (proxyIf == null)
            {
                proxyIf = new ServerProxy();
            }
            proxyIf.getName();
        }
        
    }
    
    class ServerProxy implements ProxyIf
    {
        
        @Override
        public void getName()
        {
            System.out.println("ServerProxy.getName()");
        }
        
    }
    
    public void test()
    {
        ProxyIf proxyIf = new ClientProxy();
        proxyIf.getName();
    }
    
    public static void main(String[] args)
    {
        new Proxy().test();
    }
    
}
