package com.yanbinwa.equal.car1;

public class SubCar1 extends Car1
{
    int count1;
    
    public SubCar1(int count, int count1)
    {
        super(count);
        this.count1 = count1;
    }
    
    public boolean equals(Object object)
    {
        if(object == null)
        {
            return false;
        }
        if(! (object instanceof SubCar1))
        {
            return false;
        }
        SubCar1 otherCar1 = (SubCar1)object;
        return super.equals(otherCar1) && otherCar1.count1 == this.count1;
    }

    public static void main(String[] args)
    {
        Car1 c = new Car1(10);
        SubCar1 sc = new SubCar1(10, 20);
        System.out.println(sc.equals(c));
        System.out.println(c.equals(sc));
        //这里违背了交换律
    }
}
