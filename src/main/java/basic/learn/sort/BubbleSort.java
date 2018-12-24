package basic.learn.sort;

import java.util.Arrays;

/**
 * @author wangtao
 * @date 2018-12-24
 * @desc 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64};
        System.out.println("排序之前：");
        System.out.println(Arrays.toString(a));
        long start = System.currentTimeMillis();

        //冒泡排序
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                // 这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println("排序耗时："+(System.currentTimeMillis() - start));
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(a));



    }
}
