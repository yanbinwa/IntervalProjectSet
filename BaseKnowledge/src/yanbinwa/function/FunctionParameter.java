package yanbinwa.function;

public class FunctionParameter
{
    public static void test(String str)
    {
        str += "123";       //这里对于str还是重新赋值了，即str = str + "123", 只是临时变量改变了，值没有真正的改变
    }
    
    public static void main(String[] args)
    {
        String testStr = "wyb";
        test(testStr);
        System.out.println(testStr);
    }
}
