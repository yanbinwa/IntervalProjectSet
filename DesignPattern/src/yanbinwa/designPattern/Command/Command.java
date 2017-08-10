package yanbinwa.designPattern.Command;

public class Command
{
    interface ICommand
    {
        public void execute();
    }
    
    class TurnOnCommand implements ICommand
    {

        @Override
        public void execute()
        {
            System.out.println("Ture on the light");
        }
        
    }
    
    class TurnOffCommand implements ICommand
    {

        @Override
        public void execute()
        {
            System.out.println("Ture off the light");
        }
        
    }
    
    class Light
    {
        public void execute(ICommand command)
        {
            System.out.println("Light 1: ");
            command.execute();
        }
    }
    
    class Switch
    {
        Light light = null;
        
        public Switch(Light light)
        {
            this.light = light;
        }
        
        public void turnOnTheLight()
        {
            light.execute(new TurnOnCommand());
        }
        
        public void turnOffTheLight()
        {
            light.execute(new TurnOffCommand());
        }
    }
    
    public void test()
    {
        Light light = new Light();
        Switch switzh = new Switch(light);
        switzh.turnOffTheLight();
        switzh.turnOnTheLight();
    }
    
    public static void main(String[] args)
    {
        new Command().test();
    }
}
