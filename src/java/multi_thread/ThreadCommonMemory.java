package multi_thread;

/**
 * 测试多线程 共享变量，多个线程修， jmm 是否可保证其准确性
 * @author fatKarin
 * @date 2020/3/13 17:00
 */
public class ThreadCommonMemory {

    private int i = 0;

    public static void main(String[] args) {
        ThreadCommonMemory threadCommonMemory = new ThreadCommonMemory();

        new Thread(() -> {
            int j = 0;
            while ( j++ < 10000) {
                threadCommonMemory.i++;
                System.out.println("thread1 i:" + threadCommonMemory.i );
            }
        }).start();

        new Thread(() -> {
            int j = 0;
            while ( j++ < 10000) {
                threadCommonMemory.i++;
                System.out.println("thread2 i:" + threadCommonMemory.i );
            }
        }).start();
    }
}
