package yanbinwa.designPattern.Factory;

/**
 * 抽象方法模式是针对产品族的概念，比如当前有两中产品，一种的ProductA，一种是ProductB，而每个产品又有两个产品族，分别是
 * ProductA1 和 ProductA2，以及ProductB1和ProductB2. Factory1 生成ProductA1和ProductB1，Facotory2生成
 * ProductA2和ProductB2。
 * 
 * @author yanbinwa
 *
 */

public class AbstractFactory
{

    // The define of produce
    
    interface ProductA
    {
        public void getName();
    }
    
    class ProductA1 implements ProductA
    {

        @Override
        public void getName()
        {
            System.out.println("produceA1");
        }
        
    }
    
    class ProductA2 implements ProductA
    {

        @Override
        public void getName()
        {
            System.out.println("produceA2");
        }
        
    }
    
    interface ProductB
    {
        public void getName();
    }
    
    class ProductB1 implements ProductB
    {

        @Override
        public void getName()
        {
            System.out.println("produceB1");
        }
        
    }
    
    class ProductB2 implements ProductB
    {

        @Override
        public void getName()
        {
            System.out.println("produceB2");
        }
        
    }
    
    //The define of Factory
    
    interface Factory
    {
        public ProductA getProductA();
        public ProductB getProductB();
    }
    
    class Factory1 implements Factory
    {

        @Override
        public ProductA getProductA()
        {
            return new ProductA1();
        }

        @Override
        public ProductB getProductB()
        {
            return new ProductB1();
        }
        
    }
    
    class Factory2 implements Factory
    {

        @Override
        public ProductA getProductA()
        {
            return new ProductA2();
        }

        @Override
        public ProductB getProductB()
        {
            return new ProductB2();
        }
        
    }
    
    public void test()
    {
        Factory factory = new Factory1();
        ProductA productA = factory.getProductA();
        ProductB productB = factory.getProductB();
        productA.getName();
        productB.getName();
        
        factory = new Factory2();
        productA = factory.getProductA();
        productB = factory.getProductB();
        productA.getName();
        productB.getName();
    }
    
    public static void main(String[] args)
    {
        AbstractFactory af = new AbstractFactory();
        af.test();
    }
    
}


