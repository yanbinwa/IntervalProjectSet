package yanbinwa.designPattern.Decorator;

public class Decorator
{
    interface MyStream
    {
        public String showStream();
    }
    
    class MyBaseStream implements MyStream
    {

        @Override
        public String showStream()
        {
            return "MyBaseStream";
        }
        
    }
    
    interface StreamDecorator extends MyStream
    {
        String getDecoration();
    }
    
    class StreamDecoratorArray implements StreamDecorator
    {

        MyStream myStream = null;
        
        public StreamDecoratorArray(MyStream myStream)
        {
            this.myStream = myStream;
        }
        
        @Override
        public String showStream()
        {
            return getDecoration() + ":" + myStream.showStream();
        }

        @Override
        public String getDecoration()
        {
            return "Array";
        }
        
    }
    
    class StreamDecoratorBuffer implements StreamDecorator
    {

        MyStream myStream = null;
        
        public StreamDecoratorBuffer(MyStream myStream)
        {
            this.myStream = myStream;
        }
        
        @Override
        public String showStream()
        {
            return getDecoration() + ":" + myStream.showStream();
        }

        @Override
        public String getDecoration()
        {
            return "Buffer";
        }
        
    }
    
    public void test()
    {
        MyStream myStream = new MyBaseStream();
        System.out.println(myStream.showStream());
        
        MyStream arrayStream = new StreamDecoratorArray(myStream);
        System.out.println(arrayStream.showStream());
        
        MyStream bufferStream = new StreamDecoratorBuffer(arrayStream);
        System.out.println(bufferStream.showStream());
        
    }
    
    public static void main(String[] args)
    {
        new Decorator().test();
    }
    
}
