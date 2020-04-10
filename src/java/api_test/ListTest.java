package api_test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Array 插入速度比Linked 速度快很多。linked 在中间插入速度比Array快(数量越大越快得多)
 * @author fatKarin
 * @date 2020/4/7 10:33
 */
public class ListTest {

    public static void main(String[] args) {

        ListTest listTest = new ListTest();
        listTest.linkedInsert();
        listTest.arrayInsert();
    }

    private void linkedInsert() {
        long beforeTime = System.currentTimeMillis();
        List<Integer> list = new LinkedList<>();
        int i = 0;
        while (i < 10000000) {
            list.add(i);
            i ++;
        }
        list.add(list.size()/2,i);
        list.remove(list.size()/2);
        //list.forEach(System.out::println);
        System.out.println("链表List耗时：" + (System.currentTimeMillis() - beforeTime));
    }

    private void arrayInsert() {
        long beforeTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i < 10000000) {
            list.add(i);
            i ++;
        }
        list.add(list.size()/2,i);
        list.remove(list.size()/2);
        //list.forEach(System.out::println);
        System.out.println("数组List耗时：" + (System.currentTimeMillis() - beforeTime));
    }
}
