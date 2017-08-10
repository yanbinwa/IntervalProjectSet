package com.yanbinwa.equal.car2;

/**
 * 这里就需要进行约定了，如果count值相同，那么就相同，或者直接获取真实的类进行比较
 * 
 * @author yanbinwa
 *
 */

public class SubCar2 extends Car2
{
    int count1;
    
    public SubCar2(int count, int count1)
    {
        super(count);
        this.count1 = count1;
    }
    
    public boolean equals(Object object)
    {
        if (object == null)
        {
            return false;
        }
        if (!(object instanceof SubCar2))
        {
            return false;
        }
        if (!object.getClass().equals(this.getClass()))
        {
            return false;
        }
        SubCar2 otherCar2 = (SubCar2)object;
        return super.equals(otherCar2) && otherCar2.count == this.count;
    }
    
    public static void main(String[] args)
    {
        Car2 c = new Car2(10);
        SubCar2 sc = new SubCar2(10, 20);
        System.out.println(sc.equals(c));
        System.out.println(c.equals(sc));
    }
}
