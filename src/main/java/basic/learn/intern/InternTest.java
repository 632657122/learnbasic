package basic.learn.intern;

/**
 * @author wangtao
 * @date 2019-01-08
 * @desc
 */
public class InternTest {

    public static void main(String[] args) {
        String str1 = new StringBuffer("stringBuilder").append("test").toString();
        String str2 = new StringBuffer("stringBuilder").append("test").toString();

        System.out.println(str2.intern() == str2);
    }
}
