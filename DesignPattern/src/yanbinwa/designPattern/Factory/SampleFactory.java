package yanbinwa.designPattern.Factory;

/**
 * 
 * 可以将创建实例的工作与使用实例的工作区分开来，例如可以通过传入参数来确定实例的最终类型
 * 
 * @author yanbinwa
 *
 */

public class SampleFactory
{
    interface Product
    {
        public void getName();
    }
    
    class Product1 implements Product
    {

        @Override
        public void getName()
        {
            System.out.println("Product1");
        }
        
    }
    
    class Product2 implements Product
    {

        @Override
        public void getName()
        {
            System.out.println("Product2");
        }
        
    }
    
    class Factory
    {
        public Product getProduct(int type)
        {
            if(type == 1)
            {
                return new Product1();
            }
            else if (type == 2)
            {
                return new Product2(); 
            }
            else
            {
                return null;
            }
        }
    }
    
    public void test()
    {
        Factory factory = new Factory();
        Product product1 = factory.getProduct(1);
        product1.getName();
        
        Product product2 = factory.getProduct(2);
        product2.getName();
    }
    
    public static void main(String[] args)
    {
        SampleFactory sf = new SampleFactory();
        sf.test();
    }
    
}
