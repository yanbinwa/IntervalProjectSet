package yanbinwa.designPattern.State;

public class State
{
    
    interface IState
    {
        public void handle(Context context, String word);
    }
    
    class StateLowerCase implements IState
    {

        @Override
        public void handle(Context context, String word)
        {
            System.out.println(word.toLowerCase());
            context.setState(new StateUpperCase());
        }
        
    }
    
    class StateUpperCase implements IState
    {

        @Override
        public void handle(Context context, String word)
        {
            System.out.println(word.toUpperCase());
            context.setState(new StateLowerCase());
        }
        
    }
    
    class Context
    {
        IState state;
        
        public Context(IState state)
        {
            this.state = state;
        }
        
        public void setState(IState state)
        {
            this.state = state;
        }
        
        public void writeWord(String word)
        {
            state.handle(this, word);
        }
    }
    
    public void test()
    {
        Context ct = new Context(new StateLowerCase());
        ct.writeWord("Wyb");
        ct.writeWord("Wyb");
        ct.writeWord("Wyb");
        ct.writeWord("Wyb");
        ct.writeWord("Wyb");
    }
    
    public static void main(String[] args)
    {
        new State().test();
    }
}
