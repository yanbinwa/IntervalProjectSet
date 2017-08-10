package yanbinwa.java8;

import java.util.Arrays;

public class Lambda
{
    public static void main(String[] args)
    {
        Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );
    }
}
