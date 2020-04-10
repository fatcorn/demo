package api_test;

/**
 * @author fatKarin
 * @date 2020/4/3 13:31
 */
public class ClassCopyTest {

    private Integer integer3 = 3;

    public static void main(String[] args) {
        Integer integer1 = 1;
        Integer integer2 = 2;
        ClassCopyTest copyTest = new ClassCopyTest();
        copyTest.swap(integer1, integer2);

        copyTest.fixObjectValue(copyTest);

        System.out.println("integer1:" + integer1);
        System.out.println("integer2:" + integer2);
        System.out.println("integer3:" + copyTest.integer3);
    }

    /**
     * java Java中只有传值，
     * 这个方法传递是传值，值是原integer1（1）与integer2（2）的值，string的也是一样，swap 不会改变
     * @param i1
     * @param i2
     */
    public void swap(Integer i1, Integer i2) {
        Integer temp = i1;
        i1 = 2;
        i2 = 1;
//        i1 = i2;
//        i2 = temp;
    }
    /**
     * java Java中只有传值，
     * 这个方法传递是传值，值是对copyTest的引用，所以之后copyTest里的属性值变了
     * @param C1
     */
    public void fixObjectValue(ClassCopyTest C1) {
        C1.integer3 = 4;
    }

}
