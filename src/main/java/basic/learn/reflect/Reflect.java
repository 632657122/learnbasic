package basic.learn.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangtao
 * @date 2019-02-14
 * @desc 反射就是在运行时才知道要操作的类是什么
 *       并且可以在运行时获取类的完整构造，并调用对应的方法。
 */
public class Reflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("basic.learn.reflect.Apple");
        Method method = clazz.getMethod("setPrice", int.class);
        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();//或 clazz.newInstance()

        method.invoke(object, 4);

        Method method1 = clazz.getMethod("getPrice");
        System.out.println(method1.invoke(object));
    }
}
