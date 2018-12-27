package basic.learn.methodparam;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author wangtao
 * @date 2018-12-27
 * @desc 运行时获得Java程序中方法的参数名称
 */
public class ParameterName {

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = ParameterName.class.getMethod("main", String[].class);
        for (final Parameter parameter : method.getParameters()) {
            System.out.println("Parameter:"+parameter.getName());
        }
    }
}
