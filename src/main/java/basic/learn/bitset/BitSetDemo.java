package basic.learn.bitset;

import java.util.BitSet;

/**
 * @author wangtao
 * @date 2018-12-24
 * @desc
 */
public class BitSetDemo {


    public static void main(String[] args) {
        BitSet bitSet = new BitSet(64);

        for (int i = 100; i >= 0; i--) {
            bitSet.set(i);
        }
        bitSet.set(1,21,false);

        System.out.println(bitSet.toString());
    }
}
