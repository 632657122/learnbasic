package basic.learn.sort;

import java.util.Arrays;

/**
 * @author wangtao
 * @date 2018-12-24
 * @desc 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64};
        System.out.println("排序之前：");
        System.out.println(Arrays.toString(a));

        //快速排序
        quick(a);
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(a));

    }

    private static void quick(int[] a) {
        if (a.length > 0) {
            quickSort(a,0,a.length-1);
        }
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            // 如果不加这个判断递归会无法退出导致堆栈溢出异常
            int middle = getMiddle(a, low, high);
            quickSort(a, 0, middle - 1);
            quickSort(a, middle + 1, high);
        }
    }

    private static int getMiddle(int[] a, int low, int high) {
        int temp = a[low];//基准元素
        while (low < high) {
            // 找到比基准元素小的元素位置
            high--;
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }
}
