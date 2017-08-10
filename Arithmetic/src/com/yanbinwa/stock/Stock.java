package com.yanbinwa.stock;

public class Stock
{
    
    private int[] stockPrice;
    
    public Stock(int[] stockPrice)
    {
        this.stockPrice = stockPrice;
    }
    
    public void getMaxIncome()
    {
        int minPrice = 0;
        int minPricePoint = 0;
        int maxIncome = 0;
        int buyPoint = 0;
        boolean isMaxIncomeFromCurrentMinPricePoint = true;
        
        if(stockPrice == null)
        {
            System.out.println("Error");
        }
        
        for(int i = 0; i < stockPrice.length; i ++)
        {
            if(i == 0)
            {
                minPrice = stockPrice[i];
                minPricePoint = i;
                maxIncome = 0;
                buyPoint = i;
                isMaxIncomeFromCurrentMinPricePoint = true;
            }
            else
            {
                int income = stockPrice[i] - minPrice;
                if(income > maxIncome)
                {
                    maxIncome = income;
                    
                    if (!isMaxIncomeFromCurrentMinPricePoint)
                    {
                        isMaxIncomeFromCurrentMinPricePoint = true;
                        buyPoint = minPricePoint;
                    }
                }
                if (stockPrice[i] < minPrice)
                {
                    isMaxIncomeFromCurrentMinPricePoint = false;
                    minPricePoint = i;
                    minPrice = stockPrice[i];
                }
            }
        }
        System.out.println("The buy index is: " + buyPoint);
    }
    
    public static void main(String[] args)
    {
        
    }
}
