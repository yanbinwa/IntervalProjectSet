package yanbinwa.designPattern.Prototype;

/**
 * 原型模式，用于对一个实例进行复制
 * 
 * @author yanbinwa
 *
 */

public class ProtoType
{
    abstract class Prototype implements Cloneable 
    {  
        public Prototype clone()
        {  
            Prototype prototype = null;  
            try
            {  
                prototype = (Prototype)super.clone();  
            }
            catch(CloneNotSupportedException e)
            {  
                e.printStackTrace();  
            }  
            return prototype;   
        }  
        public void show()
        {
            
        }
    }  
      
    class ConcretePrototype1 extends Prototype 
    {  
        public void show()
        {  
            System.out.println("get ConcretePrototype1");
        }  
    }  
    
    class ConcretePrototype2 extends Prototype 
    {  
        public void show()
        {  
            System.out.println("get ConcretePrototype2");
        }  
    } 
    
    public void test()
    {
        Prototype protoType1 = new ConcretePrototype1();
        Prototype copyConcretePrototype = protoType1.clone();
        copyConcretePrototype.show();
        
        Prototype protoType2 = new ConcretePrototype2();
        copyConcretePrototype = protoType2.clone();
        copyConcretePrototype.show();
    }
    
    public static void main(String[] args)
    {
        ProtoType protoType = new ProtoType();
        protoType.test();
    }
    
}
