package yanbinwa.designPattern.Bridge;

public class Bridge
{
    interface DrawAPI
    {
        public void drawCircle();
    }
    
    class DrawAPIImpl1 implements DrawAPI
    {

        @Override
        public void drawCircle()
        {
            System.out.println("实线");
        }
        
    }
    
    class DrawAPIImpl2 implements DrawAPI
    {

        @Override
        public void drawCircle()
        {
            System.out.println("虚线");
        }
        
    }
    
    abstract class Shape
    {
        protected DrawAPI drawAPI = null;
        
        Shape(DrawAPI drawAPI)
        {
            this.drawAPI = drawAPI;
        }
        
        public abstract void drawShape();
    }
    
    class Circle extends Shape
    {

        Circle(DrawAPI drawAPI)
        {
            super(drawAPI);
        }

        @Override
        public void drawShape()
        {
            System.out.println("Circle: ");
            drawAPI.drawCircle();
        }
        
    }
    
    class Oval extends Shape
    {

        Oval(DrawAPI drawAPI)
        {
            super(drawAPI);
        }

        @Override
        public void drawShape()
        {
            System.out.println("Oval: ");
            drawAPI.drawCircle();
        }
        
    }
    
    public void test()
    {
        Shape circle = new Circle(new DrawAPIImpl1());
        circle.drawShape();
        
        circle = new Circle(new DrawAPIImpl2());
        circle.drawShape();
        
        Shape oval = new Oval(new DrawAPIImpl1());
        oval.drawShape();
        
        oval = new Oval(new DrawAPIImpl2());
        oval.drawShape();
        
    }
    
    public static void main(String[] args)
    {
        new Bridge().test();
    }
}
