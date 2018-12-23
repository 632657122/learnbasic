package basic.learn.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtao
 * @date 2018-12-22
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(5);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.subList(0,2).clear();
        System.out.println(list.size());
    }
}
