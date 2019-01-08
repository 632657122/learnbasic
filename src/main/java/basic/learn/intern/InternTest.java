package basic.learn.intern;

/**
 * @author wangtao
 * @date 2019-01-08
 * @desc
 */
public class InternTest {

    public static void main(String[] args) {

        //StringBuilder的toString()方法创建了一个new String()对象。
        String str1 = new StringBuilder("stringBuilder").toString();
        /*
        上面代码等价于
        String a = "stringBuilder";
        String str1 = new StringBuilder(a).toString;
        所以"stringBuilder"先被创建在常量池中
        */
        System.out.println(str1.intern() == "stringBuilder");//true
        System.out.println(str1.intern() == str1);//false


        String str2 = new StringBuilder("stringBuilder").append("test").toString();
        /*
        上面代码等价于
        String a = "stringBuilder";
        String b = "test";
        String str2 = new StringBuilder(a).append(b).toString;
        所以"stringBuildertest"最先创建在堆中
         */

        System.out.println(str2.intern() == "stringBuildertest");//true
        System.out.println(str2.intern() == str2);//true
        /*
        如果str2和str1字面量相同且str1先创建，则str2.intern()指向str1
         */

    }
}
