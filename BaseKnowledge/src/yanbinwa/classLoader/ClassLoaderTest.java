package yanbinwa.classLoader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest
{
    public static void test()
    {
        Thread t = new Thread(new Runnable() {      //new Runnable也是一个内部类

            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                
            }
            
        });
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        ClassLoader myClassLoader = new ClassLoader()   //这里为内部类
        {
            protected Class<?> findClass(String name)
            {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream in = getClass().getResourceAsStream(filename);
                if (in == null)
                {
                    throw new RuntimeException("Could not load class file: " + filename);
                }
                byte[] b;
                try
                {
                    b = new byte[in.available()];
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
                return defineClass(name, b, 0, b.length);
            }
        };
        Object obj = myClassLoader.loadClass("yanbinwa.classLoader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());    
        System.out.println(obj instanceof yanbinwa.classLoader.ClassLoaderTest);
    }
}
