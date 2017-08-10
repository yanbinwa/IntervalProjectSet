package com.yanbinwa.list.copyOnWriteArrayList;

import java.util.Arrays;
import java.util.Collection;

public class ICopyOnWriteArrayList1<E>
{
    private volatile transient Object[] array;  
    
    final Object[] getArray() 
    {  
        return array;  
    }  
    final void setArray(Object[] a) 
    {  
        array = a;  
    }  
    public ICopyOnWriteArrayList1() 
    {  
        setArray(new Object[0]);  
    }  
    public ICopyOnWriteArrayList1(Collection<? extends E> c) 
    {  
        Object[] elements = c.toArray();            //如果elements不是Object[]类型，需要将elements改为Object[]类型
        if (elements.getClass() != Object[].class)  
        {
            elements = Arrays.copyOf(elements, elements.length, Object[].class); 
        }
        setArray(elements);  
    }  
    // Creates a list holding a copy of the given array.  
    public ICopyOnWriteArrayList1(E[] toCopyIn) 
    {  
        setArray(Arrays.copyOf(toCopyIn, toCopyIn.length, Object[].class));  
    }  
}
