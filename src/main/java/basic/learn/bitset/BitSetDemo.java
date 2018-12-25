package basic.learn.bitset;

import java.util.BitSet;

/**
 * @author wangtao
 * @date 2018-12-24
 * @desc
 */
public class BitSetDemo {

    private static int cal;

    public BitSetDemo() {
        System.out.println("构造方法");
    }

    static {
        if (String.valueOf(System.currentTimeMillis()).endsWith("1")) {
            cal = 1;
        } else {
            cal = 3;
        }
        System.out.println("静态代码块");
    }

    {
        System.out.println("普通代码块");
    }


    public static void main(String[] args) {
        BitSet bitSet = new BitSet(64);

        for (int i = 100; i >= 0; i--) {
            bitSet.set(i);
        }
        bitSet.set(1, 21, false);
        BitSetDemo b= new BitSetDemo();

        System.out.println(cal);
    }
}
