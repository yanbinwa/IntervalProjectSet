package com.yanbinwa.hash;

import java.util.HashMap;
import java.util.Map;

public class IHashMap
{
    public static void main(String[] args)
    {
        Map<Object, String> map = new HashMap<Object, String>();
        map.put(null, "wyb");
        System.out.println(map.get(null));
    }
}
