package com.yanbinwa.hash;

import java.util.Hashtable;
import java.util.Map;

public class IHashTable
{
    public static void main(String[] args)
    {
        Map<Object, String> map = new Hashtable<Object, String>();
        map.put(null, "wyb");
        System.out.println(map.get(null));
    }
}
