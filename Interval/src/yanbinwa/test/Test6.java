package yanbinwa.test;

public class Test6 {

    public static int i = 10;

    {
        i++;
        System.out.println("Test6 step1 i " + i);
    }

    static 
    {
        i = Test7.j;
        System.out.println("Test6 step2 i " + i);
    }

    public Test6() 
    {
        i++;
        System.out.println("Test6 step3 i " + i);
    }
}
