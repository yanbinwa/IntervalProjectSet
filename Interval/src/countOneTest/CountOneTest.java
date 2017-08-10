package countOneTest;

public class CountOneTest
{
    public static int count1(int num) 
    {
        String numStr = new Integer(num).toString();
        int count = 0;
        for (int i = numStr.length() - 1; i >= 0; i --)
        {
            int beforeNum = num / getMask(i + 1);
            int currentNum = num / getMask(i) % 10;
            int afterNum = num % getMask(i);
            
            int count1 = beforeNum * getMask(i);
            int count2 = 0;
            int count3 = 0;
            
            if (currentNum >= 2)
            {
                count2 = getMask(i);
                count3 = 0;
            }
            else if (currentNum == 1)
            {
                count2 = 0;
                count3 = afterNum - 0 + 1;
            }
            count += count1 + count2 + count3;
        }
        return count;
    }
    
    public static int count2(int num)
    {
        int count = 0;
        for(int i = 1; i <= num; i ++)
        {
            count += countForSingleNum(i);
        }
        return count;
    }
    
    public static int countForSingleNum(int num)
    {
        String numStr = new Integer(num).toString();
        int length = numStr.length();
        int count = 0;
        for(int i = length - 1; i >= 0; i --)
        {
            if ((num / getMask(i)) % 10 == 1)
            {
                count ++;
            }
        }
        return count;
    }
    
    public static int getMask(int length)
    {
        int mask = 1;
        for(int i = 0; i < length; i ++)
        {
            mask *= 10;
        }
        return mask;
    }
    
    public static void main(String[] args)
    {
        int[] testList = {101, 579, 1000, 2121};
        for (int i = 0; i < testList.length; i ++)
        {
            if (count1(testList[i]) != count2(testList[i]))
            {
                System.out.println("Failed!");
            }
        }
    }
}
