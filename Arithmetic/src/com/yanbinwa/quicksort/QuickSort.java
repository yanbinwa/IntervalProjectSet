package com.yanbinwa.quicksort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort
{
    List<Integer> datas = new ArrayList<Integer>();
    
    public QuickSort(List<Integer> datas)
    {
        this.datas.addAll(datas);
    }
    
    public void sort()
    {
        sort(0, datas.size() - 1);
    }
    
    public void sort(int startIndex, int endIndex)
    {
        if (startIndex >= endIndex)
        {
            return;
        }
        int value = datas.get(startIndex);
        int i = startIndex;
        int j = endIndex;
        //先从后往前，再从前往后
        while(i < j)
        {
            while(value <= datas.get(j) && i < j)
            {
                j --;
            }
            if (i < j)
            {
                datas.set(i, datas.get(j));
                i ++;
            }
            while(value >= datas.get(i) && i < j)
            {
                i ++;
            }
            if (i < j)
            {
                datas.set(j, datas.get(i));
                j --;
            }
        }
        datas.set(i, value);
        sort(startIndex, i - 1);
        sort(i + 1, endIndex);
    }
    
    public void print()
    {
        for(Integer data : datas)
        {
            System.out.println(data);
        }
    }
    
    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<Integer>();
        
        list.add(5);
        list.add(9);
        list.add(3);
        list.add(7);
        list.add(6);
        list.add(8);
        list.add(1);
        
        QuickSort quickSort = new QuickSort(list);
        quickSort.print();
        quickSort.sort();
        System.out.println("--------------------");
        quickSort.print();
        
    }
}
