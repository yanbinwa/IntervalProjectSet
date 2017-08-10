package com.yanbinwa.equal.car1;

public class Car1
{
    int count;
    
    public Car1(int count)
    {
        this.count = count;
    }
    
    public boolean equals(Object object)
    {
        if (object == null)
        {
            return false;
        }
        if (!(object instanceof Car1))
        {
            return false;
        }
        Car1 otherCar1 = (Car1)object;
        return otherCar1.count == this.count;
    }
}


