package com.yanbinwa.list.copyOnWriteArrayList;

import java.util.Arrays;

public class TestDeepCopy
{
    public static void main(String[] args)
    {
        Object[] list = new Object[2];
        list[0] = new Object();
        list[1] = new Object();
        for(int i = 0; i < list.length; i ++)
        {
            System.out.println(list[i]);
        }
        
        Object[] newElements = Arrays.copyOf(list, list.length + 1);        //这里是浅拷贝
        newElements[2] = new Object();
        for(int i = 0; i < newElements.length; i ++)
        {
            System.out.println(newElements[i]);
        }
    }
}
