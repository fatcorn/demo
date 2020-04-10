package api_test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author fatKarin
 * @date 2020/4/8 14:12
 */
public class MapTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] strings = new String[200];
        HashMap<String,String> map = new HashMap<>();

        int i = 0;
        while (++i < 200) {
            strings[i] = i + "";
            map.put(strings[i],strings[i]);
            Class<?> mapType = map.getClass();
            Method capacity = mapType.getDeclaredMethod("capacity");
            capacity.setAccessible(true);
            System.out.println("map size:" + map.size() +";capacity:" + capacity.invoke(map));
        }

    }
}
