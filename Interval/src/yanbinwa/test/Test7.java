package yanbinwa.test;

public class Test7 extends Test6 {

    public static int j = 6;

    static 
    {
        j++;
        System.out.println("Test7 step1 j " + j);
    }

    {
        j++;
        System.out.println("Test7 step2 j " + j);
    }
}
