package com.yanbinwa.equal.car2;

public class Car2
{
    int count;
    
    public Car2(int count)
    {
        this.count = count;
    }
    
    public boolean equals(Object object)
    {
        if (object == null)
        {
            return false;
        }
        if (!(object instanceof Car2))
        {
            return false;
        }
        Car2 otherCar1 = (Car2)object;
        return otherCar1.count == this.count;
    }
}
