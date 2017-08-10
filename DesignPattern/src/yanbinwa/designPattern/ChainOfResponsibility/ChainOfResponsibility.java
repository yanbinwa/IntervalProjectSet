package yanbinwa.designPattern.ChainOfResponsibility;

public class ChainOfResponsibility
{
    
    class PurchaseRequest
    {
        int value;
        
        public PurchaseRequest(int value)
        {
            this.value = value;
        }
        
        public int getValue()
        {
            return this.value;
        }
        
    }
    
    abstract class PurchasePower
    {
        protected PurchasePower successor;
        
        abstract int getAllowed();
        abstract String getRole();
        
        public void setSuccessor(PurchasePower successor)
        {
            this.successor = successor;
        }
        
        public void processRequest(PurchaseRequest request)
        {
            if (request.getValue() > getAllowed())
            {
                successor.processRequest(request);
            }
            else
            {
                System.out.println(getRole() + " resolved this value: " + request.getValue());
            }
        }
    }
    
    class ManagerPPower extends PurchasePower
    {

        @Override
        int getAllowed()
        {
            return 10;
        }

        @Override
        String getRole()
        {
            return "Manager";
        }
        
    }
    
    class DirectorPPower extends PurchasePower
    {

        @Override
        int getAllowed()
        {
            return 20;
        }

        @Override
        String getRole()
        {
            return "Director";
        }
        
    }
    
    class PresidentPPower extends PurchasePower
    {

        @Override
        int getAllowed()
        {
            return 30;
        }

        @Override
        String getRole()
        {
            return "President";
        }
        
    }
    
    public void test()
    {
        PurchasePower managerPPower = new ManagerPPower();
        PurchasePower directorPPower = new DirectorPPower();
        PurchasePower presidentPPower = new PresidentPPower();
        
        managerPPower.setSuccessor(directorPPower);
        directorPPower.setSuccessor(presidentPPower);
        
        PurchaseRequest request1 = new PurchaseRequest(5);
        PurchaseRequest request2 = new PurchaseRequest(15);
        PurchaseRequest request3 = new PurchaseRequest(25);
        
        managerPPower.processRequest(request1);
        managerPPower.processRequest(request2);
        managerPPower.processRequest(request3);
    }
    
    public static void main(String[] args)
    {
        new ChainOfResponsibility().test();
    }
    
}
