package com.yanbinwa.mergesort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort
{
    List<Integer> datas = new ArrayList<Integer>();
    List<Integer> tmpDatas = new ArrayList<Integer>();
    
    public MergeSort(List<Integer> datas)
    {
        this.datas.addAll(datas);
        this.tmpDatas.addAll(datas);
    }
    
    public void sort()
    {
        sort(0, datas.size() - 1);
    }
    
    private void merge(int startIndex, int mid, int endIndex)
    {
        int i, j, k;
        for(i = startIndex, j = mid + 1, k = startIndex; i <= mid && j <= endIndex; k++)
        {
            if(datas.get(i) <= datas.get(j))
            {
                tmpDatas.set(k, datas.get(i));
                i ++;
            }
            else
            {
                tmpDatas.set(k, datas.get(j));
                j ++;
            }
        }
        if (i <= mid)
        {
            for(int l = i; l <= mid; l ++)
            {
                tmpDatas.set(k, datas.get(l));
                k ++;
            }
        }
        else
        {
            for(int l = j; l <= endIndex; l ++)
            {
                tmpDatas.set(k, datas.get(l));
                k ++;
            }
        }
        for(int l = startIndex; l <= endIndex; l ++)
        {
            datas.set(l, tmpDatas.get(l));
        }
    }
    
    private void sort(int startIndex, int endIndex)
    {
        System.out.println("startIndex is: " + startIndex + "; endIndex is: " + endIndex);
        if (startIndex >= endIndex)
        {
            return;
        }
        int mid = startIndex + (endIndex - startIndex) / 2;
        sort(startIndex, mid);
        sort(mid + 1, endIndex);
        merge(startIndex, mid, endIndex);
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
        
        MergeSort mergeSort = new MergeSort(list);
        mergeSort.print();
        mergeSort.sort();
        System.out.println("--------------------");
        mergeSort.print();
        
    }
}
